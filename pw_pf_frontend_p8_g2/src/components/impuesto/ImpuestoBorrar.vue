<template>
    <div class="container_consultarcliente">
        <div class="container_consultar">
            <input
                class="input_consulta"
                type="number"
                v-model="identificador"
                placeholder="Ingrese el ID del Impuesto..."
                :disabled="deshabilitadoBotonConsultar"
            />
            <button class="boton_consulta" @click="ObtenerImpuestoPorId()"
                :disabled="deshabilitadoBotonConsultar">
                Consultar
            </button>
        </div>

        <h1>Información del Impuesto a Borrar</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="impuesto.nombre" disabled />
            </p>
            <p type="Porcentaje:">
                <input type="text" v-model="impuesto.porcentaje" disabled />
            </p>
        </div>
        <button class="boton_opcion" @click="borrar()" :disabled="deshabilitado">Borrar</button>

        <div v-if="!existeImpuesto && !errorMensaje && identificador" class="mensaje-error">
            <h2>El Impuesto con el ID {{ identificador }} no existe</h2>
        </div>
        <div v-if="exitoBorrar" class="mensaje-exito">
            <h2>El Impuesto con el ID {{ identificador }} se borró correctamente</h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error">
            <h2>{{ errorMensaje }}</h2>
        </div>
    </div>
</template>

<script>
import { consultarImpuestosIdFachada, borrarPorIdFachada } from "@/clients/ImpuestoClient";
import "@/css/EstiloGenerico.css";

export default {
    data() {
        return {
            identificador: null,
            impuesto: {
                id: null,
                nombre: null,
                porcentaje: null,
            },
            existeImpuesto: true,
            exitoBorrar: false,
            deshabilitado: true,
            deshabilitadoBotonConsultar: false,
            errorMensaje: null,
        };
    },

    methods: {
        async ObtenerImpuestoPorId() {
            this.deshabilitadoBotonConsultar = true;
            this.deshabilitado = true;
            this.existeImpuesto = true;
            this.exitoBorrar = false;
            this.errorMensaje = null;

            if (!this.identificador) {
                this.mostrarMensajeError("Por favor, ingrese un ID para consultar el impuesto.");
                this.deshabilitadoBotonConsultar = false;
                return;
            }

            try {
                const aux = await consultarImpuestosIdFachada(this.identificador);
                if (aux === null) {
                    this.existeImpuesto = false;
                    this.mostrarMensajeError(`El Impuesto con el ID ${this.identificador} no existe.`);
                    setTimeout(() => {
                        this.existeImpuesto = true;
                        this.reiniciarVariables();
                        this.deshabilitadoBotonConsultar = false;
                    }, 3000);
                    return;
                }

                this.impuesto.id = aux.id;
                this.impuesto.nombre = aux.nombre;
                this.impuesto.porcentaje = aux.porcentaje;

                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
            } catch (error) {
                this.mostrarMensajeError(`Error al consultar el Impuesto. Verifique el ID e intente de nuevo.`);
                console.error("Error al obtener Impuesto por ID:", error);
                this.reiniciarVariables();
                this.deshabilitado = true;
                this.deshabilitadoBotonConsultar = false;
            }
        },

        async borrar() {
            if (this.deshabilitado || !this.impuesto.id) {
                this.mostrarMensajeError("Por favor, consulte un impuesto válido antes de intentar borrarlo.");
                return;
            }

            this.deshabilitado = true;
            this.deshabilitadoBotonConsultar = true;
            this.exitoBorrar = false;
            this.errorMensaje = null;

            try {
                await borrarPorIdFachada(this.identificador);

                this.exitoBorrar = true;
                setTimeout(() => {
                    this.exitoBorrar = false;
                    this.deshabilitado = false;
                    this.deshabilitadoBotonConsultar = false;
                    this.reiniciarVariables();
                }, 3000);
            } catch (error) {
                this.mostrarMensajeError(`Error al borrar el Impuesto. Es posible que ya no exista o hubo un problema en el servidor.`);
                console.error("Error al borrar Impuesto:", error);
                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
            }
        },

        reiniciarVariables() {
            this.identificador = null;
            this.impuesto.id = null;
            this.impuesto.nombre = null;
            this.impuesto.porcentaje = null;
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

<style >

</style>