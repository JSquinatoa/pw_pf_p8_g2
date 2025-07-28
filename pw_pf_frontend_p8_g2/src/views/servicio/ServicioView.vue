<template>
  <div class="container_servicio">
    <Servicio />
  </div>
</template>

<script>
import Servicio from "@/components/servicio/Servicio.vue";

export default {
  components: {
    Servicio,
  },
};
</script>

<style>
.container_servicio {
  width: 100%;
  height: 100%;
}
</style>