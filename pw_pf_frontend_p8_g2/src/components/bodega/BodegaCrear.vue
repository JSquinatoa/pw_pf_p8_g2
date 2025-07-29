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


.container_bcrear h1 {
  color: #003366;
  margin-bottom: 25px;
  font-size: 2.2em;
  text-align: center;
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
}

.form-elementos-contenido {
  display: flex;
  flex-direction: column;
  gap: 15px; 
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

.boton_crear {
  width: 90%;
  max-width: 250px;
  padding: 12px 25px;
  font-size: 1.4em;
  font-weight: bold;
  letter-spacing: 2px;
  background-color: #07265c;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
  margin-top: 20px;
}

.boton_crear:hover {
  background-color: #217dbb;
  transform: translateY(-2px);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.boton_crear:active {
  background-color: #217dbb;
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
  color: #fff;
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

/* Vue Transition Styles */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}


@media screen and (max-width: 768px) {
  .container_bcrear h1 {
    font-size: 1.8em;
  }
  .form-container-ente {
    width: 95%;
    max-width: 450px;
    padding: 30px 20px; 
  }
  p[type]::before { 
    font-size: 1em;
  }
  p[type] input { 
    padding: 10px;
    font-size: 0.95em;
  }
  .boton_crear {
    padding: 10px 20px;
    font-size: 1.2em;
    max-width: 200px;
  }
  .mensajes-exito h1 {
    font-size: 1.5em;
  }
  .mensaje {
    padding: 8px 15px;
    font-size: 0.9em;
  }
}

@media screen and (max-width: 480px) {
  .container_bcrear h1 {
    font-size: 1.5em;
    margin-bottom: 15px;
  }
  .form-container-ente {
    width: 100%;
    padding: 15px;
    border: 5px double #003366;
    border-end-end-radius: 50px;
    border-start-start-radius: 50px;
  }
  p[type]::before {
    font-size: 0.9em;
  }
  p[type] input {
    padding: 8px;
    font-size: 0.9em;
  }
  .boton_crear {
    padding: 8px 15px;
    font-size: 1em;
    max-width: 180px;
  }
  .mensajes-exito {
    padding: 15px;
    width: 100%;
  }
  .mensajes-exito h1 {
    font-size: 1.3em;
  }
  .mensaje {
    padding: 7px 10px;
    font-size: 0.85em;
  }
}
</style>