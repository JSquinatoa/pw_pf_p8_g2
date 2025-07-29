<template>
    <div class="container_borrar_servicio">
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
        <button class="boton_general boton_borrar" @click="borrar()" :disabled="deshabilitado">Borrar</button>

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

<style scoped>
.container_borrar_servicio {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 90%;
    max-width: 960px;
    margin: 5px auto;
    padding: 20px;
    box-sizing: border-box;
    gap: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

h1 {
    margin-top: 20px;
    margin-bottom: 20px;
    text-transform: uppercase;
    color: #333;
    text-align: center;
    font-size: 1.8em;
    width: 100%;
}

h2 {
    margin-top: 15px;
    text-align: center;
    font-size: 1.2em;
}


.mensaje-error h2 {
    color: #dc3545;
}

.mensaje-exito h2 {
    color: #4CAF50;
}


.container_consultar {
    display: flex;
    width: 60%;
    max-width: 450px;
    justify-content: center;
    align-items: center;
    gap: 15px;
}

.input_consulta {
    flex-grow: 1;
    padding: 10px 12px;
    border: 1px solid #ccc;
    border-bottom: 2px solid #2c3e50;
    border-radius: 5px;
    font-size: 1em;
}

.input_consulta::-webkit-outer-spin-button,
.input_consulta::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

.input_consulta:focus {
    outline: none;
    border-color: #007bff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.4);
}

.boton_consulta {
    width: 30%;
    min-width: 100px;
    padding: 10px 15px;
    border-radius: 10px;
    font-size: 1em;
    font-family: "Courier New", Courier, monospace;
    font-weight: bold;
    transition: all 0.1s ease;
    cursor: pointer;
    background-color: #2c3e50;
    color: white;
    border: none;
    white-space: nowrap;
    text-align: center;
}

.boton_consulta:hover {
    background-color: #34495e;
    transform: scale(1.02);
}

.boton_consulta:active {
    background-color: #1a252f;
    transform: scale(0.99);
}

.boton_general {
    padding: 12px 25px;
    width: auto;
    min-width: 180px;
    margin-top: 25px;
    border-radius: 10px;
    font-family: "Courier New", Courier, monospace;
    font-weight: bold;
    transition: all 0.1s ease;
    cursor: pointer;
    color: white;
    border: none;
    white-space: nowrap;
    text-align: center;
}

.boton_general:hover {
    transform: scale(1.02);
}

.boton_general:active {
    transform: scale(0.99);
}


.boton_borrar {
    background-color: #dc3545;
}

.boton_borrar:hover {
    background-color: #c82333;
}

.boton_borrar:active {
    background-color: #bd2130;
}

.containerformulario {
    display: flex;
    flex-direction: column;
    width: 50%;
    max-width: 550px;
    padding: 35px 20px 45px 20px;
    border: 10px double;
    border-end-end-radius: 100px;
    border-start-start-radius: 100px;
    background-color: #f4f6f8;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.516);
    gap: 10px;
}

p {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
    box-sizing: border-box;
    padding: 0 5px;
    margin-top: 5px;
    margin-bottom: 5px;
}

.containerformulario p:last-of-type {
    margin-bottom: 0;
}

p::before {
    display: block;
    content: attr(type);
    text-align: left;
    font-weight: bold;
    color: #333;
    font-size: 0.9em;
    margin-bottom: 3px;
    width: 100%;
}

.containerformulario input {
    width: 95%;
    padding: 6px 8px;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 0.9em;
    box-sizing: border-box;
}

@media (max-width: 1024px) {
    .container_borrar_servicio {
        width: 95%;
        padding: 15px;
        gap: 15px;
    }

    .container_consultar {
        width: 80%;
    }

    .containerformulario {
        width: 80%;
        padding: 25px 15px 35px 15px;
        gap: 8px;
    }

    p {
        margin-top: 3px;
        margin-bottom: 3px;
        padding: 0 3px;
    }

    p::before {
        font-size: 0.85em;
        margin-bottom: 2px;
    }

    .containerformulario input {
        width: 95%;
        padding: 5px 7px;
        font-size: 0.85em;
    }

    .boton_general {
        min-width: 150px;
        padding: 10px 20px;
    }
}

@media (max-width: 768px) {
    .container_borrar_servicio {
        margin: 20px auto;
        padding: 10px;
        gap: 15px;
    }

    h1 {
        font-size: 1.5em;
        margin-bottom: 15px;
    }

    .container_consultar {
        flex-direction: column;
        width: 90%;
        gap: 10px;
    }

    .input_consulta {
        width: 100%;
        padding: 8px;
        font-size: 0.9em;
    }

    .boton_consulta {
        width: 100%;
        min-width: auto;
        margin-left: 0;
        padding: 8px 10px;
        font-size: 0.9em;
    }

    .boton_general {
        width: 80%;
        max-width: 250px;
        padding: 10px;
        font-size: 0.95em;
    }

    .containerformulario {
        width: 90%;
        padding: 20px 10px 30px 10px;
        gap: 8px;
    }

    .containerformulario input {
        width: 95%;
        padding: 5px 7px;
        font-size: 0.8em;
    }

    p::before {
        font-size: 0.8em;
        margin-bottom: 2px;
    }

    p {
        padding: 0 5px;
        margin-top: 3px;
        margin-bottom: 3px;
    }
}

@media (max-width: 480px) {
    .container_borrar_servicio {
        padding: 8px;
        margin: 10px auto;
        gap: 10px;
    }

    h1 {
        font-size: 1.2em;
        margin-bottom: 10px;
    }

    .input_consulta,
    .boton_consulta {
        font-size: 0.8em;
    }

    .boton_consulta {
        padding: 6px 8px;
    }

    .boton_general {
        padding: 6px 10px;
        font-size: 0.8em;
    }

    .containerformulario {
        padding: 12px 8px 20px 8px;
        gap: 5px;
    }

    .containerformulario input {
        width: 95%;
        font-size: 0.75em;
        padding: 4px 6px;
    }

    p::before {
        font-size: 0.75em;
        margin-bottom: 1px;
    }

    p {
        padding: 0;
        margin-top: 2px;
        margin-bottom: 2px;
    }
}
</style>