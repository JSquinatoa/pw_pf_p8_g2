<template>
  <div class="container-servicioG">
    <ServicioGuardar :category="'servicio'" />
  </div>
</template>

<script>
import ServicioGuardar from '@/components/servicio/ServicioGuardar.vue';
export default {
  components: {
    ServicioGuardar
  },
};
</script>

<style scoped>
.container-servicioG {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>