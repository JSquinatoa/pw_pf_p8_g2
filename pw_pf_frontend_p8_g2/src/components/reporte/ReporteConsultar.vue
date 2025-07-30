<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <button class="boton_consulta" @click="rellenarTabla">
        Consultar Reportes
      </button>
    </div>

    <div v-if="todosReportes.length">
      <h1>Lista de Reportes</h1>
      <table class="tabla-clientes">
        <thead>
          <tr>
            <th>Número Documento</th>
            <th>ID Cliente</th>
            <th>Nombre Cliente</th>
            <th>Cantidad Items</th>
            <th>Total Impuestos</th>
            <th>Total</th>
            <th>Acción</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="r in todosReportes" :key="r.id">
            <td data-label="Número Documento">{{ r.numeroDocumento }}</td>
            <td data-label="ID Cliente">{{ r.cedulaCliente }}</td>
            <td data-label="Nombre Cliente">{{ r.nombreCliente }}</td>
            <td data-label="Cantidad Items">{{ r.cantidadItems }}</td>
            <td data-label="Total Impuestos">{{ r.totalImpuestos.toFixed(2) }}</td>
            <td data-label="Total">{{ r.total.toFixed(2) }}</td>
            <td data-label="Acción">
              <button class="boton_opcion" @click="openModal(r.id)">Ver</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <p v-else class="mensaje">No hay reportes. Presiona el botón para cargar.</p>

    <!-- Modal interno -->
    <div v-if="showModal" class="modal-overlay" @click.self="closeModal">
      <div class="modal-content">
        <button class="close-btn" @click="closeModal">✕</button>
        <div v-if="factura">
          <!-- Encabezado -->
          <div class="modal-header">
            <h2>Factura {{ factura.numeroDocumento }}</h2>
            <span class="header-date">{{ factura.fechaEmision }}</span>
          </div>

          <!-- Empresa y Cliente -->
          <div class="info-grid">
            <div class="info-card">
              <h3>Empresa</h3>
              <p><strong>RUC:</strong> {{ factura.rucEmpresa }}</p>
              <p><strong>Establecimiento:</strong> {{ factura.establecimiento }}</p>
              <p><strong>Punto Emisión:</strong> {{ factura.puntoEmision }}</p>
            </div>
            <div class="info-card">
              <h3>Cliente</h3>
              <p><strong>Cédula:</strong> {{ factura.cedulaCliente }}</p>
              <p><strong>Items:</strong> {{ factura.cantidadItems }}</p>
              <p><strong>Total:</strong> {{ factura.total.toFixed(2) }}</p>
            </div>
          </div>

          <!-- Detalle Productos -->
          <h3 class="section-title">Detalle de Productos</h3>
          <table class="detalle-table">
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Subtotal</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in detalles" :key="item.id">
                <td data-label="Código">{{ item.codigoBarras }}</td>
                <td data-label="Nombre">{{ item.nombre }}</td>
                <td data-label="Cantidad">{{ item.cantidad }}</td>
                <td data-label="Precio">{{ item.precio.toFixed(2) }}</td>
                <td data-label="Subtotal">{{ item.subTotal.toFixed(2) }}</td>
              </tr>
            </tbody>
          </table>

          <!-- Totales -->
          <div class="totales-grid">
            <div><strong>Subtotal:</strong> {{ factura.subTotal.toFixed(2) }}</div>
            <div><strong>Impuestos:</strong> {{ factura.totalImpuestos.toFixed(2) }}</div>
            <div><strong>Total:</strong> {{ factura.total.toFixed(2) }}</div>
          </div>
        </div>
        <div v-else>
          <p>Cargando factura...</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  consultarTodosReportesFachada,
  consultarFacturaCompletaFachada,
  consultarDetallesCompraFachada
} from '@/clients/ReporteClient.js';

export default {
  name: 'ReporteConsultar',
  data() {
    return {
      todosReportes: [],
      showModal: false,
      factura: null,
      detalles: []
    };
  },
  methods: {
    async rellenarTabla() {
      this.todosReportes = [];
      try {
        this.todosReportes = await consultarTodosReportesFachada();
      } catch (e) {
        console.error('Error al consultar reportes:', e);
      }
    },
    async openModal(id) {
      this.showModal = true;
      this.factura = null;
      this.detalles = [];
      try {
        this.factura = await consultarFacturaCompletaFachada(id);
        this.detalles = await consultarDetallesCompraFachada(id);
      } catch (e) {
        console.error('Error al cargar factura:', e);
      }
    },
    closeModal() {
      this.showModal = false;
    }
  },
  mounted() {
    this.rellenarTabla();
  }
};
</script>

<style scoped>
.container_consultarcliente {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  gap: 20px;
}

