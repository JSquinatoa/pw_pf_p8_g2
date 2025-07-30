<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <input
        class="input_consulta"
        type="number"
        v-model="identificador"
        placeholder="Ingrese la cédula del cliente"
        :disabled="deshabilitado"
      />
      <button class="boton_consulta" @click="ObtenerClientePorId()">
        Consultar
      </button>
    </div>
    <h1>Datos del Cliente</h1>
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
    <div v-if="!existeCliente">
      <h1>El cliente con la cedula {{ identificador }} no existe</h1>
    </div>
  </div>

</template>

<script>
import { consultarClientePorIdFachada } from "@/clients/ClienteClient.js";
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
      clientes:[],
      existeCliente: true,
      deshabilitado: false,
    };
  },

 methods: {
  async ObtenerClientePorId() {
    try {
      let aux = await consultarClientePorIdFachada(this.identificador);
      if (!aux) {
        this.existeCliente = false;
        setTimeout(() => {
          this.existeCliente = true;
        }, 3000);
        return;
      }
      this.cliente.cedula = aux.cedula;
      this.cliente.nombre = aux.nombre;
      this.cliente.apellido = aux.apellido;
      this.cliente.razonSocial = aux.razonSocial;
      this.cliente.direccion = aux.direccion;
      this.cliente.telefono = aux.telefono;
      this.cliente.correo = aux.correo;
    } catch (error) {
      if (error.response && error.response.status === 404) {
        this.existeCliente = false;
        setTimeout(() => {
          this.existeCliente = true;
        }, 3000);
      }  
    }
  },
  },
};

</script>
<style>

</style>