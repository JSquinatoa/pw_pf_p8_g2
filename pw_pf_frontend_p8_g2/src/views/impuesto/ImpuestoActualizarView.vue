<template>
  <div class="container-impuestoAc">
    <ImpuestoActualizar/>
  </div>
</template>

<script>
import ImpuestoActualizar from "@/components/impuesto/ImpuestoActualizar.vue";

export default {
  components: {
   ImpuestoActualizar
  },
};
</script>

<style scoped>
.container-impuestoAc {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>