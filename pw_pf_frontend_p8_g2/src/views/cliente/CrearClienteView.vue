<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <CrearCliente />
  </div>
</template>

<script>
import CrearCliente from "@/components/cliente/CrearCliente.vue";
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    CrearCliente,
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