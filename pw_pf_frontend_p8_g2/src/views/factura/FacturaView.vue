<template>
  <div class="container-facturaview">
    <Factura />
  </div>
</template>

<script>
import Factura from "@/components/factura/Factura.vue";
export default {
  components: {
    Factura,
  },
};
</script>

<style>
.container-facturaview {
  width: 100%;
  height: 100%;
}
</style>