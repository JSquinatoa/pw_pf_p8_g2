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
    <h1>Actualizar Cliente</h1>
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
    <button class="boton_opcion" @click="actulizarParcial()">
      Actualizar
    </button>
    <div v-if="!existeCliente">
      <h1>El cliente con la cedula {{ identificador }} no existe</h1>
    </div>
    <div v-if="exitoActulizar">
      <h1>
        El cliente con la cedula {{ identificador }} Se actulizo Correctamente
      </h1>
    </div>
  </div>
</template>

<script>
import { consultarClientePorIdFachada, actulizarParcialPorIdFachada } from "@/clients/ClienteClient.js";
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
      existeCliente: true,
      exitoActulizar: false,
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

    async actulizarParcial() {
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
        await actulizarParcialPorIdFachada(
          clienteToBody,
          this.identificador
        );
      }
      this.deshabilitado = true;
      this.exitoActulizar = true
      setTimeout(() => {
        this.exitoActulizar = false;
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