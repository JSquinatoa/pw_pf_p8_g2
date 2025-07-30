<template>
  <div class="container_consultarcliente">
    <h1>Eliminar Bodega</h1>
    <div class="container_consultar">
      <input class="input_consulta" type="number" v-model="id" placeholder="Ingrese el código de la bodega a eliminar"
        :disabled="deshabilitadoConsulta" />
      <button class="boton_consulta" @click="obtenerBodegaPorId()">
        Consultar
      </button>
    </div>

    <transition name="fade">
      <div v-if="temporarymensaje" :class="['mensaje', mensajeType]">
        {{ temporarymensaje }}
      </div>
    </transition>

    <div v-if="bodegaCargada" class="containerformulario">
      <h2>Datos de la Bodega</h2>
      <div class="form-elementos-contenido">
        <p type="Código:">
          <input type="text" v-model="bodega.codigo" :disabled="true" />
        </p>
        <p type="Nombre:">
          <input type="text" v-model="bodega.nombre" :disabled="true" />
        </p>
        <p type="Ubicación:">
          <input type="text" v-model="bodega.ubicacion" :disabled="true" />
        </p>
      </div>
    </div>
    <button v-if="bodegaCargada" class="boton_eliminar_especifico" @click="confirmarEliminar()">
      Eliminar
    </button>
  </div>
</template>

<script>
import { consultarBodegaPorIdFachada, eliminarBodegaFachada } from "@/clients/BodegaClient";
import "@/css/EstiloGenerico.css";

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
        this.mensajesTemporales('Ocurrió un error al eliminar la bodega. Inténtalo de nuevo.', 'error');
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
.container_consultarcliente h1 {
  color: #003366;
  margin-bottom: 15px;
  font-size: 2.2em;
  text-align: center;
}

.form-elementos-contenido {
  display: flex;
  flex-direction: column;
  gap: 15px;
  width: 100%;
}

p[type] input:disabled {
  background-color: #e9ecef;
  cursor: not-allowed;
  opacity: 0.7;
}

.containerformulario h2 {
  color: #003366;
  font-size: 1.8em;
  margin-bottom: 0;
  text-align: center;
}

.boton_eliminar_especifico {
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

.boton_eliminar_especifico:hover {
  background-color: #c82333;
  transform: translateY(-2px);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.boton_eliminar_especifico:active {
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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@media screen and (max-width: 768px) {
  .container_consultarcliente h1 {
    font-size: 1.8em;
  }

  .containerformulario h2 {
    font-size: 1.6em;
  }

  .boton_eliminar_especifico {
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
  .container_consultarcliente h1 {
    font-size: 1.5em;
    margin-bottom: 10px;
  }

  .containerformulario h2 {
    font-size: 1.4em;
  }

  .boton_eliminar_especifico {
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