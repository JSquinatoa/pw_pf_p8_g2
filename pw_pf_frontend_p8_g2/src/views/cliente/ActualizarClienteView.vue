<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <ActualizarCliente/>
  </div>
</template>

<script>
import ActualizarCliente from "@/components/cliente/ActualizarCliente.vue";
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    ActualizarCliente,
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