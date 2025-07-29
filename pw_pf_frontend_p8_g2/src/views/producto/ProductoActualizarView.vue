<template>
  <div class="container-productoAc">
    <ProductoActualizar :category="'producto'"/>
  </div>
</template>

<script>
import ProductoActualizar from "@/components/producto/ProductoActualizar.vue";
export default {
  components: {
    ProductoActualizar
  },
};
</script>

<style scoped>
.container-productoAc {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>