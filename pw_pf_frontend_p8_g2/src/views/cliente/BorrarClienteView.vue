<template>
  <div class="container-clienteview">
    <NavBarCliente />
    <BorrarCliente/>
  </div>
</template>

<script>
import BorrarCliente from "@/components/cliente/BorrarCliente.vue";
import NavBarCliente from "@/components/cliente/NavBarCliente.vue";

export default {
  components: {
    BorrarCliente,
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