<template>
  <nav v-if="!ocultar" class="continaer-navbar">
    <router-link class="link" to="/">Home</router-link>
    <router-link class="link" to="/bodega">Bodega</router-link>
    <router-link class="link" to="/producto">Produto/Servicio</router-link>
    <router-link class="link" to="/cliente">Cliente</router-link>
    <router-link class="link" to="/factura">Factura</router-link>
    <router-link class="link" to="/reporte">Reporte</router-link>
  </nav>
  <div :class="!ocultar? 'container-esconder':'container-mostrar'">
    <button @click="esconder()">{{ flecha }}</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ocultar: false,
      flecha: "<",
    };
  },
  methods: {
    esconder() {
      this.ocultar = !this.ocultar;
      if (this.ocultar) {
        this.flecha = ">"
        
      }else{
        this.flecha = "<"
      }
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
  gap: 25px;
  padding: 0 30px;
  padding-top: 50px;
  width: 200px;
  position: sticky;
  top:0;
}

.link {
  border-bottom: 1px solid;
  padding: 10px 20px;
  font-size: 15px;
  transition: all 0.5s ease-in;
  text-decoration: none;
}

.container-esconder {
  position: sticky;
  top: 10px;
  left: 180px;
}

button {
  padding: 5px 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container-mostrar{
  justify-content: center;
  display: flex;
  position: absolute;
  top: 10px;
  left: 10px;
}
</style>
