<template>
  <div class="container_consultarcliente">
    <h1>Listado de Bodegas</h1>
    <button class="boton_consulta" @click="consultarBodegas()">
      Consultar Todas las Bodegas
    </button>

    <transition name="fade">
      <div v-if="temporarymensaje" :class="['mensaje', mensajeType]">
        {{ temporarymensaje }}
      </div>
    </transition>

    <div class="tabla-clientes" v-if="bodegas && bodegas.length > 0">
      <table>
        <thead>
          <tr>
            <th>Identificación</th>
            <th>Código</th>
            <th>Nombre</th>
            <th>Ubicación</th>
            <th>Productos y Stock</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="bodega in bodegas" :key="bodega.id">
            <td data-label="Identificación">{{ bodega.id }}</td>
            <td data-label="Código">{{ bodega.codigo }}</td>
            <td data-label="Nombre">{{ bodega.nombre }}</td>
            <td data-label="Ubicación">{{ bodega.ubicacion }}</td>
            <td data-label="Productos y Stock" class="td-boton-ver">
              <button class="boton-ver-productos" @click="showProductsModal(bodega.codigo, bodega.nombre)">
                Ver
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <h2 v-if="loading">Cargando bodegas...</h2>
    <h2 v-else-if="!bodegas || bodegas.length === 0">
      Presiona el botón para consultar todas las bodegas.
    </h2>

    <ModalProductosBodega
      v-if="isModalVisible"
      :bodegaCodigo="selectedBodegaCodigo"
      :bodegaNombre="selectedBodegaNombre"
      @close="closeModal"
    />
  </div>
</template>

<script>
import { consultarBodegasFachada } from "@/clients/BodegaClient";
import ModalProductosBodega from "@/components/bodega/ModalProductosBodega.vue";
import "@/css/EstiloGenerico.css";
export default {
  name: 'BodegaConsultar',
  components: {
    ModalProductosBodega
  },
  data() {
    return {
      bodegas: null,
      loading: false,
      temporarymensaje: null,
      mensajeType: '',
      mensajeTimeout: null,
      isModalVisible: false,
      selectedBodegaCodigo: null,
      selectedBodegaNombre: null
    };
  },
  methods: {
    async consultarBodegas() {
      this.limpiarMensajesTemporales();
      this.loading = true;
      this.bodegas = null;

      try {
        const fetchedBodegas = await consultarBodegasFachada();
        if (fetchedBodegas && fetchedBodegas.length > 0) {
          this.bodegas = fetchedBodegas;
          this.mensajesTemporales('Bodegas cargadas exitosamente.', 'success');
        } else {
          this.bodegas = [];
          this.mensajesTemporales('No hay bodegas registradas.', 'info');
        }
      } catch (error) {
        console.error("Error al consultar bodegas:", error);
        this.mensajesTemporales('Ocurrió un error al cargar las bodegas. Inténtalo de nuevo.', 'error');
        this.bodegas = [];
      } finally {
        this.loading = false;
      }
    },

    showProductsModal(codigo, nombre) {
      this.selectedBodegaCodigo = codigo;
      this.selectedBodegaNombre = nombre;
      this.isModalVisible = true;
    },

    closeModal() {
      this.isModalVisible = false;
      this.selectedBodegaCodigo = null;
      this.selectedBodegaNombre = null;
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
};
</script>

<style scoped>

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


table {
  width: 90%;
  margin: 0 auto;
  border-collapse: collapse;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

th, td {
  padding: 15px; 
  text-align: left;
  border-bottom: 1px solid #e0e0e0;
}

th {
  background-color: #07265c;
  color: #fff;
  font-weight: bold;
  text-transform: uppercase;
  font-size: 0.9em;
}

tr:nth-child(even) {
  background-color: #f8f8f8; 
}

tr:hover {
  background-color: #f0f0f0; 
}

.td-boton-ver {
  text-align: center;
}

.boton-ver-productos {
  padding: 8px 15px;
  border-radius: 5px;
  background-color: #07265c;
  color: #fff;
  border: none;
  font-size: 0.9em;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
}

.boton-ver-productos:hover {
  background-color: #217dbb;
  transform: scale(1.05);
}


</style>