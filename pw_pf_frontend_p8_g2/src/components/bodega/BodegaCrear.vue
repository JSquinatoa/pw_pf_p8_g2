<template>
  <div class="container_bcrear">
    <h1>Crear una nueva Bodega</h1>
    <div class="containerformulario">
      <p type="Codigo:">
        <input type="text" v-model="bodega.codigo" :disabled="deshabilitado"/>
      </p>
      <p type="Nombre:">
        <input type="text" v-model="bodega.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Direccion:">
        <input type="text" v-model="bodega.ubicacion" :disabled="deshabilitado"/>
      </p>

    </div>
    <button class="boton_crear" @click="guardarBodega()">
      Crear
    </button>
    <div v-if="exito">
      <h1>Bodega exitosamente Creada </h1>
    </div>
  </div>
</template>

<script>
import { insertarBodegaFachada } from "@/clients/BodegaClient";
export default {

    data() {
        return {
            id: null,
            bodega: {
                codigo: "",
                nombre: "",
                ubicacion: ""
            },
            exito: false,
            deshabilitado: false
        }
    }, 
    methods:{
        async guardarBodega() {
            try {
                this.exito = true;
                this.deshabilitado = true;
                insertarBodegaFachada(this.bodega);
                console.log("Bodega creada:", this.bodega);
            } catch (error) {
                console.error("Error al crear la bodega:", error);
            }
        }
    }

}
</script>

<style>

.container_bcrear {
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