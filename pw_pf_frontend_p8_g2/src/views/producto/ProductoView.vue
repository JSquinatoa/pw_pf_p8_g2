<template>
  <div class="container-productoview">
    <Producto />
    <NavBarProducto />
    <router-view />
  </div>
</template>

<script>
import NavBarProducto from "@/components/producto/NavBarProducto.vue";
import Producto from "@/components/producto/Producto.vue";
export default {
  components: {
    Producto,
    NavBarProducto
  },
};
</script>

<style>
.container-productoview {
  width: 100%;
  height: 100%;
}
</style>