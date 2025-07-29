<template>
    <div class="container_guardar_servicio">
        <h1>Crear un nuevo {{ category === 'producto' ? 'Producto' : 'Servicio' }}</h1>
        <div class="containerformulario">
            <p type="Nombre:">
                <input type="text" v-model="item.nombre" :disabled="deshabilitado" />
            </p>
            <p type="Categoria:">
                <input type="text" v-model="item.categoria" :disabled="true" :value="category" />
            </p>
            <p type="Codigo de Barras:">
                <input type="text" v-model="item.codigoBarras" :disabled="deshabilitado" />
            </p>
            <p type="Precio:">
                <input type="number" v-model="item.precio" :disabled="deshabilitado" />
            </p>
        </div>

        <button class="boton_general guardar_servicio" @click="guardarServicio()">Crear</button>

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
                categoria: null,
                codigoBarras: null,
                precio: null,
            },
            exito: false,
            deshabilitado: false,
            errorMensaje: null, 
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
                categoria: this.category,
                codigoBarras: this.item.codigoBarras,
                precio: this.item.precio,
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
    }
};
</script>

<style scoped>
.container_guardar_servicio {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 90%;
    max-width: 960px;
    margin: 5px;
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


.mensaje-exito h2 {
    color: #4CAF50;
}

.mensaje-error h2 {
    color: #dc3545;
}


.containerformulario {
    display: flex;
    flex-direction: column;
    width: 50%;
    max-width: 550px;

    padding: 35px 20px 35px 20px;
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


.boton_general {
    padding: 10px 20px;
    border-radius: 10px;
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

.boton_general:hover {
    background-color: #34495e;
    transform: scale(1.02);
}

.boton_general:active {
    background-color: #1a252f;
    transform: scale(0.99);
}

.guardar_servicio {
    margin-top: 25px;
    background-color: #4CAF50;
    width: 200px;
}

.guardar_servicio:hover {
    background-color: #45a049;
}

.guardar_servicio:active {
    background-color: #3e8e41;
}


@media (max-width: 1024px) {
    .container_guardar_servicio {
        width: 95%;
        padding: 15px;
        gap: 15px;
    }

    .containerformulario {
        width: 80%;
        padding: 25px 15px 25px 15px;
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
}

@media (max-width: 768px) {
    .container_guardar_servicio {
        margin: 20px auto;
        padding: 10px;
        gap: 15px;
    }

    h1 {
        font-size: 1.5em;
        margin-bottom: 15px;
    }

    .containerformulario {
        width: 90%;
        padding: 20px 10px 20px 10px;
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

    .guardar_servicio {
        width: 150px;
        padding: 8px 15px;
        font-size: 0.9em;
    }
}

@media (max-width: 480px) {
    .container_guardar_servicio {
        padding: 8px;
        margin: 10px auto;
        gap: 10px;
    }

    h1 {
        font-size: 1.2em;
        margin-bottom: 10px;
    }

    .containerformulario {
        padding: 15px 8px 15px 8px;
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

    .guardar_servicio {
        width: 120px;
        padding: 6px 10px;
        font-size: 0.8em;
    }
}
</style>