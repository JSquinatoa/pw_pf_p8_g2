<template>
    <div class="container-bconsultar">

        <button class="boton_consultar" @click="consultarBodegas()">
            Consultar Todos
        </button>
        <table v-if="todosBodegas">
            <thead>
                <tr>
                    <th>Identificacion</th>
                    <th>Codigo</th>
                    <th>Nombre</th>
                    <th>Ubicacion</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="bodega in todosBodegas" :key="bodega.id">
                    <td>{{ bodega.id }}</td>
                    <td>{{ bodega.codigo }}</td>
                    <td>{{ bodega.nombre }}</td>
                    <td>{{ bodega.ubicacion }}</td>
                </tr>
            </tbody>
        </table>

        <h2 v-if="!todosBodegas">
            Presiona el botón para consultar todas las bodegas
        </h2>

    </div>
</template>

<script>
import { consultarBodegasFachada } from "@/clients/BodegaClient";
export default {
    data() {
        return {
            todosBodegas: null,
        };
    },

    methods: {
        async consultarBodegas() {
            this.todosBodegas =  await consultarBodegasFachada();
            console.log(this.todosBodegas);
            
        },
    }

}
</script>

<style>

.container-bconsultar {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
}

table {
  width: 80%;
  border-collapse: collapse;
  margin-top: 1rem;
  font-family: Arial, sans-serif;
  font-size: 0.95rem;
  background-color: white;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  overflow: hidden;
}

h2 {
  margin-top: 50px;
  text-transform: uppercase;
}

thead {
  background-color: #003366;
  color: #ffffff;
}

thead th {
  padding: 0.75rem;
  text-align: center;
  font-weight: bold;
  border-bottom: 1px solid #ccc;
  border-right: 1px solid #ccc;
  text-transform: uppercase;
}

thead th:hover {
  background-color: #07294a;
}

tbody tr {
  border-bottom: 1px solid #e0e0e0;
  transition: background-color 0.3s;
}

tbody tr:hover {
  background-color: #f2f2f2;
}

tbody td {
  padding: 0.75rem;
  border-left: 1px solid #33333370;
  color: #333;
}

.boton_consultar {
  width: 40%;
  height: 40px;
  border-radius: 8px;
  font-size: 25px;
  letter-spacing: 5px;
  font-family: "Courier New", Courier, monospace;
  font-weight: bold;
  transition: all 0.1s ease;
  background-color: #0c3e80;
  color: white;
  cursor: pointer;
}

.boton_consultar:hover {
  background-color: #0b2855;
  transform: scale(1.01);
}

.boton_consultar:active {
  background-color: #041a3d;
  transform: scale(0.98);
}</style>