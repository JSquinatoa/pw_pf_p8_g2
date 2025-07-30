<template>
    <div class="container_guardar_producto">
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
        </div>

        <button class="boton_general guardar_producto" @click="guardarProductos()">Crear</button>

        <div v-if="exito" class="mensaje-exito">
            <h2>{{ category === 'producto' ? 'Producto' : 'Servicio' }} Correctamente Guardado</h2>
        </div>
        <div v-if="errorMensaje" class="mensaje-error">
            <h2>{{ errorMensaje }}</h2>
        </div>
    </div>
</template>

<script>
import { guardarFachada } from "@/clients/ProductoClient.js";

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
            exito: false,
            deshabilitado: false,
            errorMensaje: null, 
        };
    },
    methods: {
        async guardarProductos() {
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
            };

            this.deshabilitado = true; 

            try {
                await guardarFachada(productoToBody);
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
            this.producto.nombre = null;
            this.producto.codigoBarras = null;
            this.producto.precio = null;
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
                this.producto.categoria = newCategory;
            }
        }
    }
};
</script>

<style scoped>
.container_guardar_producto {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%; 
    padding: 20px;
    box-sizing: border-box;
    gap: 20px; 
}
.container_guardar_producto h1 {
    color: #003366; 
    margin-bottom: 25px;
    font-size: 2.2em;
    text-align: center;
}

h2 {
    margin-top: 15px;
    text-align: center;
    font-size: 1.2em;
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
.boton_general.guardar_producto {
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

.boton_general.guardar_producto:hover {
    background-color: #217dbb; 
    transform: translateY(-2px); 
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2); 
}

.boton_general.guardar_producto:active {
    background-color: #217dbb;
    transform: translateY(0); 
    box-shadow: none;
}

.mensaje-exito {
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
}

.mensaje-exito h2 { 
    font-size: 1.5em; 
    color: #155724; 
    margin-bottom: 0; 
}


.mensaje-error {
    margin-top: 15px; 
    padding: 10px 20px;
    border-radius: 5px;
    font-weight: bold;
    text-align: center;
    background-color: #f8d7da; 
    color: #721c24; 
    border: 1px solid #f5c6cb;
    width: 90%;
    max-width: 400px;
    box-sizing: border-box;
}

.mensaje-error h2 { 
    font-size: 1.2em; 
    color: #721c24; 
    margin-top: 0;
    margin-bottom: 0;
}

@media screen and (max-width: 768px) {
    .container_guardar_producto h1 {
        font-size: 1.8em;
    }
    .containerformulario {
        width: 95%;
        max-width: 450px;
        padding: 30px 20px;
    }
    p[type]::before {
        font-size: 1em;
    }
    p[type] input {
        padding: 10px;
        font-size: 0.95em;
    }
    .boton_general.guardar_producto {
        padding: 10px 20px;
        font-size: 1.2em;
        max-width: 200px;
    }
    .mensaje-exito h2 { 
        font-size: 1.5em;
    }
    .mensaje-error h2 { 
        font-size: 0.9em;
    }
    .mensaje-exito, .mensaje-error { 
        padding: 8px 15px;
        font-size: 0.9em; 
    }
}

@media screen and (max-width: 480px) {
    .container_guardar_producto h1 {
        font-size: 1.5em;
        margin-bottom: 15px;
    }
    .containerformulario {
        width: 100%;
        padding: 15px;
        border: 5px double #003366;
        border-end-end-radius: 50px;
        border-start-start-radius: 50px;
    }
    p[type]::before {
        font-size: 0.9em;
    }
    p[type] input {
        padding: 8px;
        font-size: 0.9em;
    }
    .boton_general.guardar_producto {
        padding: 8px 15px;
        font-size: 1em;
        max-width: 180px;
    }
    .mensaje-exito {
        padding: 15px;
        width: 100%;
    }
    .mensaje-exito h2 {
        font-size: 1.3em;
    }
    .mensaje-error {
        padding: 7px 10px;
        width: 100%;
    }
    .mensaje-error h2 { 
        font-size: 0.85em;
    }
}
</style>