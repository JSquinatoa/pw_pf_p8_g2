<template>
  <div class="container-factura">
    <div class="factura">
      <div class="factura-header">
        <h1>FACTURA</h1>
        <input type="date" v-model="factura.fecha" disabled />
      </div>

      <div class="cabecera">
        <h3>Empresa:</h3>
        <div class="empresa">
          <p type="RUC:">
            <input type="text" v-model="factura.empresa.ruc" />
            <span v-if="errores.ruc" class="error-msg">Campo obligatorio</span>
          </p>
          <p type="Establecimiento:">
            <input type="text" v-model="factura.empresa.establecimiento" />
            <span v-if="errores.establecimiento" class="error-msg">Campo obligatorio</span>
          </p>
          <p type="Número del documento:">
            <input type="text" v-model="factura.empresa.numeroDocumento" />
            <span v-if="errores.numeroDocumento" class="error-msg">Campo obligatorio</span>
          </p>
          <p type="Punto de emisión:">
            <input type="text" v-model="factura.empresa.puntoEmision" />
            <span v-if="errores.puntoEmision" class="error-msg">Campo obligatorio</span>
          </p>
        </div>

        <h3>Cliente:</h3>
        <div class="cliente">
          <p type="ID Cliente:">
            <input type="text" v-model="factura.cliente.idCliente" />
            <span v-if="errores.idCliente" class="error-msg">Campo obligatorio</span>
          </p>
          <p type="Nombre del Cliente:">
            <input type="text" v-model="factura.cliente.nombreCliente" disabled />
          </p>
          <p type="Dirección:">
            <input type="text" v-model="factura.cliente.direccion" disabled />
          </p>
          <p type="Correo electrónico:">
            <input type="text" v-model="factura.cliente.correoElectronico" disabled />
          </p>
        </div>
      </div>

      <div class="agregar-producto">
        <input
          type="text"
          v-model="productoActual.buscador"
          placeholder="Buscar producto..."
          @keyup.enter="ObtenerProductoPorId"
        />
        <input
          type="number"
          v-model="productoActual.cantidad"
          placeholder="Cantidad"
          min="1"
        />
        <button @click="ObtenerProductoPorId">Agregar</button>
      </div>

      <div v-if="errores.productos" class="error-productos">
        Debe agregar al menos un producto
      </div>

      <div class="detalle">
        <table>
          <thead>
            <tr>
              <th>Código de Barras</th>
              <th>Nombre</th>
              <th>Cantidad</th>
              <th>Precio Unitario</th>
              <th>Subtotal</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(producto, index) in factura.productos" :key="index">
              <td>{{ producto.codigoBarras }}</td>
              <td>{{ producto.nombre }}</td>
              <td>{{ producto.cantidad }}</td>
              <td>${{ producto.precioUnitario }}</td>
              <td>${{ producto.subtotal }}</td>
              <td>
                <button @click="eliminarProducto(index)" class="btn-eliminar">
                  ✕
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="pie_factura">
        <div class="total-row">
          <span class="total-label">Subtotal:</span>
          <input type="text" :value="formatearMoneda(factura.subtotal)" disabled />
        </div>
        <div class="total-row">
          <span class="total-label">Total impuestos:</span>
          <input type="text" :value="formatearMoneda(factura.totalImpuestos)" disabled />
        </div>
        <div class="total-row">
          <span class="total-label">Total:</span>
          <input type="text" :value="formatearMoneda(factura.total)" disabled />
        </div>
      </div>
    </div>
    
    <div v-if="mensajeExito" class="mensaje-exito">
      Factura guardada exitosamente
    </div>
    <div v-if="mensajeError" class="mensaje-error">
      Error al guardar la factura. Por favor intente nuevamente.
    </div>
    
    <button @click="guardarFactura">Guardar Factura</button>
  </div>
</template>

<script>
import { guardarFachada } from "@/clients/FacturaClient.js";
import { consultarClientePorIdFachada } from "@/clients/ClienteClient.js";
import { consultarProductosIdFachada, buscarImpuestosPorIdProductoFachada } from "@/clients/ProductoClient";

