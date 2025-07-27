<template>
  <div></div>
  <nav :class="['continaer-navbar', ocultar ? 'navbar-oculto' : 'navbar-visible']">
    <router-link class="link" to="/">Home</router-link>
    <router-link class="link" to="/bodega">Bodega</router-link>
    <a class="link" @click="isMercancia = !isMercancia">Mercancia</a>
    <div v-if="isMercancia" class="container_mercancia">
      <router-link class="link prod" to="/producto"
        >Produto</router-link
      >
      <router-link class="link prod" to="/servicio"
        >Servicio</router-link
      >
    </div>
    <router-link class="link" to="/cliente">Cliente</router-link>
    <router-link class="link" to="/factura">Factura</router-link>
    <router-link class="link" to="/reporte">Reporte</router-link>
  </nav>
  <div :class="['mostrar', !ocultar ? 'container-esconder' : 'container-mostrar']">
    <button @click="esconder()">{{ flecha }}</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ocultar: false,
      flecha: "<",
      isMercancia: true,
    };
  },
  methods: {
    esconder() {
      this.ocultar = !this.ocultar;
      if (this.ocultar) {
        this.flecha = ">";
      } else {
        this.flecha = "<";
      }
    },
    mostrarMecancia() {
      this.isMercancia = !this.isMercancia;
    },
  },
};
</script>

<style scoped>
.continaer-navbar {
  display: flex;
  flex-direction: column;
  justify-content: start;
  background-color: white;
  border-radius: 0px 20px 20px 0px;
  box-shadow: 10px 0px 10px rgba(0, 0, 0, 0.407);
  gap: 1px;
  padding: 0 30px;
  padding-top: 50px;
  width: 250px;
  height: 100vh;
  box-sizing: border-box;
  position: fixed;
  top: 0;
  left: 0;
  height: 100vh;
  transition: all 0.4s ease-in-out;
}

.navbar-visible {
  transform: translateX(0);
  opacity: 1;
}

.navbar-oculto {
  transform: translateX(-100%);
  opacity: 0;
  pointer-events: none;
}

.link {
  border: 1px solid;
  padding: 10px 20px;
  font-size: 15px;
  transition: all 0.5s ease-in;
  text-decoration: none;
  color: black;
  cursor: pointer;
  box-sizing: border-box;
  text-align: left;
}

.container_mercancia {
  display: flex;
  flex-direction: column;
  gap: 1px;
}

.prod {
  margin-left: 15px;
}

.mostrar{
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 5px;
  left: 190px; 
  transition: all 0.4s ease-in-out; 
}

button {
  padding: 10px 15px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 100%;
}

.container-esconder {
  transform: translateX(0);
}

.container-mostrar {
  left: 10px;
}
</style>
