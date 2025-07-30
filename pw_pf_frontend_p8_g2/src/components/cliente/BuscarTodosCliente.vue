<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <button class="boton_consulta" @click="obtenerTodosClientes()">
        Consultar todos
      </button>
    </div>
       <!-- Tabla de todos los clientes -->
    <div v-if="clientes.length > 0">
      <h1>Lista de Todos los Clientes</h1>
      <table class="tabla-clientes">
        <thead>
          <tr>
            <th>Cédula</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Razón Social</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Correo</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(cliente, index) in clientes" :key="index">
            <td data-label="Cédula">{{ cliente.cedula }}</td>
            <td data-label="Nombre">{{ cliente.nombre }}</td>
            <td data-label="Apellido">{{ cliente.apellido }}</td>
            <td data-label="Razón Social">{{ cliente.razonSocial }}</td>
            <td data-label="Dirección">{{ cliente.direccion }}</td>
            <td data-label="Teléfono">{{ cliente.telefono }}</td>
            <td data-label="Correo">{{ cliente.correo }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    
    <p v-else class="mensaje">Presiona el botón para cargar los clientes, si ya los tiene agregados.</p>
  </div>

</template>

<script>
import { consultarTodosClientesFachada } from "@/clients/ClienteClient.js";
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
  async obtenerTodosClientes() {
    try {
      const clientes = await consultarTodosClientesFachada(); 
      if (clientes && Array.isArray(clientes)) {
        this.clientes = clientes.map(cliente => ({
          cedula: cliente.cedula,
          nombre: cliente.nombre,
          apellido: cliente.apellido,
          razonSocial: cliente.razonSocial,
          direccion: cliente.direccion,
          telefono: cliente.telefono,
          correo: cliente.correo,
        }));
      }
    } catch (error) {
      console.error('Error al obtener todos los clientes:', error);
    }
  }
  },
};

</script>

<style>
</style>