.container_consultar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 400px;
  margin-bottom: 30px;
  gap: 10px;
}

.boton_consulta {
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
  text-align: center;
}

.boton_opcion {
  width: 80px;
  padding: 8px 12px;
  border-radius: 8px;
  background-color: #09b162;
  color: #fff;
  font-weight: bold;
  border: none;
  font-family: "Segoe UI", Arial, sans-serif;
  font-size: 0.9em;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
  display: block;
  margin: 0 auto;
}

.boton_consulta:hover, .boton_opcion:hover {
  background-color: #217dbb;
  transform: scale(1.03);
}

.boton_consulta:active, .boton_opcion:active {
  background-color: #176093;
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

.mensaje {
  margin-top: 2rem;
  font-style: italic;
  color: #718096;
  text-align: center;
}

.tabla-clientes {
  width: 80%;
  margin-top: 20px;
  border-collapse: collapse;
  margin-left: auto;
  margin-right: auto;
}

.tabla-clientes th,
.tabla-clientes td {
  padding: 12px;
  text-align: left;
  border: 1px solid #ddd;
}

.tabla-clientes th {
  text-align: left;
  background-color: #07265c;
  color: #ddd;
  font-weight: bold;
}

.tabla-clientes tr:nth-child(even) {
  background-color: #f9f9f9;
}

.tabla-clientes td:last-child {
  text-align: center;
}

.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  max-width: 800px;
  width: 90%;
  max-height: 90%;
  overflow-y: auto;
  box-shadow: 0 8px 24px rgba(0,0,0,0.2);
  position: relative;
}

.close-btn {
  position: absolute;
  top: 1rem;
  right: 1rem;
  background: transparent;
  border: none;
  font-size: 1.2rem;
  cursor: pointer;
  color: #07265c;
}

.modal-header {
  text-align: center;
  margin-bottom: 1.5rem;
}

.modal-header h2 {
  margin: 0;
  font-size: 1.8rem;
  color: #2d3748;
}

.header-date {
  font-size: 1rem;
  color: #4a5568;
}

.info-grid {
  display: flex;
  gap: 2rem;
  margin-bottom: 1.5rem;
}

.info-card {
  flex: 1;
  background: #f7fafc;
  padding: 1rem;
  border: 1px solid #e2e8f0;
  border-radius: 6px;
}

.info-card h3 {
  margin-top: 0;
  color: #2d3748;
  margin-bottom: 0.5rem;
}

.section-title {
  margin: 1.5rem 0 0.5rem;
  color: #2d3748;
  font-size: 1.2rem;
}

.detalle-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 1.5rem;
}

.detalle-table th,
.detalle-table td {
  padding: 0.5rem;
  border: 1px solid #e2e8f0;
}

.detalle-table th {
  background: #edf2f7;
  color: #2d3748;
  text-transform: uppercase;
  font-size: 0.9rem;
}

.detalle-table td {
  font-size: 0.9rem;
  color: #4a5568;
}

.totales-grid {
  display: flex;
  justify-content: flex-end;
  gap: 1.5rem;
}

.totales-grid div {
  background: #edf2f7;
  padding: 0.75rem 1rem;
  border-radius: 6px;
  font-weight: bold;
}

@media (max-width: 700px) {
  .container_consultar {
    flex-direction: column;
    width: 100%;
    gap: 15px;
  }

  .boton_consulta, .boton_opcion {
    width: 100%;
    max-width: none;
    padding: 12px 16px;
    font-size: 1.1em;
  }

  .tabla-clientes {
    width: 100%;
  }

  .tabla-clientes thead {
    display: none;
  }

  .tabla-clientes tr {
    display: flex;
    flex-direction: column;
    border: 1px solid #ddd;
    margin-bottom: 25px;
    border-radius: 5px;
    overflow: hidden;
  }

  .tabla-clientes td {
    display: flex;
    padding: 8px;
    border-bottom: 1px solid #ddd;
    text-align: right;
    justify-content: space-between;
  }

  .tabla-clientes td::before {
    content: attr(data-label);
    font-weight: bold;
    background-color: #07265c;
    color: white;
    padding: 8px;
    display: inline-block;
    margin-right: 10px;
    border-radius: 5px 0 0 5px;
  }

  .tabla-clientes td:last-child {
    border-bottom: none;
    justify-content: flex-end;
  }

  .boton_opcion {
    margin-left: auto;
    width: auto;
  }

  .info-grid {
    flex-direction: column;
    gap: 1rem;
  }

  .modal-content {
    padding: 1rem;
  }
}

@media (max-width: 400px) {
  .detalle-table td::before {
    min-width: 80px;
  }

  .totales-grid {
    flex-direction: column;
    gap: 0.5rem;
  }
}
</style>