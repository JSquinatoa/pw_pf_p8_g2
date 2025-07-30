<template>
  <div class="container-productoview">
    <NavBarProducto />
    <router-view />
  </div>
</template>

<script>
import NavBarProducto from "@/components/producto/NavBarProducto.vue";
export default {
  components: {
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