<template>
    <div class="container_consultarcliente">
        <div class="container_consultar">
            <input class="input_consulta" type="text" v-model="identificador"
                :placeholder="`Ingrese el id del ${category === 'producto' ? 'Producto' : 'Servicio'}...`" :disabled="deshabilitado" />

            <button class="boton_consulta" @click="ObtenerProductoPorId()"
                :disabled="deshabilitadoBotonConsultar">Consultar</button>
        </div>

        <h1>Actualice el {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>

        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="producto.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Categoria:">
                <input type="text" v-model="producto.categoria" disabled />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="producto.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="producto.precio" :disabled="deshabilitado" />
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

        <button class="boton_opcion" @click="actulizarParcial()" :disabled="deshabilitado">Actualizar</button>

        <div v-if="!existeProducto" class="mensaje-error-actualizar">
            <h1>El {{ category === 'producto' ? 'producto' : 'servicio' }} con el id {{ identificador }} no existe o no es un {{ category === 'producto' ? 'producto' : 'servicio' }}.</h1>
        </div>
        <div v-if="exitoActulizar" class="mensaje-exito-actualizar">
            <h2>
                El {{ category === 'producto' ? 'Producto' : 'Servicio' }} con el id {{ identificador }} se actualizó correctamente
            </h2>
        </div>
    </div>
</template>

<script>
import { consultarProductosIdFachada, actulizarParcialPorIdFachada} from "@/clients/ProductoClient";
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
            producto: {
                id: null,
                nombre: null,
                categoria: null,
                codigoBarras: null,
                precio: null,
            },
            existeProducto: true,
            exitoActulizar: false,
            deshabilitado: false,
            deshabilitadoBotonConsultar: false,
            impuestos: [],
            impuestosSeleccionados: [],
            impuestosDeProducto: [],
        };
    },

    methods: {
        async ObtenerProductoPorId() {
            this.deshabilitadoBotonConsultar = true;
            this.deshabilitado = true;
            this.existeProducto = true;
            this.exitoActulizar = false; 

            try {
                let aux = await consultarProductosIdFachada(this.identificador);
                this.impuestosDeProducto = await obtenerInformacionUrlsFachada(aux._links.impuestos);
                

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

                // logica para rellenar impuestos
                for (let impuesto of this.impuestosDeProducto){
                    this.impuestosSeleccionados.unshift(impuesto.id);
                }

            } catch (error) {
                alert(`Error al consultar el ${this.category}. Verifique el ID e intente de nuevo.`);
                console.error(`Error al obtener ${this.category} por ID:`, error);
                this.reiniciarVaraibles();
                this.deshabilitado = false;
                this.deshabilitadoBotonConsultar = false;
                this.existeProducto = false;
                setTimeout(() => { this.existeProducto = true; }, 3000);
            }
        },

        async actulizarParcial() {
            if (!this.producto.id || !this.producto.nombre || !this.producto.codigoBarras || this.producto.precio === null) {
                alert(`Por favor, consulte un ${this.category} válido y complete todos los campos antes de actualizar.`);
                return;
            }

            const productoToBody = {
                nombre: this.producto.nombre,
                categoria: this.category, 
                codigoBarras: this.producto.codigoBarras,
                precio: this.producto.precio,
                impuestos: this.impuestosSeleccionados
            };

            try {
                await actulizarParcialPorIdFachada(
                    productoToBody,
                    this.producto.codigoBarras
                );

                this.deshabilitado = true;
                this.exitoActulizar = true;
                setTimeout(() => {
                    this.exitoActulizar = false;
                    this.deshabilitado = false;
                    this.reiniciarVaraibles();
                }, 3000);
            } catch (error) {
                alert(`Error al actualizar el ${this.category}. Verifique los datos o si el ${this.category} existe.`);
                console.error(`Error al actualizar ${this.category}:`, error);
            }
        },

        reiniciarVaraibles() {
            this.identificador = null;
            this.producto.nombre = null;
            this.producto.categoria = null;
            this.producto.codigoBarras = null;
            this.producto.precio = null;
            this.producto.id = null;
            this.impuestosSeleccionados = [];
        },
    },

    async beforeMount(){
        this.impuestos = await consultarTodosImpuestosFachada();        
    }
};
</script>


<style >
</style>