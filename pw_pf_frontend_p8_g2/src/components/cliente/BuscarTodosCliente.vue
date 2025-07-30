<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <button class="boton_consulta" @click="obtenerTodosClientes()">
        Consultar todos
      </button>
    </div>
       <!-- Tabla de todos los clientes -->
    <div v-if="clientes.length > 0">
      <h1>Lista de Todos los Clientes</h1>
      <table class="tabla-clientes">
        <thead>
          <tr>
            <th>Cédula</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Razón Social</th>
            <th>Dirección</th>
            <th>Teléfono</th>
            <th>Correo</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(cliente, index) in clientes" :key="index">
            <td data-label="Cédula">{{ cliente.cedula }}</td>
            <td data-label="Nombre">{{ cliente.nombre }}</td>
            <td data-label="Apellido">{{ cliente.apellido }}</td>
            <td data-label="Razón Social">{{ cliente.razonSocial }}</td>
            <td data-label="Dirección">{{ cliente.direccion }}</td>
            <td data-label="Teléfono">{{ cliente.telefono }}</td>
            <td data-label="Correo">{{ cliente.correo }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

</template>

<script>
import { consultarTodosClientesFachada } from "@/clients/ClienteClient.js";

export default {
  data() {
    return {
      identificador: null,
      cliente: {
        cedula: null,
        nombre: null,
        apellido: null,
        razonSocial: null,
        direccion: null,
        telefono: null,
        correo: null,        
      },
      clientes:[],
      existeCliente: true,
      deshabilitado: false,
    };
  },

 methods: {
  async obtenerTodosClientes() {
    try {
      const clientes = await consultarTodosClientesFachada(); 
      if (clientes && Array.isArray(clientes)) {
        this.clientes = clientes.map(cliente => ({
          cedula: cliente.cedula,
          nombre: cliente.nombre,
          apellido: cliente.apellido,
          razonSocial: cliente.razonSocial,
          direccion: cliente.direccion,
          telefono: cliente.telefono,
          correo: cliente.correo,
        }));
      }
    } catch (error) {
      console.error('Error al obtener todos los clientes:', error);
    }
  }
  },
};
</script>
<style scoped>
.container_consultarcliente {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  padding: 20px;
  box-sizing: border-box;
  gap: 20px; 
}

.container_consultar {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  max-width: 400px;
  margin-bottom: 30px;
  gap: 10px;
}

.boton_consulta {
  padding: 10px 24px;
  border-radius: 8px;
  background-color: #07265c;
  color: #fff;
  font-weight: bold;
  border: none;
  font-family: "Segoe UI", Arial, sans-serif;
  font-size: 1em;
  cursor: pointer;
  transition: background 0.2s, transform 0.1s;
  box-shadow: 0 2px 8px rgba(52, 152, 219, 0.08);
}

.boton_consulta:hover {
  background-color: #217dbb;
  transform: scale(1.03);
}

.boton_consulta:active {
  background-color: #176093;
}

h1 {
  font-size: 1.3em;
  color: #2c3e50;
  margin-bottom: 18px;
  margin-top: 0;
  font-weight: 600;
  letter-spacing: 1px;
}

p {
  margin: 0;
  font-weight: 500;
  color: #34495e;
  font-size: 1em;
  display: flex;
  flex-direction: column;
}

p::before {
  content: attr(type);
  font-size: 0.95em;
  color: #7f8c8d;
  margin-bottom: 4px;
  font-weight: 400;
}

.containerformulario input {
  padding: 9px 12px;
  border: 1px solid #b0c4de;
  border-radius: 7px;
  font-size: 1em;
  background: #f8fafc;
  transition: border-color 0.2s;
}

.containerformulario input:focus {
  border-color: #3498db;
  background: #fff;
  outline: none;
}

.tabla-clientes {
  width: 80%;
  margin-top: 20px;
  border-collapse: collapse;
}

.tabla-clientes th,
.tabla-clientes td {
  padding: 12px;
  text-align: left;
  border: 1px solid #ddd;
}

.tabla-clientes th {
  background-color: #07265c;
  color: #ddd;
  font-weight: bold;
}

.tabla-clientes tr:nth-child(even) {
  background-color: #f9f9f9;
}


/* RESPONSIVE */
@media (max-width: 700px) {
  .container_consultar {
    flex-direction: column; 
    width: 100%;
    gap: 15px; 
  }

  .input_consulta {
    width: 100%; 
    padding: 12px 16px; 
    font-size: 0.9em; 
  }

  .boton_consulta {
    width: 90%;
    max-width: 250px;
    padding: 12px 16px; 
    font-size: 1.1em; 
  }
  .container-navbar-cliente {
      width: 98%;
      margin: 20px auto;
  }
  .container-navbar-cliente nav {
      flex-direction: column;
      align-items: stretch;
      border-radius: 8px;
  }
  .nav-item {
      padding: 12px 10px;
      font-size: 1em;
      border-right: none;
      border-bottom: 1px solid rgba(0, 0, 0, 0.1);
      border-radius: 0;
  }
  .nav-item:first-child {
      border-top-left-radius: 8px;
      border-top-right-radius: 8px;
      border-bottom-left-radius: 0;
      border-bottom-right-radius: 0;
  }
  .nav-item:last-child {
      border-bottom: none;
      border-bottom-left-radius: 8px;
      border-bottom-right-radius: 8px;
      border-top-left-radius: 0;
      border-top-right-radius: 0;
  }
  .tabla-clientes {
    width: 100%;
    border-collapse: collapse;
  }

  .tabla-clientes thead {
    display: none;
  }

  .tabla-clientes tr {
    display: flex;
    flex-direction: column;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    border-radius: 5px;
    overflow: hidden;
  }

  .tabla-clientes td {
    display: flex;
    padding: 8px;
    border-bottom: 1px solid #ddd;
  }

  .tabla-clientes td::before {
    content: attr(data-label);
    min-width: 100px;
    font-weight: bold;
    background-color: #07265c;
    color: white;
    padding: 8px;
    display: inline-block;
    margin-right: 10px;
    border-radius: 5px 0 0 5px;
  }

  .tabla-clientes td:last-child {
    border-bottom: none;
  }
  
@media (max-width: 400px) {
  .container_consultar {
    flex-direction: column; 
    width: 100%;
    gap: 15px; 
  }

  .input_consulta {
    width: 100%; 
    padding: 12px 16px; 
    font-size: 0.9em; 
  }

  .boton_consulta {
    width: 90%;
    max-width: 250px;
    padding: 12px 16px; 
    font-size: 1.1em; 
  }
  .container-navbar-cliente {
        width: 95%; 
        margin: 15px auto; 
  }
  .container-navbar-cliente nav {
      flex-direction: column;
      align-items: center; 
      padding: 10px 0; 
  }
  .nav-item {
      padding: 10px 8px; 
      font-size: 0.9em; 
      width: 100%; 
      text-align: center; 
      border-bottom: 1px solid rgba(0, 0, 0, 0.1); 
  }
  .nav-item:first-child {
      border-top-left-radius: 8px;
      border-top-right-radius: 8px;
      border-bottom-left-radius: 0;
      border-bottom-right-radius: 0;
  }
  .nav-item:last-child {
      border-bottom: none;
      border-bottom-left-radius: 8px;
      border-bottom-right-radius: 8px;
      border-top-left-radius: 0;
      border-top-right-radius: 0;
  }
    .tabla-clientes {
    width: 100%;
    border-collapse: collapse;
  }

  .tabla-clientes thead {
    display: none;
  }

  .tabla-clientes tr {
    display: flex;
    flex-direction: column;
    border: 1px solid #ddd;
    margin-bottom: 10px;
    border-radius: 5px;
    overflow: hidden;
  }

  .tabla-clientes td {
    display: flex;
    padding: 8px;
    border-bottom: 1px solid #ddd;
  }

  .tabla-clientes td::before {
    content: attr(data-label);
    min-width: 100px;
    font-weight: bold;
    background-color: #07265c;
    color: white;
    padding: 8px;
    display: inline-block;
    margin-right: 10px;
    border-radius: 5px 0 0 5px;
  }

  .tabla-clientes td:last-child {
    border-bottom: none;
  }
}
}

</style>