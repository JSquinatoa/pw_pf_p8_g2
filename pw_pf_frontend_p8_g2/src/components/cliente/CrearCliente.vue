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
    <div v-if="exito">
      <h1>Cliente Correctamente Guardado</h1>
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
    };
  },

  methods: {
    async guardarCliente() {
      const clienteToBody = {
        cedula: this.cliente.cedula,
        nombre: this.cliente.nombre,
        apellido: this.cliente.apellido,
        razonSocial: this.cliente.razonSocial,
        direccion: this.cliente.direccion,
        telefono: this.cliente.telefono,
        correo: this.cliente.correo,
      };
      if (!this.deshabilitado) {
        await guardarFachada(clienteToBody);
      }

      this.exito = true;
      this.deshabilitado = true;
      setTimeout(() => {
        this.exito = false;
        this.deshabilitado = false;
        this.reiniciarVaraibles();
      }, 3000);
    },

    reiniciarVaraibles() {
        this.cliente.cedula = null,
        this.cliente.nombre = null,
        this.cliente.apellido = null,
        this.cliente.razonSocial = null,
        this.cliente.direccion = null,
        this.cliente.telefono = null,
        this.cliente.correo = null;
    },
  },
};
</script>

<style>

</style>