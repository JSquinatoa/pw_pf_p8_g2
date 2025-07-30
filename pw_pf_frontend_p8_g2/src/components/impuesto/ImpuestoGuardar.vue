<template>
  <div class="container_guardar_impuesto">
    <h1>Crear un nuevo Impuesto</h1>
    <div class="containerformulario">
      <p type="Nombre:">
        <input type="text" v-model="impuesto.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Porcentaje:">
        <input type="number" v-model="impuesto.porcentaje" :disabled="deshabilitado"/>
      </p>
    </div>
    <button class="boton_general guardar_impuesto" @click="guardarImpuesto()">
      Crear
    </button>
    <div v-if="exito">
      <h2>Impuesto Correctamente Guardado</h2>
    </div>
  </div>
</template>

<script>
import { guardarFachada } from "@/clients/ImpuestoClient";

export default {
  data() {
    return {
      identificador: null,
      impuesto: {
        nombre: null,
        porcentaje: null,
      },
      exito: false,
      deshabilitado: false,
    };
  },

  methods: {
    async guardarImpuesto() {
      const impuestoToBody = {
        nombre: this.impuesto.nombre,
        porcentaje: this.impuesto.porcentaje,
      };

      if (!this.deshabilitado) {
        try {
            await guardarFachada(impuestoToBody);
            this.exito = true;
            this.deshabilitado = true;
            setTimeout(() => {
                this.exito = false;
                this.deshabilitado = false;
                this.reiniciarVaraibles();
            }, 3000);
        } catch (error) {
            console.error("Error al guardar el impuesto:", error);
        }
      }
    },

    reiniciarVaraibles() {
      this.impuesto.nombre = null;
      this.impuesto.porcentaje = null;
    },
  },
};
</script>
<style scoped>
/* Estilos generales del contenedor principal */
.container_guardar_impuesto {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%; /* Ocupa todo el ancho disponible */
    padding: 20px;
    box-sizing: border-box;
    gap: 20px; /* Espacio entre los elementos principales */
    /* Eliminados background-color, border-radius, box-shadow de aquí */
}

/* Estilo para el título principal (h1) */
.container_guardar_impuesto h1 {
    color: #003366; /* Color azul oscuro */
    margin-bottom: 25px;
    font-size: 2.2em;
    text-align: center;
}

/* Estilos del contenedor del formulario (containerformulario) */
.containerformulario {
    width: 90%; /* Ancho responsivo, ajustado de 50% */
    max-width: 550px; /* Ancho máximo para pantallas grandes */
    border: 10px double #003366; /* Borde doble azul oscuro */
    border-end-end-radius: 100px; /* Esquinas redondeadas distintivas */
    border-start-start-radius: 100px;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.516); /* Sombra para profundidad */
    background-color: #f4f6f8; /* Fondo ligeramente gris */
    padding: 40px 30px; /* Espaciado interno */
    box-sizing: border-box;
    display: flex; /* Añadido para que los p[type] se apilen con gap */
    flex-direction: column; /* Añadido para que los p[type] se apilen con gap */
    gap: 15px; /* Espacio entre los elementos del formulario */
}

/* Estilos para las etiquetas de los campos (usando el atributo type de <p>) */
p[type] {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    width: 100%;
}

p[type]::before {
    content: attr(type); /* El texto de la etiqueta viene del atributo 'type' */
    font-weight: bold;
    color: #333;
    margin-bottom: 8px;
    font-size: 1.1em;
}

/* Estilos para los campos de entrada (input dentro de p[type]) */
p[type] input {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 8px;
    font-size: 1em;
    box-sizing: border-box;
    transition: border-color 0.3s ease, box-shadow 0.3s ease; /* Transiciones suaves */
    background-color: white;
}

p[type] input:focus {
    outline: none;
    border-color: #0c3e80; /* Borde azul al enfocar */
    box-shadow: 0 0 8px rgba(12, 62, 128, 0.3); /* Sombra al enfocar */
}

p[type] input:disabled {
    background-color: #e9ecef; /* Fondo gris para campos deshabilitados */
    cursor: not-allowed;
    opacity: 0.7;
}

/* Estilos para el botón "Crear" (boton_general guardar_impuesto) */
.boton_general.guardar_impuesto {
    width: 90%;
    max-width: 250px;
    padding: 12px 25px;
    font-size: 1.4em;
    font-weight: bold;
    letter-spacing: 2px;
    background-color: #07265c; /* Color azul oscuro */
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.1s ease; /* Transiciones suaves */
    margin-top: 20px;
}

.boton_general.guardar_impuesto:hover {
    background-color: #217dbb; /* Color azul más claro al pasar el ratón */
    transform: translateY(-2px); /* Pequeño levantamiento */
    box-shadow: 0 5px 10px rgba(0, 0, 0, 0.2); /* Sombra al pasar el ratón */
}

.boton_general.guardar_impuesto:active {
    background-color: #217dbb;
    transform: translateY(0); /* Vuelve a la posición normal al hacer clic */
    box-shadow: none;
}

/* Estilos para el mensaje de éxito (mensaje-exito-general) */
.mensaje-exito-general {
    margin-top: 30px;
    text-align: center;
    padding: 20px;
    background-color: #d4edda; /* Fondo verde claro para éxito */
    color: #155724; /* Texto verde oscuro */
    border: 1px solid #c3e6cb;
    border-radius: 8px;
    width: 90%;
    max-width: 400px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
}

.mensaje-exito-general h2 { /* Apunta a tu h2 original */
    font-size: 1.5em; /* Tamaño de fuente consistente */
    color: #155724; /* Color del texto del mensaje de éxito */
    margin-bottom: 0; /* No hay margen inferior si solo hay un h2 */
}

/* Si tuvieras un mensaje de error, usarías una clase similar a esta:
.mensaje-error-general {
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
.mensaje-error-general h2 {
    font-size: 1.5em;
    color: #721c24;
    margin-top: 0;
    margin-bottom: 0;
}
*/

/* --- Media Queries para Responsividad --- */

/* Tablets y pantallas medianas (max-width: 768px) */
@media screen and (max-width: 768px) {
    .container_guardar_impuesto h1 {
        font-size: 1.8em;
    }
    .containerformulario {
        width: 95%; /* Ajustado de 80% */
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
    .boton_general.guardar_impuesto {
        padding: 10px 20px;
        font-size: 1.2em;
        max-width: 200px;
    }
    .mensaje-exito-general h2 {
        font-size: 1.2em;
    }
    .mensaje-exito-general {
        padding: 8px 15px;
    }
}

/* Teléfonos y pantallas pequeñas (max-width: 480px) */
@media screen and (max-width: 480px) {
    .container_guardar_impuesto {
        padding: 8px;
        /* margin: 10px auto; */ /* Eliminado, ya no es necesario con width: 100% y align-items: center */
        gap: 10px;
    }
    .container_guardar_impuesto h1 {
        font-size: 1.5em;
        margin-bottom: 15px;
    }
    .containerformulario {
        width: 100%; /* Ajustado de 80% */
        padding: 15px;
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
    .boton_general.guardar_impuesto {
        padding: 8px 15px;
        font-size: 1em;
        max-width: 180px;
    }
    .mensaje-exito-general h2 {
        font-size: 1.0em;
    }
    .mensaje-exito-general {
        padding: 7px 10px;
        width: 100%;
    }
}
</style>