export default {
  data() {
    return {
      factura: {
        fecha: new Date().toISOString().split("T")[0],
        empresa: {
          ruc: "",
          establecimiento: "",
          numeroDocumento: "",
          puntoEmision: "",
        },
        cliente: {
          idCliente: "",
          nombreCliente: "",
          direccion: "",
          correoElectronico: "",
        },
        productos: [],
        subtotal: 0,
        totalImpuestos: 0,
        total: 0,
      },
      productoActual: {
        cantidad: 1,
        buscador: "",
      },
      errores: {
        ruc: false,
        establecimiento: false,
        numeroDocumento: false,
        puntoEmision: false,
        idCliente: false,
        productos: false,
      },
      mensajeExito: false,
      mensajeError: false,
    };
  },
  watch: {
    "factura.cliente.idCliente"(nuevaCedula) {
      if (nuevaCedula && nuevaCedula.length >= 10) {
        this.ObtenerClientePorId();
      } else {
        this.factura.cliente.nombreCliente = "";
        this.factura.cliente.direccion = "";
        this.factura.cliente.correoElectronico = "";
      }
    },
  },
  methods: {
    validarCampos() {
      this.errores = {
        ruc: !this.factura.empresa.ruc.trim(),
        establecimiento: !this.factura.empresa.establecimiento.trim(),
        numeroDocumento: !this.factura.empresa.numeroDocumento.trim(),
        puntoEmision: !this.factura.empresa.puntoEmision.trim(),
        idCliente: !this.factura.cliente.idCliente.trim(),
        productos: this.factura.productos.length === 0,
      };

      return !Object.values(this.errores).some(error => error);
    },

    async guardarFactura() {
      try {
        if (!this.validarCampos()) {
          return;
        }

        const body = {
          rucEmpresa: this.factura.empresa.ruc,
          numeroDocumento: this.factura.empresa.numeroDocumento,
          establecimiento: this.factura.empresa.establecimiento,
          puntoEmision: this.factura.empresa.puntoEmision,
          fechaEmision: this.factura.fecha,
          cedulaCliente: this.factura.cliente.idCliente,
          cantidadItems: this.factura.productos.length,
          subTotal: this.factura.subtotal,
          totalImpuestos: this.factura.totalImpuestos,
          total: this.factura.total,
          detalleTos: this.factura.productos.map((producto) => ({
            codigoBarras: producto.codigoBarras,
            nombre: producto.nombre,
            cantidad: producto.cantidad,
            precio: producto.precioUnitario,
            subTotal: producto.subtotal,
          })),
        };

        const respuesta = await guardarFachada(body);
        console.log("Factura guardada:", respuesta);

        this.mensajeExito = true;
        setTimeout(() => {
          this.mensajeExito = false;
        }, 3000);
        
        this.limpiarFormulario();
      } catch (error) {
        console.error("Error al guardar factura:", error);
        
        this.mensajeError = true;
        setTimeout(() => {
          this.mensajeError = false;
        }, 3000);
      }
    },

    async ObtenerClientePorId() {
      try {
        let aux = await consultarClientePorIdFachada(this.factura.cliente.idCliente);
        if (!aux) {
          return;
        }
        this.factura.cliente.nombreCliente = aux.nombre + " " + aux.apellido;
        this.factura.cliente.direccion = aux.direccion;
        this.factura.cliente.correoElectronico = aux.correo;
      } catch (error) {
        console.error("Error al obtener cliente:", error);
      }
    },

    async ObtenerProductoPorId() {
      if (!this.productoActual.buscador || this.productoActual.cantidad < 1) {
        return;
      }

      try {
        const productoEncontrado = await consultarProductosIdFachada(this.productoActual.buscador);

        if (!productoEncontrado) {
          return;
        }

        const productoExistente = this.factura.productos.find(
          (p) => p.codigoBarras === productoEncontrado.codigoBarras
        );

        if (productoExistente) {
          productoExistente.cantidad += Number(this.productoActual.cantidad);
          productoExistente.subtotal = Math.round(
            productoExistente.precioUnitario * productoExistente.cantidad * 100
          ) / 100;
        } else {
          this.factura.productos.push({
            id: productoEncontrado.id,
            codigoBarras: productoEncontrado.codigoBarras,
            nombre: productoEncontrado.nombre,
            cantidad: Number(this.productoActual.cantidad),
            precioUnitario: productoEncontrado.precio,
            subtotal: Math.round(
              productoEncontrado.precio * Number(this.productoActual.cantidad) * 100
            ) / 100,
          });
        }

        await this.calcularTotales();
        this.productoActual.buscador = "";
        this.productoActual.cantidad = 1;
      } catch (error) {
        console.error("Error al obtener producto:", error);
      }
    },

    eliminarProducto(index) {
      this.factura.productos.splice(index, 1);
      this.calcularTotales();
    },

    async calcularTotales() {
      this.factura.subtotal = this.factura.productos.reduce(
        (total, producto) => total + Number(producto.subtotal || 0),
        0
      );

      this.factura.totalImpuestos = 0;

      for (const producto of this.factura.productos) {
        try {
          const impuestosDelProducto = await buscarImpuestosPorIdProductoFachada(
            producto.codigoBarras
          );

          if (impuestosDelProducto && impuestosDelProducto.length > 0) {
            producto.impuestos = impuestosDelProducto.reduce((sum, imp) => {
              return sum + producto.subtotal * imp.porcentaje;
            }, 0);
          } else {
            producto.impuestos = 0;
          }

          this.factura.totalImpuestos += producto.impuestos;
        } catch (error) {
          console.error(`Error calculando impuestos para producto ${producto.id}:`, error);
          producto.impuestos = 0;
        }
      }
      this.factura.total = this.factura.subtotal + this.factura.totalImpuestos;
    },

    formatearMoneda(valor) {
      return "$" + Number(valor || 0).toFixed(2);
    },

    limpiarFormulario() {
      this.factura = {
        fecha: new Date().toISOString().split("T")[0],
        empresa: {
          ruc: "",
          establecimiento: "",
          numeroDocumento: "",
          puntoEmision: "",
        },
        cliente: {
          idCliente: "",
          nombreCliente: "",
          direccion: "",
          correoElectronico: "",
        },
        productos: [],
        subtotal: 0,
        totalImpuestos: 0,
        total: 0,
      };
      this.productoActual = {
        cantidad: 1,
        buscador: "",
      };
      this.errores = {
        ruc: false,
        establecimiento: false,
        numeroDocumento: false,
        puntoEmision: false,
        idCliente: false,
        productos: false,
      };
    },
  },
};
</script>

