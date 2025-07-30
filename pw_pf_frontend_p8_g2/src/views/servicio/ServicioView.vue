<template>
  <div class="container_servicio">
    <NavBarServicio />
    <router-view/>   
  </div>
</template>

<script>
import NavBarServicio from "@/components/servicio/NavBarServicio.vue";
export default {
  components: {
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