<template>
  <div class="container-bconsultar">
    <h1>Listado de Bodegas</h1>
    <button class="boton_consultar" @click="consultarBodegas()">
      Consultar Todas las Bodegas
    </button>

    <transition name="fade">
      <div v-if="temporarymensaje" :class="['mensaje', mensajeType]">
        {{ temporarymensaje }}
      </div>
    </transition>

    <div class="table-responsive" v-if="bodegas && bodegas.length > 0">
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
            <td>{{ bodega.id }}</td>
            <td>{{ bodega.codigo }}</td>
            <td>{{ bodega.nombre }}</td>
            <td>{{ bodega.ubicacion }}</td>
            <td>
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
      Presiona el botón para consultar todas las bodegas. No hay bodegas registradas.
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
.container-bconsultar {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  padding: 20px;
  min-height: 50vh;
  box-sizing: border-box;
  gap: 20px; /* Agrega espacio entre elementos */
}

h1 {
  color: #003366;
  margin-bottom: 15px;
  font-size: 2.2em;
  text-align: center;
}

.boton_consultar {
  width: 40%;
  max-width: 350px; /* Aumenta el max-width para que sea un poco más ancho */
  padding: 12px 20px; /* Ajusta el padding para mejor aspecto */
  font-size: 1.4em; /* Un poco más grande para el texto */
  font-weight: bold;
  letter-spacing: 1px;
  text-align: center;
  background-color: #0056b3; /* Color azul más estándar */
  color: white;
  border: none;
  border-radius: 8px; /* Bordes más redondeados */
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
  margin-bottom: 25px; /* Más espacio debajo del botón */
}

.boton_consultar:hover {
  background-color: #004080;
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Sombra al pasar el mouse */
}

.boton_consultar:active {
  background-color: #002c5c;
  transform: translateY(0);
  box-shadow: none;
}

.table-responsive {
  width: 90%;
  max-width: 900px;
  overflow-x: auto;
  max-height: 60vh;
  overflow-y: auto;
  margin-top: 1rem;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); /* Sombra más pronunciada */
  border-radius: 8px;
  background-color: white;
}

table {
  width: 100%;
  min-width: 700px; /* Asegura un mínimo de ancho para la tabla */
  border-collapse: collapse;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; /* Fuente más moderna */
  font-size: 0.95rem;
}

h2 {
  margin-top: 30px; /* Ajuste de margen */
  color: #555; /* Color de texto más suave */
  font-size: 1.5em; /* Tamaño de fuente para los mensajes */
  text-align: center;
  padding: 0 10px;
}

thead {
  background-color: #003366;
  color: #ffffff;
}

thead th {
  padding: 12px 15px; /* Aumenta el padding */
  text-align: center;
  font-weight: bold;
  border-bottom: 1px solid #4a6fa3; /* Borde más sutil */
  border-right: 1px solid #4a6fa3;
  text-transform: uppercase;
  font-size: 0.9em; /* Un poco más pequeño para encabezados */
}

thead th:last-child {
  border-right: none;
}

thead th:hover {
  background-color: #07294a;
}

tbody tr {
  border-bottom: 1px solid #e0e0e0;
  transition: background-color 0.3s ease;
}

tbody tr:last-child {
  border-bottom: none;
}

tbody tr:hover {
  background-color: #f6f6f6;
}

tbody td {
  padding: 10px 15px; 
  border-left: 1px solid #e0e0e0;
  border-right: 1px solid #e0e0e0;
  color: #333;
  text-align: center;
  vertical-align: middle;
}

tbody td:first-child {
  border-left: none;
}

tbody td:last-child {
  border-right: none;
}

.boton-ver-productos {
  padding: 8px 15px;
  font-size: 0.9em;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease;
  min-width: 80px;
}

.boton-ver-productos:hover {
  background-color: #218838;
  transform: translateY(-1px);
}

.boton-ver-productos:active {
  background-color: #1e7e34;
  transform: translateY(0);
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
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

.mensaje.info { 
  background-color: #e2f0fb;
  color: #0c5460;
  border: 1px solid #bee5eb;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

@media screen and (max-width: 768px) {
  .container-bconsultar {
    padding: 15px;
    gap: 15px;
  }
  h1 {
    font-size: 1.8em;
    margin-bottom: 10px;
  }
  .boton_consultar {
    width: 60%;
    font-size: 1.2em;
    padding: 10px 15px;
    margin-bottom: 20px;
  }
  .table-responsive {
    width: 100%;
    max-width: none;
    max-height: 70vh;
  }
  table {
    min-width: 600px;
    font-size: 0.85rem;
  }
  thead th,
  tbody td {
    padding: 8px 10px;
    font-size: 0.9em;
  }
  h2 {
    font-size: 1.3em;
    margin-top: 20px;
  }
  .boton-ver-productos {
    padding: 6px 12px;
    font-size: 0.85em;
    min-width: 70px;
  }
  .mensaje {
    padding: 8px 15px;
    font-size: 0.9em;
  }
}

@media screen and (max-width: 480px) {
  .container-bconsultar {
    padding: 10px;
    gap: 10px;
  }
  h1 {
    font-size: 1.5em;
    margin-bottom: 8px;
  }
  .boton_consultar {
    width: 80%;
    font-size: 1em;
    padding: 8px 10px;
    margin-bottom: 15px;
  }
  .table-responsive {
    max-height: 75vh;
  }
  table {
    min-width: 450px; 
    font-size: 0.75rem;
  }
  thead th,
  tbody td {
    padding: 6px 8px;
    font-size: 0.8em;
  }
  h2 {
    font-size: 1.1em;
    margin-top: 15px;
  }
  .boton-ver-productos {
    padding: 5px 10px;
    font-size: 0.8em;
    min-width: 60px;
  }
  .mensaje {
    padding: 7px 10px;
    font-size: 0.85em;
  }
}
</style>