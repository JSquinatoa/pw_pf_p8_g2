<template>
  <div class="container-impuesto">
    <NavBarImpuesto />
    <router-view />
  </div>
</template>

<script>
import NavBarImpuesto from "@/components/impuesto/NavBarImpuesto.vue";

export default {
  components: {
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