<template>
  <div class="container_eliminar_bodega">
    <h1>Eliminar Bodega</h1>

    <div class="consulta-section">
      <input
        class="input_consulta"
        type="number"
        v-model="id"
        placeholder="Ingrese el código de la bodega a eliminar"
        :disabled="deshabilitadoConsulta"
      />
      <button class="boton_consulta" @click="obtenerBodegaPorId()">
        Consultar
      </button>
    </div>

    <transition name="fade">
      <div v-if="temporarymensaje" :class="['mensaje', mensajeType]">
        {{ temporarymensaje }}
      </div>
    </transition>

    <div v-if="bodegaCargada" class="form-container-ente">
      <h2>Datos de la Bodega</h2>
      <div class="form-elementos-contenido">
        <p type="Código:">
          <input type="text" v-model="bodega.codigo" :disabled="true"/>
        </p>
        <p type="Nombre:">
          <input type="text" v-model="bodega.nombre" :disabled="true"/>
        </p>
        <p type="Ubicación:">
          <input type="text" v-model="bodega.ubicacion" :disabled="true"/>
        </p>
      </div>
      <button class="boton_eliminar" @click="confirmarEliminar()">
        Eliminar
      </button>
    </div>
  </div>
</template>

<script>
import { consultarBodegaPorIdFachada, eliminarBodegaFachada } from "@/clients/BodegaClient";

export default {
  data() {
    return {
      id: null,
      bodega: {
        codigo: "",
        nombre: "",
        ubicacion: ""
      },
      bodegaCargada: false,
      deshabilitadoConsulta: false,
      temporarymensaje: null,
      mensajeType: '',
      mensajeTimeout: null,
    };
  },
  methods: {
    async obtenerBodegaPorId() {
      this.limpiarMensajesTemporales();
      this.bodegaCargada = false;
      this.bodega = { codigo: "", nombre: "", ubicacion: "" };

      if (!this.id) {
        this.mensajesTemporales('Por favor, ingrese un código para consultar.', 'error');
        return;
      }

      try {
        this.deshabilitadoConsulta = true;
        const bodegaPorId = await consultarBodegaPorIdFachada(this.id);
        
        if (bodegaPorId && bodegaPorId.codigo) { 
          this.bodega.codigo = bodegaPorId.codigo;
          this.bodega.nombre = bodegaPorId.nombre;
          this.bodega.ubicacion = bodegaPorId.ubicacion;
          this.bodegaCargada = true;
          this.mensajesTemporales(`Bodega ${this.id} cargada, lista para eliminar.`, 'success');
        } else {
          this.mensajesTemporales(`La bodega con el código ${this.id} no existe, vuelva a intentarlo.`, 'error');
        }
      } catch (error) {
        console.error("Error al consultar la bodega:", error);
        this.mensajesTemporales('Ocurrió un error al consultar la bodega. Inténtelo de nuevo.', 'error');
      } finally {
        this.deshabilitadoConsulta = false;
      }
    },

    async confirmarEliminar() {
      this.limpiarMensajesTemporales();
      if (!this.id || !this.bodegaCargada) {
        this.mensajesTemporales('Primero debe consultar una bodega para eliminar.', 'error');
        return;
      }

      const confirmacion = confirm(`¿Está seguro de que desea eliminar la bodega con código ${this.id}?`);
      if (!confirmacion) {
        this.mensajesTemporales('Eliminación cancelada.', 'info');
        return;
      }

      try {
        this.deshabilitadoConsulta = true; 
        await eliminarBodegaFachada(this.id);
        this.mensajesTemporales(`Bodega ${this.id} eliminada exitosamente.`, 'success');
        this.resetForm(); 
      } catch (error) {
        console.error("Error al eliminar la bodega:", error);
        this.mensajesTemporales('Ocurrió un error al eliminar la bodega. Inténtelo de nuevo.', 'error');
      } finally {
        this.deshabilitadoConsulta = false;
      }
    },

    resetForm() {
      this.limpiarMensajesTemporales();
      this.id = null;
      this.bodega = {
        codigo: "",
        nombre: "",
        ubicacion: ""
      };
      this.bodegaCargada = false;
    },

    mensajesTemporales(mensaje, tipo) {
      this.limpiarMensajesTemporales();
      this.temporarymensaje = mensaje;
      this.mensajeType = tipo;
      this.mensajeTimeout = setTimeout(() => {
        this.limpiarMensajesTemporales();
      }, 3000);
    },
    
    limpiarMensajesTemporales() {
      if (this.mensajeTimeout) {
        clearTimeout(this.mensajeTimeout);
        this.mensajeTimeout = null;
      }
      this.temporarymensaje = null;
      this.mensajeType = '';
    }
  },
  beforeUnmount() {
    this.limpiarMensajesTemporales();
  }
};
</script>

<style scoped>
.container_eliminar_bodega {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  gap: 20px;
}

.container_eliminar_bodega h1 {
  color: #003366;
  margin-bottom: 15px;
  font-size: 2.2em;
  text-align: center;
}

