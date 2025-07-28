<template>
  <div class="container_consultarcliente">
    <div class="container_consultar">
      <input
        class="input_consulta"
        type="number"
        v-model="identificador"
        placeholder="Ingrese la cédula del cliente"
        :disabled="deshabilitado"
      />
      <button class="boton_consulta" @click="ObtenerClientePorId()">
        Consultar
      </button>
    </div>
    <h1>Datos del Cliente</h1>
    <div class="containerformulario">
      <p type="Cédula:">
        <input type="number" v-model="cliente.cedula" :disabled="deshabilitado"/>
      </p>
      <p type="Nombre:">
        <input type="text" v-model="cliente.nombre" :disabled="deshabilitado"/>
      </p>
      <p type="Apellido:">
        <input type="text" v-model="cliente.apellido" :disabled="deshabilitado"/>
      </p>
      <p type="Razón Social:">
        <input type="text" v-model="cliente.razonSocial" :disabled="deshabilitado"/>
      </p>
      <p type="Dirección:">
        <input type="text" v-model="cliente.direccion" :disabled="deshabilitado"/>
      </p>
      <p type="Telefono:">
        <input type="number" v-model="cliente.telefono" :disabled="deshabilitado"/>
      </p>
      <p type="Correo electrónico:">
        <input type="email" v-model="cliente.correo" :disabled="deshabilitado"/>
      </p>
    </div>
    <div v-if="!existeCliente">
      <h1>El cliente con la cedula {{ identificador }} no existe</h1>
    </div>
  </div>
</template>

<script>
import { consultarClientePorIdFachada } from "@/clients/ClienteClient.js";

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
      existeCliente: true,
      deshabilitado: false,
    };
  },

 methods: {
  async ObtenerClientePorId() {
    try {
      let aux = await consultarClientePorIdFachada(this.identificador);
      if (!aux) {
        this.existeCliente = false;
        setTimeout(() => {
          this.existeCliente = true;
        }, 3000);
        return;
      }
      this.cliente.cedula = aux.cedula;
      this.cliente.nombre = aux.nombre;
      this.cliente.apellido = aux.apellido;
      this.cliente.razonSocial = aux.razonSocial;
      this.cliente.direccion = aux.direccion;
      this.cliente.telefono = aux.telefono;
      this.cliente.correo = aux.correo;
    } catch (error) {
      if (error.response && error.response.status === 404) {
        this.existeCliente = false;
        setTimeout(() => {
          this.existeCliente = true;
        }, 3000);
      }  
    }
  },
  },
};
</script>
<style scoped>
.container_consultarcliente {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-height: 80vh;
  background: linear-gradient(135deg, #e3f0ff 0%, #f9f9f9 100%);
  padding-top: 40px;
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

.input_consulta {
  flex: 1;
  padding: 10px 16px;
  border: 1px solid #b0c4de;
  border-radius: 8px;
  font-size: 1em;
  background: #fff;
  transition: border-color 0.2s;
}

.input_consulta:focus {
  outline: none;
  border-color: #3498db;
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

.containerformulario {
  display: flex;
  flex-direction: column;
  gap: 18px;
  width: 100%;
  max-width: 420px;
  background: #fff;
  border-radius: 18px;
  box-shadow: 0 4px 24px rgba(44, 62, 80, 0.08);
  padding: 32px 28px;
  margin-bottom: 20px;
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

/* RESPONSIVE */
@media (max-width: 700px) {
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
}
</style>