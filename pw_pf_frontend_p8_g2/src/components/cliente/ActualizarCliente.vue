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
    <h1>Actualizar Cliente</h1>
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
      <p type="Teléfono:">
        <input type="number" v-model="cliente.telefono" :disabled="deshabilitado"/>
      </p>
      <p type="Correo electrónico:">
        <input type="email" v-model="cliente.correo" :disabled="deshabilitado"/>
      </p>
    </div>
    <button class="boton_consulta actualizar" @click="actulizarParcial()">
      Actualizar
    </button>
    <div v-if="!existeCliente">
      <h1>El cliente con la cedula {{ identificador }} no existe</h1>
    </div>
    <div v-if="exitoActulizar">
      <h1>
        El cliente con la cedula {{ identificador }} Se actulizo Correctamente
      </h1>
    </div>
  </div>
</template>

<script>
import { consultarClientePorIdFachada } from "@/clients/ClienteClient.js";
import { actulizarParcialPorIdFachada } from "@/clients/ClienteClient.js";

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
      exitoActulizar: false,
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

    async actulizarParcial() {
      const clienteToBody = {
        cedula: this.cliente.cedula,
        nombre: this.cliente.nombre,
        apellido: this.cliente.apellido,
        razonSocial: this.cliente.razonSocial,
        direccion: this.cliente.direccion,
        telefono: this.cliente.telefono,
        correo: this.cliente.correo,
      };

      if (!this.deshabilitado) {
        await actulizarParcialPorIdFachada(
          clienteToBody,
          this.identificador
        );
      }
      this.deshabilitado = true;
      this.exitoActulizar = true
      setTimeout(() => {
        this.exitoActulizar = false;
        this.deshabilitado = false;
        this.reiniciarVaraibles();
      }, 3000);
    },

    reiniciarVaraibles() {
        this.cliente.cedula = null,
        this.cliente.nombre = null,
        this.cliente.apellido = null,
        this.cliente.razonSocial = null,
        this.cliente.direccion = null,
        this.cliente.telefono = null,
        this.cliente.correo = null;
    },
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

.actualizar {
  width: 90%;
  max-width: 250px;
  background-color: #07265c;
  box-shadow: 0 2px 8px rgba(231, 76, 60, 0.08);
}

.actualizar:hover {
  background-color: #217dbb;
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
  border: 10px double #003366; 
  box-shadow: 0 4px 24px rgba(44, 62, 80, 0.08);
  padding: 32px 28px;
  box-sizing: border-box;
}

p {
  margin: 0;
  padding: 0;
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
}
}
</style>