<style scoped>
.container-factura {
  width: 100%;
  min-height: 100vh;
  box-sizing: border-box;
  padding: 15px 10px 15px 10px;
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.factura {
  width: 85%;
  max-width: 900px;
  box-sizing: border-box;
  padding: 15px 20px;
  border: 1px solid black;
  margin: 0 auto;
  border-radius: 10px;
  background: #fff;
  display: flex;
  flex-direction: column;
  gap: 15px;
  flex: 1;
}

.factura-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.factura-header h1 {
  margin: 0;
  font-size: 24px;
}

.factura-header input {
  width: auto;
  padding: 4px 8px;
  font-size: 14px;
  border-radius: 4px;
  border: 1px solid #ccc;
  text-align: right;
}

.cabecera {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.cabecera h3 {
  width: 100%;
  text-align: left;
  margin: 0 0 3px 0;
  padding-left: 5%;
  font-size: 16px;
}

.empresa,
.cliente {
  display: grid;
  grid-template-columns: 350px 350px;
  grid-template-rows: auto auto;
  gap: 12px;
  max-width: 700px;
  margin: 0 auto;
}

input {
  width: 100%;
  max-width: 300px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 13px;
  box-sizing: border-box;
  margin-top: auto;
}

p {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  gap: 6px;
  margin-bottom: 10px;
  min-height: 50px;
}

p::before {
  content: attr(type);
  min-width: 120px;
  width: 100%;
  max-width: 300px;
  text-align: left;
  font-weight: bold;
  color: #333;
  line-height: 1.2;
  display: block;
  word-wrap: break-word;
  font-size: 13px;
}

.error-msg {
  color: #dc3545;
  font-size: 12px;
  font-weight: bold;
  margin-top: 2px;
}

.error-productos {
  color: #dc3545;
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 5px;
  padding: 10px;
  
}

.agregar-producto {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
  max-width: 600px;
  margin: 15px auto;
  padding: 15px;
  background-color: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
}

.agregar-producto input[type="text"],
.agregar-producto input[type="number"] {
  flex-grow: 1;
  padding: 8px 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
  color: #333;
  box-sizing: border-box;
  font-size: 13px;
}

.agregar-producto input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.25);
}

