<template>
  <div class="container-impuestoB">
    <ImpuestoBorrar/> </div>
</template>

<script>
import ImpuestoBorrar from '@/components/impuesto/ImpuestoBorrar.vue';
export default {
  components: {
    ImpuestoBorrar
  },
};
</script>

<style scoped>
.container-impuestoB {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>