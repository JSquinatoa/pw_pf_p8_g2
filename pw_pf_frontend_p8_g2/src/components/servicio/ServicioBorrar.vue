<template>
    <div class="container_consultarcliente">
        <div class="container_consultar">
            <input class="input_consulta" type="number" v-model="identificador"
                :placeholder="`Ingrese el id del ${category === 'producto' ? 'Producto' : 'Servicio'}...`"
                :disabled="deshabilitado" />
            <button class="boton_consulta" @click="ObtenerServicioPorId()"
                :disabled="deshabilitadoBotonConsultar">Consultar</button>
        </div>

        <h1>Información del {{ category === 'producto' ? 'Producto' : 'Servicio' }} a Borrar</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="servicio.nombre" disabled />
            </p>
            <p type="Categoria:">
                <input type="text" v-model="servicio.categoria" disabled />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="servicio.codigoBarras" disabled />
            </p>
            <p type="Precio:">
                <input type="number" v-model="servicio.precio" disabled />
            </p>
        </div>
        <button class="boton_opcion" @click="borrar()" :disabled="deshabilitado">Borrar</button>

        <div v-if="!existeServicio" class="mensaje-error">
            <h2>El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} no existe</h2>
        </div>
        <div v-if="exitoBorrar" class="mensaje-exito">
            <h2>
                El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} Se Borró
                Correctamente
            </h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error">
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
            servicio: {
                id: null,
                nombre: null,
                categoria: null,
                codigoBarras: null,
                precio: null,
            },
            existeServicio: true,
            exitoBorrar: false,
            deshabilitado: false,
            deshabilitadoBotonConsultar: false,
            errorMensaje: null, 
        };
    },

    methods: {
        async ObtenerServicioPorId() {
            this.deshabilitadoBotonConsultar = true;
            this.deshabilitado = true; 
            this.existeServicio = true;
            this.exitoBorrar = false;
            this.errorMensaje = null; 

            try {
                let aux = await consultarProductosIdFachada(this.identificador);

                if (aux === null || aux.categoria !== this.category) {
                    this.existeServicio = false;
                  
                    setTimeout(() => {
                        this.existeServicio = true;
                        this.deshabilitado = false;
                        this.reiniciarVaraibles();
                        this.deshabilitadoBotonConsultar = false;
                    }, 3000);
                    return;
                }

                this.servicio.id = aux.id;
                this.servicio.nombre = aux.nombre;
                this.servicio.categoria = aux.categoria;
                this.servicio.codigoBarras = aux.codigoBarras;
                this.servicio.precio = aux.precio;
                this.deshabilitado = false; 
                this.deshabilitadoBotonConsultar = false;
            } catch (error) {

                this.mostrarMensajeError(`Error al consultar el ${this.category}. Verifique el ID e intente de nuevo.`);
                console.error(`Error al obtener ${this.category} por ID para borrar:`, error);
                this.reiniciarVaraibles();
                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
                this.existeServicio = false; // Mostrar que no existe si hay error de consulta
                setTimeout(() => { this.existeServicio = true; }, 3000);
            }
        },

        async borrar() {
            this.errorMensaje = null; 

            if (!this.deshabilitado && this.identificador !== null && this.servicio.id !== null) {
                try {
                    await borrarPorIdFachada(this.identificador); 

                    this.deshabilitado = true;
                    this.deshabilitadoBotonConsultar = true;
                    this.exitoBorrar = true;
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
            } else {
                this.mostrarMensajeError(`Por favor, consulte un ${this.category === 'producto' ? 'producto' : 'servicio'} válido antes de intentar borrarlo.`);
                return;
            }
        },

        reiniciarVaraibles() {
            this.identificador = null;
            this.servicio.nombre = null;
            this.servicio.categoria = null;
            this.servicio.codigoBarras = null;
            this.servicio.precio = null;
            this.servicio.id = null;
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