<template>
    <div class="container_consultarcliente">
        <h1>Crear un nuevo {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="producto.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="producto.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="producto.precio" :disabled="deshabilitado" />
            </p>
            <p type="Stock:">
                <input type="number" v-model="stock">
            </p>
            <p type="Bodega">
                <select v-model="bodegaSeleccionada">
                    <option v-for="bodega in bodegas" :key="bodega.id" :value="bodega.codigo">{{ bodega.codigo }} - {{ bodega.nombre }}</option>
                </select>
            </p>
        </div>

        <div class="containerformulario">
            <p type = "Impuestos:">
                <div class="impuestos" v-for="impuesto in impuestos" :key="impuesto.id">
                    <label :for="impuesto.nombre">{{ impuesto.nombre }}</label>
                    <input type="checkbox" :id="impuesto.nombre" name="impuestos" :value="impuesto.id" v-model="impuestosSeleccionados"/>
                </div>                
            </p>           
        </div>

        <button class="boton_opcion" @click="guardarProductos()">Crear</button>

        <div v-if="exito" class="mensaje-exito">
            <h2>{{ category === 'producto' ? 'Producto' : 'Servicio' }} Correctamente Guardado</h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error">
            <h2>{{ errorMensaje }}</h2>
        </div>
    </div>
</template>

<script>
import { guardarFachada as guardarProductoFachada } from "@/clients/ProductoClient.js";
import { guardarFachada as guardarInventarioFachada } from "@/clients/InventarioClient.js";
import { consultarBodegasFachada} from "@/clients/BodegaClient.js";
import { consultarTodosImpuestosFachada,  } from "@/clients/ImpuestoClient.js";

export default {
    props: {
        category: {
            type: String,
            required: true,
            validator: (value) => ['producto', 'servicio'].includes(value),
        },
    },
    data() {
        return {
            producto: {
                nombre: null,
                categoria: null,
                codigoBarras: null,
                precio: null,
            },
            stock: 0,
            bodegaSeleccionada: null,
            bodegas: [],
            exito: false,
            deshabilitado: false,
            errorMensaje: null,
            impuestos: [],
            impuestosSeleccionados: [],
        };
    },
    methods: {
        async guardarProductos() {
            console.log(this.impuestosSeleccionados);
            
            this.errorMensaje = null; 
            this.exito = false; 
            if (!this.producto.nombre || !this.producto.codigoBarras || this.producto.precio === null) {
                this.mostrarMensajeError("Por favor, complete todos los campos (Nombre, Código de Barras, Precio).");
                return;
            }
            if (this.producto.precio <= 0) {
                this.mostrarMensajeError("El precio debe ser un número positivo.");
                return;
            }

            const productoToBody = {
                nombre: this.producto.nombre,
                categoria: this.category,
                codigoBarras: this.producto.codigoBarras,
                precio: this.producto.precio,
                impuestos: this.impuestosSeleccionados,
            };

            this.deshabilitado = true; 

            try {
                await guardarProductoFachada(productoToBody)
                this.exito = true; 
                setTimeout(() => {
                    this.guardarInventario(this.bodegaSeleccionada, this.producto.codigoBarras, this.stock);
                    this.exito = false;
                    this.deshabilitado = false;
                    this.reiniciarVaraibles();                    
                }, 3000); 
            } catch (error) {                
                this.mostrarMensajeError(`Error al guardar el ${this.category}. Verifique los datos o intente más tarde.`);
                console.error(`Error al guardar ${this.category}:`, error);
                this.deshabilitado = false; 
            }
        },
        reiniciarVaraibles() {
            this.producto.nombre = null;
            this.producto.codigoBarras = null;
            this.producto.precio = null;
            this.impuestosSeleccionados = [];
        },
        mostrarMensajeError(mensaje) {
            this.errorMensaje = mensaje;
            setTimeout(() => {
                this.errorMensaje = null;
            }, 3000); 
        },
        async guardarInventario(codigoBodega, codigoBarras, stock){
            await guardarInventarioFachada(codigoBodega, codigoBarras, stock);
        }
    },
    watch: {
        category: {
            immediate: true,
            handler(newCategory) {
                this.producto.categoria = newCategory;
            }
        }
    },
    async beforeMount(){
        this.bodegas = await consultarBodegasFachada();
        console.log("bodegas", this.bodegas); 
        this.impuestos = await consultarTodosImpuestosFachada();
        console.log("impuestos", this.impuestos);       
    }
};
</script>

<style>
select {
    padding: 8px;
}

.impuestos{
    width: 100%;
    padding: 2px;
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid #ccc;  
}
.impuestos label{
    width: 100%;
    cursor: pointer;
}

</style>