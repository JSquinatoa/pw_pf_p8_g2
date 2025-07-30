<template>
    <div class="container_consultarcliente">
        <div class="container_consultar">
            <input class="input_consulta" type="text" v-model="identificador"
                :placeholder="`Ingrese el id del ${category === 'producto' ? 'Producto' : 'Servicio'}...`" :disabled="deshabilitado" />

            <button class="boton_consulta" @click="ObtenerServicioPorId()"
                :disabled="deshabilitadoBotonConsultar">Consultar</button>
        </div>

        <h1>Actualice el {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>

        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="servicio.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Categor&iacute;a:">
                <input type="text" v-model="servicio.categoria" disabled />
            </p>
            <p type="C&oacute;digo de Barras:">
                <input type="text" v-model="servicio.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="servicio.precio" :disabled="deshabilitado" />
            </p>
        </div>

        <h2> Actualice los impuestos </h2>
        <div class="containerformulario">
            <p type = "Impuestos:">
                <div class="impuestos" v-for="impuesto in impuestos" :key="impuesto.id">
                    <label :for="impuesto.nombre">{{ impuesto.nombre }}</label>
                    <input type="checkbox" :id="impuesto.nombre" name="impuestos" :value="impuesto.id" v-model="impuestosSeleccionados"/>
                </div>                
            </p>           
        </div>

        <button class="boton_opcion" @click="actulizarParcial()"
            :disabled="deshabilitado">Actualizar</button>

        <div v-if="!existeServicio" class="mensaje-error-general">
            <h1>El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} no existe</h1>
        </div>
        <div v-if="exitoActulizar" class="mensaje-exito-general">
            <h2>
                El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} se actualizó correctamente
            </h2>
        </div>
    </div>
</template>

<script>
import { consultarProductosIdFachada,actulizarParcialPorIdFachada } from "@/clients/ProductoClient"; 
import { consultarTodosImpuestosFachada } from "@/clients/ImpuestoClient.js";
import { obtenerInformacionUrlsFachada } from "@/helpers/ObtenerInforUrls.js";
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
            exitoActulizar: false,
            deshabilitado: false,
            deshabilitadoBotonConsultar: false,
            impuestos: [],
            impuestosSeleccionados: [],
            impuestosDeServicio: [],
        };
    },

    methods: {
        async ObtenerServicioPorId() {
            let aux = await consultarProductosIdFachada(this.identificador);
            this.impuestosDeServicio = await obtenerInformacionUrlsFachada(aux._links.impuestos);

            if (aux === null || aux.categoria !== this.category) {
                this.existeServicio = false; 
                this.deshabilitado = true;
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

            // logica para rellenar impuestos
            for (let impuesto of this.impuestosDeServicio){
                this.impuestosSeleccionados.unshift(impuesto.id);
            }
        },

        async actulizarParcial() {
            const servicioToBody = { 
                nombre: this.servicio.nombre,
                categoria: this.category, 
                codigoBarras: this.servicio.codigoBarras,
                precio: this.servicio.precio,
                impuestos: this.impuestosSeleccionados
            };

            if (!this.deshabilitado) {
                await actulizarParcialPorIdFachada(
                    servicioToBody,
                    this.identificador
                );
            }
            this.deshabilitado = true;
            this.exitoActulizar = true;
            setTimeout(() => {
                this.exitoActulizar = false;
                this.deshabilitado = false;
                this.reiniciarVaraibles();
                this.deshabilitadoBotonConsultar = false; 
            }, 3000);
        },

        reiniciarVaraibles() {
            this.identificador = null;
            this.servicio.nombre = null;
            this.servicio.categoria = null;
            this.servicio.codigoBarras = null;
            this.servicio.precio = null;
            this.servicio.id = null;
            this.impuestosSeleccionados = [];
        },
    },
    async beforeMount(){
        this.impuestos = await consultarTodosImpuestosFachada();
    }
};
</script>

<style>

</style>