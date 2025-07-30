<template>
  <div class="container-facturaview">
    <Factura :numero-documento="numeroDocumento" />
  </div>
</template>

<script>
import Factura from "@/components/factura/Factura.vue";
export default {
  name: "FacturaView",
  components: {
    Factura,
  },
  props: {
    numeroDocumento: {
      type: String,
      required: true,
    },
  },
};
</script>

<style scoped>
.container-facturaview {
  width: 100%;
  height: 100%;
}
</style>