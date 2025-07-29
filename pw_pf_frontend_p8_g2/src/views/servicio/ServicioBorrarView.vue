<template>
  <div class="container-servicioB">
    <ServicioBorrar :category="'servicio'" />
  </div>
</template>

<script>
import ServicioBorrar from '@/components/servicio/ServicioBorrar.vue' // Corregido el nombre del componente
export default {
  components: {
    ServicioBorrar
  },
};
</script>

<style scoped>
.container-servicioB{
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>