<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <router-view />
  </div>
</template>

<script>
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    NavBarCliente
  },
};
</script>

<style>
.container-clienteview {
  width: 100%;
  height: 100%;
}
</style>