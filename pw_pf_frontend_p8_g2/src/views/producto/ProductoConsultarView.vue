<template>
  <div class="container-producto">
    <ProductoConsultar :category="'producto'" />
  </div>
</template>

<script>
import ProductoConsultar from "@/components/producto/ProductoConsultar.vue";
export default {
  components: {
    ProductoConsultar
  },
};
</script>

<style scoped>
.container-producto {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>