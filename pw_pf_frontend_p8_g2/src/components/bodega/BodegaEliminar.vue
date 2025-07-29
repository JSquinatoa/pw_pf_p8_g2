<template>
  <div class="container-eliminar-bodega">
    <div class="container_eliminar">
      <input
        class="input_consulta"
        type="number"
        v-model="id"
        placeholder="Ingrese el codigo de la bodega a eliminar"
      />
      <button class="boton_consulta" @click="obtenerBodegaPorId()">
        Consultar
      </button>
    </div>
    <h1>Borrar Bodega</h1>
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
    <button class="boton_consulta actulizar" @click="eliminarBodega()" v-if="mostrarEliminar">
      Eliminar
    </button>
    <div v-if="noExisteBodega">
      <h1>La bodega con el codigo {{ id }} no existe, vuelva a intentarlo</h1>
    </div>
    <div v-if="exitoEliminar">
      <h1>
        La bodega con el codigo {{ id }} fue removida exitosamente
      </h1>
    </div>
  </div>
</template>

<script>
import { consultarBodegaPorIdFachada, eliminarBodegaFachada } from "@/clients/BodegaClient";
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
      noExisteBodega: false,
      exitoActulizar: false,
      mostrarEliminar: false,
    };
  },
  methods:{

    async obtenerBodegaPorId(){
        const bodegaPorId = await consultarBodegaPorIdFachada(this.id);
        
        if (bodegaPorId) {
        this.bodega.codigo = bodegaPorId.codigo;
        this.bodega.nombre = bodegaPorId.nombre;
        this.bodega.ubicacion = bodegaPorId.ubicacion;
        this.deshabilitarCodigo = true;
        this.mostrarEliminar = true;
        this.deshabilitado = true;
        }else {
        this.existeBodega = true;
        }
    },
    async eliminarBodega(){
        await eliminarBodegaFachada(this.id);
        this.exitoActulizar = true;
        this.deshabilitado = true;
        this.bodega.codigo = "";
        this.bodega.nombre = "";
        this.bodega.ubicacion = "";
    }
  },
  

}
</script>

<style scoped>
.container-eliminar-bodega {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  gap: 10px;
}

.container_eliminar {
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