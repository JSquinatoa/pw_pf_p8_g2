<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <input
        class="input_consulta"
        type="number"
        v-model="identificador"
        placeholder="Ingrese el id del Impuesto..."
        :disabled="deshabilitado"
      />
      <button class="boton_consulta" @click="ObtenerImpuestoPorId()">
        Consultar
      </button>
    </div>
    <h1>Actualice el Impuesto</h1>
    <div class="containerformulario">
      <p type="Nombre:">
        <input type="text" v-model="impuesto.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Porcentaje:">
        <input type="number" v-model="impuesto.porcentaje" :disabled="deshabilitado"/>
      </p>
    </div>
    <button class="boton_opcion" @click="actulizarParcial()">
      Actualizar
    </button>
    <div v-if="!existeImpuesto">
      <h1>El impuesto con el id {{ identificador }} no existe</h1>
    </div>
    <div v-if="exitoActulizar">
      <h2>
        El impuesto con el id {{ identificador }} se actualizó correctamente
      </h2>
    </div>
  </div>
</template>

<script>
import {
  consultarImpuestosIdFachada,
  actulizarParcialPorIdFachada,
} from "@/clients/ImpuestoClient";
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
      exitoActulizar: false,
      deshabilitado: false,
    };
  },

  methods: {
    async ObtenerImpuestoPorId() {
      this.deshabilitado = true;
      this.existeImpuesto = true;
      this.exitoActulizar = false;

      try {
        let aux = await consultarImpuestosIdFachada(this.identificador);
        if (aux === null) {
          this.existeImpuesto = false;
          setTimeout(() => {
            this.existeImpuesto = true;
            this.reiniciarVaraibles();
            this.deshabilitado = false;
          }, 3000);
          return;
        }

        this.impuesto.id = aux.id;
        this.impuesto.nombre = aux.nombre;
        this.impuesto.porcentaje = aux.porcentaje;
        this.deshabilitado = false;
      } catch (error) {
        console.error("Error al obtener impuesto por ID:", error);
        this.reiniciarVaraibles();
        this.deshabilitado = false;
        this.existeImpuesto = false;
        setTimeout(() => { this.existeImpuesto = true; }, 3000);
      }
    },

    async actulizarParcial() {
      if (!this.impuesto.id || !this.impuesto.nombre || this.impuesto.porcentaje === null) {
        console.error("Por favor, consulte un Impuesto válido y complete todos los campos antes de actualizar.");
        return;
      }

      const impuestoToBody = {
        nombre: this.impuesto.nombre,
        porcentaje: this.impuesto.porcentaje,
      };

      try {
        await actulizarParcialPorIdFachada(
          impuestoToBody,
          this.identificador
        );
        this.deshabilitado = true;
        this.exitoActulizar = true;
        setTimeout(() => {
          this.exitoActulizar = false;
          this.deshabilitado = false;
          this.reiniciarVaraibles();
        }, 3000);
      } catch (error) {
        console.error("Error al actualizar Impuesto:", error);
      }
    },

    reiniciarVaraibles() {
      this.identificador = null;
      this.impuesto.id = null;
      this.impuesto.nombre = null;
      this.impuesto.porcentaje = null;
    },
  },
};
</script>

<style >

</style>