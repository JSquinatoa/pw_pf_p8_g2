<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <BuscarTodosCliente/>
  </div>
</template>

<script>
import BuscarTodosCliente from "@/components/cliente/BuscarTodosCliente.vue";
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    BuscarTodosCliente,
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