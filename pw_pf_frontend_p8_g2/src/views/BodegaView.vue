<template>
  <div class="container-bodegaview">
    <Bodega />
  </div>
</template>

<script>
import Bodega from "@/components/bodega/Bodega.vue";
export default {
  components: {
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