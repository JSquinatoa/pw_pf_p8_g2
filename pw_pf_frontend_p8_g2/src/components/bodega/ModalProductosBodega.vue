<template>
  <transition name="modal-fade">
    <div class="modal-backdrop" @click.self="close">
      <div class="modal-content" role="dialog" aria-labelledby="modalTitle" aria-describedby="modalDescription">
        <header class="modal-header" id="modalTitle">
          <h3>Productos en Bodega: {{ bodegaNombre }}</h3>
          <button type="button" class="btn-close" @click="close" aria-label="Cerrar modal">x</button>
        </header>

        <section class="modal-body" id="modalDescription">
          <div v-if="loading" class="loading-spinner">Cargando productos...</div>
          <div v-else-if="error" class="error-message">Error al cargar los productos: {{ error }}</div>
          <div v-else-if="productos.length === 0" class="no-products">No hay productos asociados a esta bodega.</div>
          <div v-else class="productos-list">
            <table>
              <thead>
                <tr>
                  <th>Código de Barras</th>
                  <th>Nombre Producto</th>
                  <th>Stock</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="producto in productos" :key="producto.codigoBarras">
                  <td>{{ producto.codigoBarras }}</td>
                  <td>{{ producto.nombreProducto }}</td>
                  <td>{{ producto.stock }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>

        <footer class="modal-footer">
          <button type="button" class="btn-primary" @click="close">Cerrar</button>
        </footer>
      </div>
    </div>
  </transition>
</template>

<script>
import { obtenerProductosPorBodegaFachada } from '@/clients/BodegaClient';

export default {
  name: 'ModalProductosBodega',
  props: {
    bodegaCodigo: {
      type: String,
      required: true
    },
    bodegaNombre: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      productos: [],
      loading: false,
      error: null
    };
  },
  watch: {},
  async created() {
    await this.fetchProductos();
  },
  methods: {
    close() {
      this.$emit('close');
    },
    async fetchProductos() {
      this.loading = true;
      this.error = null;
      this.productos = [];

      try {
        const data = await obtenerProductosPorBodegaFachada(this.bodegaCodigo);
        this.productos = data || [];
      } catch (err) {
        this.error = 'No se pudieron cargar los productos. Verifique la conexión o el código de la bodega.';
        console.error('Detalle del error al obtener productos:', err);
      } finally {
        this.loading = false;
      }
    }
  }
};
</script>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.65);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: #fcfcfc;
  border-radius: 12px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  width: 90%;
  max-width: 700px;
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.35);
  animation: scaleIn 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.modal-header {
  padding: 20px 25px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #e0e0e0;
  background-color: #004d99;
  color: white;
}

.modal-header h3 {
  margin: 0;
  font-size: 1.8em;
  font-weight: 600;
}

.btn-close {
  border: none;
  font-size: 1.8em;
  padding: 0 10px;
  cursor: pointer;
  font-weight: bold;
  color: white;
  background: transparent;
  transition: color 0.2s ease, transform 0.2s ease;
}

.btn-close:hover {
  color: #ff8080;
  transform: rotate(90deg);
}

.modal-body {
  padding: 25px;
  flex-grow: 1;
  overflow-y: auto;
  min-height: 120px;
  max-height: 450px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.loading-spinner, .error-message, .no-products {
  text-align: center;
  padding: 20px;
  font-size: 1.2em;
  color: #666;
}

.error-message {
  color: #cc0000;
  font-weight: bold;
}

.no-products {
  color: #888;
  font-style: italic;
}

.productos-list {
  width: 100%;
}

.productos-list table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 15px;
}

.productos-list th, .productos-list td {
  border: 1px solid #e0e0e0;
  padding: 12px 18px;
  text-align: left;
  font-size: 0.95em;
}

.productos-list th {
  background-color: #e9eff5;
  font-weight: 700;
  color: #333;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.productos-list tr:nth-child(even) {
  background-color: #f9fbfd;
}

.productos-list tr:hover {
  background-color: #eef2f6;
}

.modal-footer {
  padding: 15px 25px;
  border-top: 1px solid #e0e0e0;
  display: flex;
  justify-content: flex-end;
  background-color: #004d99;
}

.btn-primary {
  padding: 10px 22px;
  font-size: 1.1em;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.1s ease, box-shadow 0.2s ease;
}

.btn-primary:hover {
  background-color: #0056b3;
  transform: translateY(-1px);
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.btn-primary:active {
  background-color: #004080;
  transform: translateY(0);
  box-shadow: none;
}

.modal-fade-enter-active, .modal-fade-leave-active {
  transition: opacity 0.3s ease;
}
.modal-fade-enter-from, .modal-fade-leave-to {
  opacity: 0;
}

@keyframes scaleIn {
  from {
    transform: scale(0.95);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}

@media (max-width: 768px) {
  .modal-content {
    max-width: 95%;
  }
  .modal-header {
    padding: 15px 20px;
  }
  .modal-header h3 {
    font-size: 1.5em;
  }
  .btn-close {
    font-size: 1.6em;
  }
  .modal-body {
    padding: 20px;
    max-height: 350px;
  }
  .productos-list th, .productos-list td {
    padding: 10px 15px;
    font-size: 0.9em;
  }
  .btn-primary {
    padding: 9px 18px;
    font-size: 1em;
  }
}

@media (max-width: 480px) {
  .modal-header h3 {
    font-size: 1.3em;
  }
  .btn-close {
    font-size: 1.4em;
  }
  .modal-body {
    padding: 15px;
    max-height: 300px;
  }
  .productos-list th, .productos-list td {
    padding: 8px 12px;
    font-size: 0.8em;
  }
  .btn-primary {
    padding: 8px 15px;
    font-size: 0.9em;
  }
}
</style>