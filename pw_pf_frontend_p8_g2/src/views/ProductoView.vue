<template>
  <div class="container-productoview">
    <Producto />
  </div>
</template>

<script>
import Producto from "@/components/producto/Producto.vue";
export default {
  components: {
    Producto,
  },
};
</script>

<style>
.container-productoview {
  width: 100%;
  height: 100%;
}
</style>