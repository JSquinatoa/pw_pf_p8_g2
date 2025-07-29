<template>
  <div class="container-impuesto">
    <ImpuestoConsultar/>
  </div>
</template>

<script>


import ImpuestoConsultar from "@/components/impuesto/ImpuestoConsultar.vue";
export default {
  components: {
    ImpuestoConsultar,
  },
};
</script>

<style scoped>
.container-impuesto {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>