<template>
  <div class="reporte-consultar">
    <button class="btn-consultar" @click="rellenarTabla">
      Consultar Reportes
    </button>

    <table v-if="todosReportes && todosReportes.length">
      <thead>
        <tr>
          <th>Número Documento</th>
          <th>ID Cliente</th>
          <th>Nombre Cliente</th>
          <th>Cantidad Items</th>
          <th>Total Impuestos</th>
          <th>Total</th>
          <th>Accion</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="r in todosReportes" :key="r.numDocu">
          <td>{{ r.numDocu }}</td>
          <td>{{ r.idCli }}</td>
          <td>{{ r.nombreCliente }}</td>
          <td>{{ r.cantidadItems }}</td>
          <td>{{ r.totalImpuestos.toFixed(2) }}</td>
          <td>{{ r.total.toFixed(2) }}</td>
          <td>
            <button class="btn-ver" @click.prevent="verReporte(r.numDocu)">
              Ver PDF
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-else class="mensaje">
      No hay reportes. Presiona el botón para cargar.
    </p>
  </div>
</template>

<script>
import { consultarTodosReportesFachada } from "@/clients/ReporteClient.js";

export default {
  name: "ReporteConsultar",
  data() {
    return {
      todosReportes: [],
    };
  },
  methods: {
    async rellenarTabla() {
      try {
        this.todosReportes = await consultarTodosReportesFachada();
      } catch (e) {
        console.error("Error al consultar reportes:", e);
      }
    },
    verReporte(numDocu) {
      // Lógica para visualizar o descargar el PDF del reporte
      console.log(`Ver reporte PDF con numDocu=${numDocu}`);
    },
  },
  mounted() {
    this.rellenarTabla();
  },
};
</script>

<style scoped>
.reporte-consultar {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.btn-consultar {
  margin-bottom: 1rem;
  padding: 0.75rem 2rem;
  font-size: 1.1rem;
  background: #003366;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.2s;
}

.btn-consultar:hover {
  background: #041a3d;
}

.btn-ver {
  padding: 0.4rem 1rem;
  font-size: 0.9rem;
  background: #4caf50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.2s;
}

.btn-ver:hover {
  background: #45a049;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: #fff;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
  border-radius: 6px;
  overflow: hidden;
}

thead {
  background: #003366;
  color: #fff;
}

thead th {
  padding: 0.75rem;
  text-align: center;
  font-weight: 600;
}

tbody tr {
  border-bottom: 1px solid #e0e0e0;
}

tbody tr:hover {
  background: #f2f2f2;
}

tbody td {
  padding: 0.75rem;
  text-align: center;
}

.mensaje {
  margin-top: 2rem;
  color: #666;
  font-style: italic;
}
</style>
