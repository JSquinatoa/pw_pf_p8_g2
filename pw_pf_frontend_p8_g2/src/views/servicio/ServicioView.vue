<template>
  <div class="container_servicio">
    <Servicio />
    <NavBarServicio />
    <router-view />
  </div>
</template>

<script>
import NavBarServicio from "@/components/servicio/NavBarServicio.vue";
import Servicio from "@/components/servicio/Servicio.vue";

export default {
  components: {
    Servicio,
    NavBarServicio
  },
};
</script>

<style>
.container_servicio {
  width: 100%;
  height: 100%;
}
</style>