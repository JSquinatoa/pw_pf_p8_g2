<template>
  <div class="container_consultarcliente">
    <h1>Consultar y Actualizar Bodega</h1>

    <div class="container_consultar">
      <input class="input_consulta" type="text" v-model="id" placeholder="Ingrese el código de la bodega"
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
      <h2>Actualice la Bodega</h2>
      <div class="form-elementos-contenido">
        <p type="Código:">
          <input type="text" v-model="bodega.codigo" :disabled="deshabilitarCodigo" />
        </p>
        <p type="Nombre:">
          <input type="text" v-model="bodega.nombre" :disabled="deshabilitado" placeholder="Ingrese el nombre" />
        </p>
        <p type="Ubicación:">
          <input type="text" v-model="bodega.ubicacion" :disabled="deshabilitado" placeholder="Ingrese la ubicación" />
        </p>
      </div>

    </div>
    <button v-if="bodegaCargada" class="boton_opcion" @click="actualizarParcial()">
      Actualizar
    </button>
  </div>
</template>

<script>
import { consultarBodegaPorIdFachada, actualizarBodegaFachada } from "@/clients/BodegaClient";
import "@/css/EstiloGenerico.css"; /* Import the generic styles */

export default {
  data() {
    return {
      id: null,
      bodega: {
        codigo: "",
        nombre: "",
        ubicacion: ""
      },
      deshabilitarCodigo: false,
      deshabilitado: true,
      bodegaCargada: false,
      temporarymensaje: null,
      mensajeType: '',
      mensajeTimeout: null,
      deshabilitadoConsulta: false,
    };
  },
  methods: {
    async obtenerBodegaPorId() {
      this.limpiarMensajesTemporales();
      this.bodegaCargada = false;
      this.deshabilitado = true;
      this.deshabilitarCodigo = false;

      if (!this.id) {
        this.mensajesTemporales('Por favor, ingrese un código para consultar.', 'error');
        return;
      }

      try {
        this.deshabilitadoConsulta = true;
        const bodegaPorId = await consultarBodegaPorIdFachada(this.id);

        if (bodegaPorId) {
          this.bodega.codigo = bodegaPorId.codigo;
          this.bodega.nombre = bodegaPorId.nombre;
          this.bodega.ubicacion = bodegaPorId.ubicacion;
          this.bodegaCargada = true;
          this.deshabilitarCodigo = true;
          this.deshabilitado = false;
          this.mensajesTemporales(`Bodega ${this.id} cargada exitosamente.`, 'success');
        } else {
          this.bodega = { codigo: "", nombre: "", ubicacion: "" };
          this.bodegaCargada = false;
          this.mensajesTemporales(`La bodega con el código ${this.id} no existe, vuelva a intentarlo.`, 'error');
        }
      } catch (error) {
        console.error("Error al consultar la bodega:", error);
        this.bodega = { codigo: "", nombre: "", ubicacion: "" };
        this.bodegaCargada = false;
        this.mensajesTemporales('Ocurrió un error al consultar la bodega. Inténtalo de nuevo.', 'error');
      } finally {
        this.deshabilitadoConsulta = false;
      }
    },

    async actualizarParcial() {
      this.limpiarMensajesTemporales();

      if (!this.id) {
        this.mensajesTemporales('Primero debe consultar una bodega para actualizar.', 'error');
        return;
      }
      if (!this.bodega.nombre && !this.bodega.ubicacion) {
        this.mensajesTemporales('Por favor, ingrese al menos el nombre o la ubicación para actualizar.', 'error');
        return;
      }
      if (!this.bodegaCargada) {
        this.mensajesTemporales('No hay bodega cargada para actualizar.', 'error');
        return;
      }

      const bodegaBody = {};
      for (const key in this.bodega) {
        if (key !== 'codigo') {
          bodegaBody[key] = this.bodega[key] === '' ? null : this.bodega[key];
        }
      }

      if (Object.keys(bodegaBody).length === 0 || (bodegaBody.nombre === null && bodegaBody.ubicacion === null)) {
        this.mensajesTemporales('No hay cambios para actualizar.', 'error');
        return;
      }

      try {
        this.deshabilitado = true;
        await actualizarBodegaFachada(this.id, bodegaBody);
        this.mensajesTemporales(`Bodega ${this.id} actualizada correctamente.`, 'success');
        console.log('Bodega actualizada:', bodegaBody);
      } catch (error) {
        console.error("Error al actualizar la bodega:", error);
        this.mensajesTemporales('Ocurrió un error al actualizar la bodega. Inténtalo de nuevo.', 'error');
      } finally {
        this.deshabilitado = false;
      }
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

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}


@media screen and (max-width: 768px) {

  .containerformulario h2 {
    font-size: 1.6em;
  }

  .mensaje {
    padding: 8px 15px;
    font-size: 0.9em;
  }
}

@media screen and (max-width: 480px) {

  .containerformulario h2 {
    font-size: 1.4em;
  }

  .mensaje {
    padding: 7px 10px;
    font-size: 0.85em;
  }
}
</style>