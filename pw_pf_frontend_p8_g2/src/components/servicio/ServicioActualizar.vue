<template>
    <div class="container_consultar_servicio">
        <div class="container_consultar">
            <input class="input_consulta" type="number" v-model="identificador"
                :placeholder="`Ingrese el id del ${category === 'producto' ? 'Producto' : 'Servicio'}...`" :disabled="deshabilitado" />

            <button class="boton_consulta" @click="ObtenerServicioPorId()"
                :disabled="deshabilitadoBotonConsultar">Consultar</button>
        </div>

        <h1>Actualice el {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>

        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="servicio.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Categoria:">
                <input type="text" v-model="servicio.categoria" :disabled="deshabilitado" />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="servicio.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="servicio.precio" :disabled="deshabilitado" />
            </p>
        </div>

        <button class="boton_consulta actulizar" @click="actulizarParcial()"
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
import {
    consultarProductosIdFachada,
    actulizarParcialPorIdFachada
} from "@/clients/ProductoClient"; 

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
        };
    },

    methods: {
        async ObtenerServicioPorId() {
            let aux = await consultarProductosIdFachada(this.identificador);

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
        },

        async actulizarParcial() {
            const servicioToBody = { 
                nombre: this.servicio.nombre,
                categoria: this.category, 
                codigoBarras: this.servicio.codigoBarras,
                precio: this.servicio.precio,
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
        },
    },
};
</script>

<style scoped>
.container_consultar_servicio {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%; 
    padding: 20px;
    box-sizing: border-box;
    gap: 20px; 
}
.container_consultar_servicio h1 {
    color: #003366; 
    margin-bottom: 25px;
    font-size: 2.2em;
    text-align: center;
}

.container_consultar {
    display: flex;
    align-items: center;
    gap: 15px;
    width: 90%; 
    max-width: 550px; 
    margin-bottom: 20px;
    padding: 0; 
    box-sizing: border-box;
}

.input_consulta {
    flex-grow: 1;
    padding: 12px; 
    border: 1px solid #ccc;
    border-radius: 8px; 
    font-size: 1em;
    box-sizing: border-box;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
    background-color: white;
}

.input_consulta::-webkit-outer-spin-button,
.input_consulta::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}

.input_consulta:focus {
    outline: none;
    border-color: #0c3e80; 
    box-shadow: 0 0 8px rgba(12, 62, 128, 0.3); 
}

.input_consulta:disabled {
    background-color: #e9ecef;
    cursor: not-allowed;
    opacity: 0.7;
}

.boton_consulta {
    padding: 12px 25px;
    font-size: 1.1em; 
    font-weight: bold;
    letter-spacing: 1px; 
    background-color: #07265c;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.1s ease;
    white-space: nowrap; 
}

.boton_consulta:hover {
    background-color: #217dbb;
    transform: translateY(-2px);
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.boton_consulta:active {
    background-color: #217dbb;
    transform: translateY(0);
    box-shadow: none;
}
.containerformulario {
    width: 90%; 
    max-width: 550px; 
    border: 10px double #003366; 
    border-end-end-radius: 100px;
    border-start-start-radius: 100px;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.516); 
    background-color: #f4f6f8; 
    padding: 40px 30px; 
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
    gap: 15px; 
    margin-top: 20px; 
}

p[type] {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
}

p[type]::before {
    content: attr(type);
    font-weight: bold;
    color: #333;
    margin-bottom: 8px;
    font-size: 1.1em;
}


p[type] input {
    width: 100%; 
    padding: 12px; 
    border: 1px solid #ccc;
    border-radius: 8px; 
    font-size: 1em; 
    box-sizing: border-box;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
    background-color: white;
}

p[type] input:focus {
    outline: none;
    border-color: #0c3e80;
    box-shadow: 0 0 8px rgba(12, 62, 128, 0.3);
}

p[type] input:disabled {
    background-color: #e9ecef;
    cursor: not-allowed;
    opacity: 0.7;
}


.boton_consulta.actulizar {
    width: 90%;
    max-width: 250px;
    padding: 12px 25px;
    font-size: 1.4em;
    font-weight: bold;
    letter-spacing: 2px;
    background-color: #07265c; 
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.1s ease;
    margin-top: 20px; 
}

.boton_consulta.actulizar:hover {
    background-color: #002244; 
    transform: translateY(-2px);
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2);
}

.boton_consulta.actulizar:active {
    background-color: #004488; 
    transform: translateY(0);
    box-shadow: none;
}

.mensaje-exito-general {
    margin-top: 30px;
    text-align: center;
    padding: 20px;
    background-color: #d4edda; 
    color: #155724; 
    border: 1px solid #c3e6cb;
    border-radius: 8px;
    width: 90%;
    max-width: 400px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
}

.mensaje-exito-general h2 {
    font-size: 1.1em; 
    color: #155724;
    margin-top: 0;
    margin-bottom: 0;
}

.mensaje-error-general {
    margin-top: 30px; 
    text-align: center;
    padding: 20px;
    background-color: #f8d7da; 
    color: #721c24; 
    border: 1px solid #f5c6cb;
    border-radius: 8px;
    width: 90%;
    max-width: 400px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
}

.mensaje-error-general h1 { 
    font-size: 1.1em; 
    color: #721c24; 
    margin-bottom: 0; 
}

@media screen and (max-width: 768px) {
    .container_consultar_servicio h1 {
        font-size: 1.8em;
    }

    .container_consultar {
        width: 95%;
        max-width: 400px;
        gap: 10px;
        padding: 0;
    }

    .input_consulta {
        padding: 8px 10px;
        font-size: 0.95em;
    }

    .boton_consulta {
        padding: 8px 15px;
        font-size: 1em;
    }

    .containerformulario {
        width: 95%;
        max-width: 450px;
        padding: 30px 20px;
        gap: 20px;
    }

    p[type]::before {
        font-size: 1em;
    }

    p[type] input {
        padding: 10px;
        font-size: 0.95em;
    }

    .boton_consulta.actulizar {
        padding: 10px 20px;
        font-size: 1.2em;
        max-width: 200px;
    }

    .mensaje-exito-general, .mensaje-error-general {
        padding: 8px 15px;
        font-size: 0.9em;
    }
    .mensaje-exito-general h2, .mensaje-error-general h1 {
        font-size: 1.2em;
    }
}

@media screen and (max-width: 480px) {
    .container_consultar_servicio h1 {
        font-size: 1.5em;
        margin-bottom: 10px;
    }

    .container_consultar {
        flex-direction: column;
        width: 100%;
        max-width: 300px;
        gap: 10px;
        padding: 0;
    }

    .input_consulta {
        width: 100%;
    }

    .boton_consulta {
        width: 100%;
    }

    .containerformulario {
        width: 100%;
        padding: 20px 15px;
        border: 5px double #003366;
        border-end-end-radius: 50px;
        border-start-start-radius: 50px;
        gap: 15px;
    }

    p[type]::before {
        font-size: 0.9em;
    }

    p[type] input {
        padding: 8px;
        font-size: 0.9em;
    }

    .boton_consulta.actulizar {
        padding: 8px 15px;
        font-size: 1em;
        max-width: 180px;
    }

    .mensaje-exito-general, .mensaje-error-general {
        padding: 7px 10px;
        width: 100%;
    }
    .mensaje-exito-general h2, .mensaje-error-general h1 {
        font-size: 1.0em;
    }
}
</style>