.agregar-producto button {
  padding: 8px 16px;
  background-color: #07265c;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 13px;
  font-weight: bold;
}

.agregar-producto button:hover {
  background-color: #1b4387;
}

.detalle {
  margin: 15px 0;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.detalle table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
  font-size: 13px;
}

.detalle th,
.detalle td {
  border: 1px solid #ddd;
  padding: 6px;
  text-align: center;
}

.detalle th {
  background-color: #07265c;
  color: white;
  font-weight: bold;
}

.detalle tbody tr:nth-child(even) {
  background-color: #f8f9fa;
}

.detalle tbody tr:hover {
  background-color: #e9ecef;
}

.pie_factura {
  margin: 15px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 8px;
  min-width: 250px;
}

.total-row {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
  max-width: 250px;
}

.total-label {
  font-weight: bold;
  color: #333;
  min-width: 100px;
  text-align: right;
  font-size: 13px;
}

.total-row input {
  flex: 1;
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 4px;
  text-align: right;
  font-weight: bold;
  font-size: 13px;
}

.detalle td:last-child {
  padding: 8px !important;
  width: 60px;
  text-align: center;
  vertical-align: middle;
}

.btn-eliminar {
  background-color: #dc3545;
  color: white;
  border: none;
  width: 28px;
  height: 28px;
  cursor: pointer;
  border-radius: 10%;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.2s;
  margin: 0 auto;
}

.btn-eliminar:hover {
  background-color: #c82333;
  transform: scale(1.1);
}

.container-factura > button {
  margin: 8px auto 0 auto;
  padding: 10px 25px;
  background-color: #07265c;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  display: block;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
  flex-shrink: 0;
}

.container-factura > button:hover {
  background-color: #1b4387;
  transform: translateY(-2px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.mensaje-exito,
.mensaje-error {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 15px 20px;
  border-radius: 8px;
  font-weight: bold;
  font-size: 16px;
  z-index: 1000;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  animation: slideIn 0.3s ease-out;
}

.mensaje-exito {
  background-color: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}

.mensaje-error {
  background-color: #f8d7da;
  color: #721c24;
  border: 1px solid #f5c6cb;
}

@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

/* RESPONSIVO */
@media (max-width: 1070px) {
  .empresa,
  .cliente {
    grid-template-columns: 1fr 1fr;
    gap: 10px;
    padding: 0 10px;
  }

  .cabecera h3 {
    padding-left: 2%;
  }
}

@media (max-width: 600px) {
  .container-factura {
    padding: 15px 10px 50px 10px;
  }

  .factura {
    padding: 15px;
    width: 95%;
  }

  .factura-header {
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
  }

  .empresa,
  .cliente {
    grid-template-columns: 1fr;
    gap: 15px;
    margin: 0;
    padding: 0 5px;
  }

  p {
    min-height: 70px;
    gap: 10px;
  }

  p::before {
    min-width: auto;
    width: 100%;
    font-size: 14px;
    margin-bottom: 5px;
  }

  input {
    width: 100%;
    max-width: none;
    padding: 12px;
    font-size: 16px;
  }

  .cabecera h3 {
    padding-left: 0;
    text-align: center;
  }

  .agregar-producto {
    flex-direction: column;
    align-items: stretch;
    gap: 10px;
    padding: 15px;
    margin: 15px auto;
  }

  .pie_factura {
    margin: 15px auto;
    align-items: center;
    width: 100%;
    padding: 0 10px;
  }

  .total-row {
    max-width: 100%;
    justify-content: space-between;
  }

  .total-label {
    min-width: auto;
    flex: 1;
  }

  .total-row input {
    flex: 1;
    max-width: 150px;
  }

  .detalle table {
    font-size: 12px;
  }

  .btn-eliminar {
    width: 30px;
    height: 30px;
    font-size: 14px;
  }

  .container-factura > button {
    font-size: 18px;
    padding: 12px 25px;
  }
}
</style>