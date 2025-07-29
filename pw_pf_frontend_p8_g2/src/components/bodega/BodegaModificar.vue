<template>
  <div class="container-consultar-bodega">
    <div class="container_consultar">
      <input
        class="input_consulta"
        type="number"
        v-model="id"
        placeholder="Ingrese el codigo de la bodega"
        :disabled="deshabilitado"
      />
      <button class="boton_consulta" @click="obtenerBodegaPorId()">
        Consultar
      </button>
    </div>
    <h1>Actualice la bodega</h1>
    <div class="containerformulario">
      <p type="Codigo:">
        <input type="text" v-model="bodega.codigo" :disabled="deshabilitarCodigo"/>
      </p>
      <p type="Nombre:">
        <input type="text" v-model="bodega.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Direccion:">
        <input type="text" v-model="bodega.ubicacion" :disabled="deshabilitado"/>
      </p>

    </div>
    <button class="boton_consulta actulizar" @click="actualizarParcial()">
      Actualizar
    </button>
    <div v-if="!existeBodega">
      <h1>La bodega con el id {{ id }} no existe, vuelva a intentarlo</h1>
    </div>
    <div v-if="exitoActulizar">
      <h1>
        El estudiante con el id {{ id }} Se actulizo Correctamente
      </h1>
    </div>
  </div>
</template>

<script>
import { consultarBodegaPorIdFachada, actualizarBodegaFachada } from "@/clients/BodegaClient";
export default {
  data() {
    return {
      id: null,
      codigotemp: null,
      bodega: {
        codigo: "",
        nombre: "",
        ubicacion: ""
      },
      deshabilitarCodigo: false,
      deshabilitado: false,
      existeBodega: false,
      exitoActulizar: false
    };
  },
  methods:{

    async obtenerBodegaPorId(){
        const bodegaPorId = await consultarBodegaPorIdFachada(this.id);
        this.bodega.codigo = bodegaPorId.codigo;
        this.bodega.nombre = bodegaPorId.nombre;
        this.bodega.ubicacion = bodegaPorId.ubicacion;
        this.existeBodega = true;
        this.deshabilitarCodigo = true;
    },
    async actualizarParcial(){
    const bodegaBody = {};
            for (const indice in this.bodega) {
                const campo = this.bodega[indice];
                if (campo === '') {
                    bodegaBody[indice] = null;
                } else {
                    bodegaBody[indice] = campo;
                }
            }

            await actualizarBodegaFachada(this.id, bodegaBody) ;
            console.log('actualizar parcial');
  }
  },
  

}
</script>

<style scoped>
.container-consultar-bodega {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  gap: 10px;
}

.container_consultar {
  display: flex;
  justify-content: space-between;
  width: 25%;
}

.input_consulta {
  width: 70%;
  border: none;
  border-bottom: 1px solid;
}

.input_consulta::-webkit-outer-spin-button,
.input_consulta::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

.input_consulta:focus {
  outline: none;
  border-bottom: 2px solid;
  margin-top: 1px;
}

.boton_consulta {
  border-radius: 10px;
  width: 35%;
  font-family: "Courier New", Courier, monospace;
  font-weight: bold;
  transition: all 0.1s ease;
  cursor: pointer;
  margin-left: 10px;
  background-color: #2c3e50;
  color: white;
}

.boton_consulta:hover {
  background-color: #34495e;
  transform: scale(1.02);
}

.boton_consulta:active {
  background-color: #1a252f;
  transform: scale(0.99);
}

.actulizar {
  padding: 10px 20px;
  width: 200px;
  margin-top: 20px;
}

.containerformulario {
  display: flex;
  flex-direction: column;
  width: 30%;
  border: 10px double;
  border-end-end-radius: 100px;
  border-start-start-radius: 100px;
  background-color: #f4f6f8;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.516);
}

p::before {
  display: block;
  content: attr(type);
  text-align: left;
  padding-left: 19%;
}

.containerformulario input {
  width: 60%;
  background-color: white;
}
</style>