<template>
    <div class="container_consultarcliente">

        <div class="container_consultar">
            <input class="input_consulta" type="text" v-model="identificador"
                :placeholder="`Ingrese el id del ${category === 'producto' ? 'Producto' : 'Servicio'}...`"
                :disabled="deshabilitado" />
            <button class="boton_consulta" @click="ObtenerProductoPorId()"
                :disabled="deshabilitadoBotonConsultar">Consultar</button>
        </div>

        <h1>Información del {{ category === 'producto' ? 'Producto' : 'Servicio' }} a Borrar</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="producto.nombre" disabled />
            </p>
            <p type="Categoria:">
                <input type="text" v-model="producto.categoria" disabled />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="producto.codigoBarras" disabled />
            </p>
            <p type="Precio:">
                <input type="number" v-model="producto.precio" disabled />
            </p>
        </div>
        <button class="boton_opcion" @click="borrar()" :disabled="deshabilitado">Borrar</button>
        <div v-if="!existeProducto" class="mensaje-error-general">
            <h2>El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} no existe o no
                es un {{ category === 'producto' ? 'producto' : 'servicio' }}.</h2>
        </div>
        
        <div v-if="exitoBorrar" class="mensaje-exito-general">
            <h2>
                El {{ category === 'producto' ? 'Producto' : 'Servicio' }} con el id {{ identificador }} Se Borro
                Correctamente
            </h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error-general">
            <h2>{{ errorMensaje }}</h2>
        </div>
    </div>
</template>

<script>
import { consultarProductosIdFachada, borrarPorIdFachada } from "@/clients/ProductoClient";
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
            identificador: null,
            producto: {
                id: null,
                nombre: null,
                categoria: null,
                codigoBarras: null,
                precio: null,
            },
            existeProducto: true,
            exitoBorrar: false,
            deshabilitado: false,
            deshabilitadoBotonConsultar: false,
            errorMensaje: null, 
        };
    },

    methods: {
        async ObtenerProductoPorId() {
            this.deshabilitadoBotonConsultar = true;
            this.deshabilitado = true;
            this.existeProducto = true;
            this.exitoBorrar = false;
            this.errorMensaje = null; 

            try {
                let aux = await consultarProductosIdFachada(this.identificador);

                if (aux === null || aux.categoria !== this.category) {
                    this.existeProducto = false;
                    setTimeout(() => {
                        this.existeProducto = true;
                        this.reiniciarVaraibles();
                        this.deshabilitado = false;
                        this.deshabilitadoBotonConsultar = false;
                    }, 3000);
                    return;
                }

                this.producto.id = aux.id;
                this.producto.nombre = aux.nombre;
                this.producto.categoria = aux.categoria;
                this.producto.codigoBarras = aux.codigoBarras;
                this.producto.precio = aux.precio;
                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
            } catch (error) {
                this.mostrarMensajeError(`Error al consultar el ${this.category}. Verifique el ID e intente de nuevo.`);
                console.error(`Error al obtener ${this.category} por ID para borrar:`, error);
                this.reiniciarVaraibles();
                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
                this.existeProducto = false;
                setTimeout(() => { this.existeProducto = true; }, 3000);
            }
        },

        async borrar() {
            if (!this.producto.id || !this.producto.codigoBarras || this.deshabilitado) {
                this.mostrarMensajeError(`Por favor, consulte un ${this.category} válido antes de intentar borrarlo.`);
                return;
            }



            try {
                await borrarPorIdFachada(this.producto.codigoBarras);

                this.deshabilitado = true;
                this.deshabilitadoBotonConsultar = true;
                this.exitoBorrar = true;
                this.errorMensaje = null; 
                setTimeout(() => {
                    this.exitoBorrar = false;
                    this.deshabilitado = false;
                    this.deshabilitadoBotonConsultar = false;
                    this.reiniciarVaraibles();
                }, 3000);
            } catch (error) {
                this.mostrarMensajeError(`Error al borrar el ${this.category}. Es posible que el ${this.category} ya no exista o hubo un problema en el servidor.`);
                console.error(`Error al borrar ${this.category}:`, error);
            }
        },

        reiniciarVaraibles() {
            this.identificador = null;
            this.producto.nombre = null;
            this.producto.categoria = null;
            this.producto.codigoBarras = null;
            this.producto.precio = null;
            this.producto.id = null;
        },
        mostrarMensajeError(mensaje) {
            this.errorMensaje = mensaje;
            setTimeout(() => {
                this.errorMensaje = null;
            }, 3000);
        }
    },
};
</script>

<style>

</style>
