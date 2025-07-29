<template>
  <div class="container-impuestoG">
    <ImpuestoGuardar/> </div>
</template>

<script>
import ImpuestoGuardar from '@/components/impuesto/ImpuestoGuardar.vue';

export default {
  components: {
    ImpuestoGuardar
  },
};
</script>

<style scoped>
.container-impuestoG {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>