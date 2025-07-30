<template>
    <div class="container_consultarcliente">
        <h1>Crear un nuevo {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="item.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="item.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="item.precio" :disabled="deshabilitado" />
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

        <button class="boton_opcion" @click="guardarServicio()">Crear</button>

        <div v-if="exito" class="mensaje-exito">
            <h2>{{ category === 'producto' ? 'Producto' : 'Servicio' }} Correctamente Guardado</h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error">
            <h2>{{ errorMensaje }}</h2>
        </div>
    </div>
</template>

<script>
import { guardarFachada } from "@/clients/ProductoClient";
import { consultarTodosImpuestosFachada } from "@/clients/ImpuestoClient";
import "@/css/EstiloGenerico.css";

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
            item: {
                nombre: null,
                codigoBarras: null,
                precio: null,
            },
            exito: false,
            deshabilitado: false,
            errorMensaje: null, 
            impuestos: [],
            impuestosSeleccionados: [],
        };
    },

    methods: {
        async guardarServicio() {
            this.errorMensaje = null; 
            this.exito = false; 

            if (!this.item.nombre || !this.item.codigoBarras || this.item.precio === null) {
                this.mostrarMensajeError("Por favor, complete todos los campos (Nombre, Código de Barras, Precio).");
                return;
            }
            if (this.item.precio <= 0) {
                this.mostrarMensajeError("El precio debe ser un número positivo.");
                return;
            }

            const itemToBody = {
                nombre: this.item.nombre,
                categoria: "servicio",
                codigoBarras: this.item.codigoBarras,
                precio: this.item.precio,
                impuestos: this.impuestosSeleccionados
            };

            this.deshabilitado = true;

            try {
                await guardarFachada(itemToBody);
                this.exito = true; 
                setTimeout(() => {
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
            this.item.nombre = null;
            this.item.categoria = null; 
            this.item.codigoBarras = null;
            this.item.precio = null;
            this.impuestosSeleccionados = [];
        },
    
        mostrarMensajeError(mensaje) {
            this.errorMensaje = mensaje;
            setTimeout(() => {
                this.errorMensaje = null;
            }, 3000);
        }
    },
    watch: {

        category: {
            immediate: true,
            handler(newCategory) {
                this.item.categoria = newCategory;
            }
        }
    },
    async beforeMount(){
        this.impuestos = await consultarTodosImpuestosFachada();
        console.log("Impuestos cargados:", this.impuestos);
        
    }
};
</script>

<style>

</style>