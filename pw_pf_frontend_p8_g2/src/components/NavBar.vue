<template>
  <nav class="continaer-navbar">
    <router-link class="link" to="/">Home</router-link>
    <router-link class="link" to="/bodega">Bodega</router-link>
    <router-link class="link" to="/producto">Produto/Servicio</router-link>
    <router-link class="link" to="/cliente">Cliente</router-link>
    <router-link class="link" to="/factura">Factura</router-link>
    <router-link class="link" to="/reporte">Reporte</router-link>
  </nav>
</template>

<script>
export default {};
</script>

<style scoped>
.continaer-navbar {
  display: flex;
  flex-direction: column;
  width: 200px;
  background-color: white;
  border-radius: 0px 20px 20px 0px;
  box-shadow: 10px 0px 10px rgba(0, 0, 0, 0.407);
  gap: 25px;
}

.link {
  border: 1px solid;
  padding: 10px 0;
  font-size: 15px;
}

nav {
  padding: 30px;
}
</style>