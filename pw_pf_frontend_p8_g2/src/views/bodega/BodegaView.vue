<template>
  <div class="container-bodegaview">
    <NavBarBodega />
    <router-view />
  </div>
</template>

<script>
import NavBarBodega from '@/components/bodega/NavBarBodega.vue';

export default {
  components: {
    NavBarBodega,

  },
};
</script>

<style>
.container-bodegaview {
  width: 100%;
  height: 100%;
}
</style>