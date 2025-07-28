<template>
  <div class="container-clienteview">
    <Cliente />
  </div>
</template>

<script>
import Cliente from "@/components/cliente/Cliente.vue";
export default {
  components: {
    Cliente,
  },
};
</script>

<style>
.container-clienteview {
  width: 100%;
  height: 100%;
}
</style>