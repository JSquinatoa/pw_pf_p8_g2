<template>
  <div class="container_consultarcliente">
    <h1>Crear un nuevo Impuesto</h1>
    <div class="containerformulario">
      <p type="Nombre:">
        <input type="text" v-model="impuesto.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Porcentaje:">
        <input type="number" v-model="impuesto.porcentaje" :disabled="deshabilitado"/>
      </p>
    </div>
    <button class="boton_opcion" @click="guardarImpuesto()">
      Crear
    </button>
    <div v-if="exito">
      <h2>Impuesto Correctamente Guardado</h2>
    </div>
  </div>
</template>

<script>
import { guardarFachada } from "@/clients/ImpuestoClient";
import "@/css/EstiloGenerico.css";

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

<style>

</style>
