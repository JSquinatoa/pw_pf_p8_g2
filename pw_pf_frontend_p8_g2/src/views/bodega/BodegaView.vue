<template>
  <div class="container-bodegaview">
    <NavBarBodega />
    <router-view />
    <Bodega />

  </div>
</template>

<script>
import NavBarBodega from '@/components/bodega/NavBarBodega.vue';
import Bodega from "@/components/bodega/Bodega.vue";

export default {
  components: {
    NavBarBodega,
    Bodega,
  },
};
</script>

<style>
.container-bodegaview {
  width: 100%;
  height: 100%;
}
</style>