<template>
  <div class="container_bcrear">
    <h1>Crear una nueva Bodega</h1>
    <div class="form-container-ente">
      <div class="form-elementos-contenido">
        <p type="Código:">
          <input type="number" v-model="bodega.codigo" :disabled="deshabilitado" placeholder="Ingrese el código"/>
        </p>
        <p type="Nombre:">
          <input type="text" v-model="bodega.nombre" :disabled="deshabilitado" placeholder="Ingrese el nombre"/>
        </p>
        <p type="Ubicación:">
          <input type="text" v-model="bodega.ubicacion" :disabled="deshabilitado" placeholder="Ingrese la ubicación"/>
        </p>
      </div>
    </div>
    <button class="boton_crear" @click="guardarBodega()">
      Crear
    </button>

    <transition name="fade">
      <div v-if="temporarymensaje" :class="['mensaje', mensajeType]">
        {{ temporarymensaje }}
      </div>
    </transition>

    <div v-if="exito" class="mensajes-exito">
      <h1>Bodega exitosamente Creada</h1>
      <button @click="resetForm()" class="boton_reset">Crear Otra Bodega</button>
    </div>
  </div>
</template>

<script>
import { insertarBodegaFachada } from "@/clients/BodegaClient";

export default {
    data() {
        return {
            id: null,
            bodega: {
                codigo: "",
                nombre: "",
                ubicacion: ""
            },
            exito: false,
            deshabilitado: false,
            temporarymensaje: null,
            mensajeType: '',
            mensajeTimeout: null,
        }
    },
    methods: {
        async guardarBodega() {
            this.limpiarMensajesTemporales();
            try {
                if (!this.bodega.codigo || !this.bodega.nombre || !this.bodega.ubicacion) {
                    this.mensajesTemporales('Por favor, complete todos los campos.', 'error');
                    return;
                }
                this.deshabilitado = true;
                await insertarBodegaFachada(this.bodega);
                this.exito = true;
                console.log("Bodega creada:", this.bodega);
            } catch (error) {
                console.error("Error al crear la bodega:", error);
                this.mensajesTemporales('Ocurrió un error al crear la bodega. Inténtalo de nuevo.', 'error');
                this.exito = false;
                this.deshabilitado = false;
            }
        },
        resetForm() {
            this.limpiarMensajesTemporales();
            this.bodega = {
                codigo: "",
                nombre: "",
                ubicacion: ""
            };
            this.exito = false;
            this.deshabilitado = false;
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
}
</script>

<style scoped>
.container_bcrear {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  gap: 20px;
}

h1 {
  font-size: 1.3em;
  color: #2c3e50;
  margin-bottom: 18px;
  margin-top: 0;
  font-weight: 600;
  letter-spacing: 1px;
  text-align: center;
}

.form-container-ente {
  display: flex;
  flex-direction: column;
  gap: 18px;
  width: 100%;
  max-width: 420px;
  background: #fff;
  border-radius: 18px;
  border: 10px double #003366;
  box-shadow: 0 4px 24px rgba(44, 62, 80, 0.08);
  padding: 32px 28px;
  box-sizing: border-box;
  background-color: #f4f6f8;
}

.form-elementos-contenido {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

p[type] {
  margin: 0;
  text-align: left;
  font-weight: 500;
  color: #34495e;
  font-size: 1em;
  display: flex;
  flex-direction: column;
  width: 100%;
}

p[type]::before {
  text-align: left;
  content: attr(type);
  font-size: 0.95em;
  color: #7f8c8d;
  margin-bottom: 4px;
  font-weight: 400;
}

.form-container-ente input {
  padding: 9px 12px;
  border: 1px solid #b0c4de;
  border-radius: 7px;
  font-size: 1em;
  background: #f8fafc;
  transition: border-color 0.2s;
  width: 100%;
  box-sizing: border-box;
}

.form-container-ente input:focus {
  border-color: #3498db;
  background: #fff;
  outline: none;
}

p[type] input:disabled {
  background-color: #e9ecef;
  cursor: not-allowed;
  opacity: 0.7;
}

.boton_crear {
  width: 90%;
  max-width: 250px;
  padding: 10px 24px;
  border-radius: 8px;
  background-color: #07265c;
  color: #fff;
  font-weight: bold;
  border: none;
  font-family: "Segoe UI", Arial, sans-serif;
  font-size: 1em;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 8px rgba(52, 152, 219, 0.08);
  margin-top: 20px;
}

.boton_crear:hover {
  background-color: #217dbb;
  transform: scale(1.03);
}

.boton_crear:active {
  background-color: #176093;
  transform: translateY(0);
  box-shadow: none;
}

.mensajes-exito {
  margin-top: 30px;
  text-align: center;
  padding: 20px;
  background-color: #cedeee;
  color: #002244;
  border: 1px solid #cedeee;
  border-radius: 8px;
  width: 90%;
  max-width: 400px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.mensajes-exito h1 {
  font-size: 1.8em;
  color: #003366;
  margin-bottom: 15px;
}

.boton_reset {
  background-color: #0056b3;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s ease;
}

.boton_reset:hover {
  background-color: #004080;
}

.mensaje {
  padding: 12px 20px;
  border-radius: 8px;
  margin-top: 15px;
  font-weight: bold;
  text-align: center;
  width: 90%;
  max-width: 400px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.mensaje.success {
  background-color: #e6ffe6;
  color: #28a745;
  border: 1px solid #28a745;
}

.mensaje.error {
  background-color: #ffe6e6;
  color: #dc3545;
  border: 1px solid #dc3545;
}

.mensaje.info {
  background-color: #e6f7ff;
  color: #007bff;
  border: 1px solid #007bff;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s ease-in-out;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>