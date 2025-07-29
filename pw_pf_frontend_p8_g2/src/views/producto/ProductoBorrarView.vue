<template>
  <div class="container-productoB">
    <ProductoBorrar :category="'producto'" /> </div>
</template>

<script>
import ProductoBorrar from '@/components/producto/ProductoBorrar.vue'
export default {
  components: {
    ProductoBorrar
  },
};
</script>

<style scoped>
.container-productoB {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>