.consulta-section {
  display: flex;
  align-items: center;
  gap: 15px;
  width: 90%;
  max-width: 550px;
  margin-bottom: 20px;
}

.input_consulta {
  flex-grow: 1;
  padding: 10px 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1em;
  box-sizing: border-box;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
  background-color: white;
}

.input_consulta:focus {
  outline: none;
  border-color: #0c3e80;
  box-shadow: 0 0 8px rgba(12, 62, 128, 0.3);
}

.input_consulta::-webkit-outer-spin-button,
.input_consulta::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.input_consulta:disabled {
  background-color: #e9ecef;
  cursor: not-allowed;
  opacity: 0.7;
}

.boton_consulta {
  padding: 10px 20px;
  font-size: 1.1em;
  font-weight: bold;
  background-color: #07265c;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
}

.boton_consulta:hover {
  background-color: #217dbb;
  transform: translateY(-1px);
}

.boton_consulta:active {
  background-color: #002c5c;
  transform: translateY(0);
}

.form-container-ente {
  width: 90%;
  max-width: 550px;
  border: 10px double #003366;
  border-end-end-radius: 100px;
  border-start-start-radius: 100px;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.516);
  background-color: #f4f6f8;
  padding: 40px 30px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 25px;
}

.form-container-ente h2 {
  color: #003366;
  font-size: 1.8em;
  margin-bottom: 0;
}

.form-elementos-contenido {
  display: flex;
  flex-direction: column;
  gap: 15px;
  width: 100%;
}

p[type] {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
}

p[type]::before {
  content: attr(type);
  font-weight: bold;
  color: #333;
  margin-bottom: 8px;
  font-size: 1.1em;
}

p[type] input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1em;
  box-sizing: border-box;
  transition: border-color 0.3s ease, box-shadow 0.3s ease;
  background-color: white;
}

p[type] input:focus {
  outline: none;
  border-color: #0c3e80;
  box-shadow: 0 0 8px rgba(12, 62, 128, 0.3);
}

p[type] input:disabled {
  background-color: #e9ecef;
  cursor: not-allowed;
  opacity: 0.7;
}

.boton_eliminar {
  width: 90%;
  max-width: 250px;
  padding: 12px 25px;
  font-size: 1.4em;
  font-weight: bold;
  letter-spacing: 2px;
  background-color: #dc3545; 
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
  margin-top: 15px;
}

.boton_eliminar:hover {
  background-color: #c82333;
  transform: translateY(-2px);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.boton_eliminar:active {
  background-color: #bd2130;
  transform: translateY(0);
  box-shadow: none;
}

.mensaje {
  margin-top: 15px;
  padding: 10px 20px;
  border-radius: 5px;
  font-weight: bold;
  text-align: center;
  width: 90%;
  max-width: 400px;
  box-sizing: border-box;
  opacity: 1;
  transition: opacity 0.5s ease-in-out;
}

.mensaje.success {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.mensaje.error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

.mensaje.info {
  background-color: #cce5ff; 
  color: #004085; 
  border: 1px solid #b8daff;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}


@media screen and (max-width: 768px) {
  .container_eliminar_bodega h1 {
    font-size: 1.8em;
  }
  .consulta-section {
    width: 95%;
    max-width: 400px;
    gap: 10px;
  }
  .input_consulta {
    padding: 8px 10px;
    font-size: 0.95em;
  }
  .boton_consulta {
    padding: 8px 15px;
    font-size: 1em;
  }
  .form-container-ente {
    width: 95%;
    max-width: 450px;
    padding: 30px 20px;
    gap: 20px;
  }
  .form-container-ente h2 {
    font-size: 1.6em;
  }
  p[type]::before {
    font-size: 1em;
  }
  p[type] input {
    padding: 10px;
    font-size: 0.95em;
  }
  .boton_eliminar {
    padding: 10px 20px;
    font-size: 1.2em;
    max-width: 200px;
  }
  .mensaje {
    padding: 8px 15px;
    font-size: 0.9em;
  }
}

@media screen and (max-width: 480px) {
  .container_eliminar_bodega h1 {
    font-size: 1.5em;
    margin-bottom: 10px;
  }
  .consulta-section {
    flex-direction: column;
    width: 100%;
    max-width: 300px;
    gap: 10px;
  }
  .input_consulta {
    width: 100%;
  }
  .boton_consulta {
    width: 100%;
  }
  .form-container-ente {
    width: 100%;
    padding: 20px 15px;
    border: 5px double #003366;
    border-end-end-radius: 50px;
    border-start-start-radius: 50px;
    gap: 15px;
  }
  .form-container-ente h2 {
    font-size: 1.4em;
  }
  p[type]::before {
    font-size: 0.9em;
  }
  p[type] input {
    padding: 8px;
    font-size: 0.9em;
  }
  .boton_eliminar {
    padding: 8px 15px;
    font-size: 1em;
    max-width: 180px;
  }
  .mensaje {
    padding: 7px 10px;
    font-size: 0.85em;
  }
}
</style>