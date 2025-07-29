<template>
  <div class="container-impuesto">
    <Impuesto />
    <NavBarImpuesto />
    <router-view />
  </div>
</template>

<script>
import NavBarImpuesto from "@/components/impuesto/NavBarImpuesto.vue";
import Impuesto from "@/components/impuesto/Impuesto.vue";
export default {
  components: {
    Impuesto,
    NavBarImpuesto
  },
};
</script>

<style>
.container-impuesto {
  width: 100%;
  height: 100%;
}
</style>