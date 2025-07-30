<template>
  <div class="container-cliente">
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
.container-cliente {
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
</style>