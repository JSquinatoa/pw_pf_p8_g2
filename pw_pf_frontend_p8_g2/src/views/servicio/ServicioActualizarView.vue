<template>
  <div class="container-servicioAc">
    <ServicioActualizar :category="'servicio'" />
  </div>
</template>

<script>
import ServicioActualizar from "@/components/servicio/ServicioActualizar.vue";
export default {
  components: {
    ServicioActualizar
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