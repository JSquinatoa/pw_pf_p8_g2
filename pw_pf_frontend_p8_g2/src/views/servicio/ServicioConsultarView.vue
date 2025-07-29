<template>
  <div class="container-servicio">
    <ServicioConsultar :category="'servicio'" />
  </div>
</template>

<script>
import ServicioConsultar from "@/components/servicio/ServicioConsultar.vue";
export default {
  components: {
    ServicioConsultar
  },
};
</script>

<style scoped>
.container-servicio {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>