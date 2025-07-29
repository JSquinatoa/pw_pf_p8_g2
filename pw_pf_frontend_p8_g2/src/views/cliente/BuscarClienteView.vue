<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <BuscarCliente/>
  </div>
</template>

<script>
import BuscarCliente from "@/components/cliente/BuscarCliente.vue";
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    BuscarCliente,
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