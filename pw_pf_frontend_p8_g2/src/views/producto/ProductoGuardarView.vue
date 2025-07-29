<template>
  <div class="container-productoG">
    <ProductoGuardar :category="'producto'" /> </div>
</template>

<script>
import ProductoGuardar from '@/components/producto/ProductoGuardar.vue';
export default {
  components: {
    ProductoGuardar
  },
};
</script>

<style scoped>
.container-productoG {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>