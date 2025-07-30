<template>
  <div class="container_consultarcliente">
    <button class="boton_consulta" @click="rellenarTabla()">
      Consultar Todos los Impuestos
    </button>

    <table v-if="todosImpuestos" class="tabla-clientes">
      <thead>
        <tr>
          <th>Identificador</th>
          <th>Nombre</th>
          <th>Porcentaje</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="impuestos in todosImpuestos" :key="impuestos.id">
          <td>{{ impuestos.id }}</td>
          <td>{{ impuestos.nombre }}</td>
          <td>{{ impuestos.porcentaje }}</td>
        </tr>
      </tbody>
    </table>

    <h2 v-if="!todosImpuestos">
      Presiona el botón para consultar todos los Impuestos
    </h2>
  </div>
</template>

<script>
import { consultarTodosImpuestosFachada } from "@/clients/ImpuestoClient";
import "@/css/EstiloGenerico.css";

export default {
  data() {
    return {
      todosImpuestos: null,
    };
  },

  methods: {
    async rellenarTabla() {
      this.todosImpuestos = await consultarTodosImpuestosFachada();
      console.log(this.todosImpuestos);
    },
  },
};
</script>

<style>

</style>