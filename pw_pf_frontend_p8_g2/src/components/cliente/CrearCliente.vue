<template>
  <div class="container_consultarcliente">
    <h1>Crear un nuevo Cliente</h1>
    <div class="containerformulario">
      <p type="Cédula:">
        <input type="number" v-model="cliente.cedula" :disabled="deshabilitado"/>
      </p>
      <p type="Nombre:">
        <input type="text" v-model="cliente.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Apellido:">
        <input type="text" v-model="cliente.apellido" :disabled="deshabilitado"/>
      </p>
      <p type="Razón Social:">
        <input type="text" v-model="cliente.razonSocial" :disabled="deshabilitado"/>
      </p>
      <p type="Dirección:">
        <input type="text" v-model="cliente.direccion" :disabled="deshabilitado"/>
      </p>
      <p type="Teléfono:">
        <input type="number" v-model="cliente.telefono" :disabled="deshabilitado"/>
      </p>
      <p type="Correo electrónico:">
        <input type="email" v-model="cliente.correo" :disabled="deshabilitado"/>
      </p>
    </div>
    <button class="boton_opcion" @click="guardarCliente()">
      Crear
    </button>
    <div v-if="exito" class="mensajes-exito">
      <h1>Cliente Correctamente Guardado</h1>
    </div>
    <div v-if="error" class="mensajes-error">
      <h1>Datos incompletos en el formulario</h1>
    </div>
  </div>
</template>

<script>
import { guardarFachada } from "@/clients/ClienteClient.js";
import "@/css/EstiloGenerico.css";

export default {
  data() {
    return {
      identificador: null,
      cliente: {
        cedula: null,
        nombre: null,
        apellido: null,
        razonSocial: null,
        direccion: null,
        telefono: null,
        correo: null,        
      },
      exito: false,
      deshabilitado: false,
      error: false
    };
  },

  methods: {
    async guardarCliente() {
      // Validar si algún campo está vacío
      if (!this.cliente.cedula || !this.cliente.nombre || !this.cliente.apellido || 
        !this.cliente.razonSocial || !this.cliente.direccion || !this.cliente.telefono || !this.cliente.correo) {
        this.error = true; 
        this.limpiarError();
        return;  
        
      }
      // Si los campos están completos, crear el objeto cliente para enviar
      const clienteToBody = {
        cedula: this.cliente.cedula,
        nombre: this.cliente.nombre,
        apellido: this.cliente.apellido,
        razonSocial: this.cliente.razonSocial,
        direccion: this.cliente.direccion,
        telefono: this.cliente.telefono,
        correo: this.cliente.correo,
      };

      try {
        if (!this.deshabilitado) {
          await guardarFachada(clienteToBody);
        }
        this.exito = true;
        this.deshabilitado = true;
        this.limpiarMensaje();
      } catch (error) {
        console.error("Error al guardar el cliente:", error);
        this.error = true;
      }
    },

    // Método para reiniciar las variables
    reiniciarVariables() {
      this.cliente.cedula = null;
      this.cliente.nombre = null;
      this.cliente.apellido = null;
      this.cliente.razonSocial = null;
      this.cliente.direccion = null;
      this.cliente.telefono = null;
      this.cliente.correo = null;
    },
    limpiarError(){
      setTimeout(() => {
          this.error = false;
          this.deshabilitado = false;
          this.reiniciarVariables();
        }, 3000)
    },
    limpiarMensaje(){
      setTimeout(() => {
          this.exito = false;
          this.deshabilitado = false;
          this.reiniciarVariables();
        }, 3000)
    }
  },
};
</script>

<style>

</style>