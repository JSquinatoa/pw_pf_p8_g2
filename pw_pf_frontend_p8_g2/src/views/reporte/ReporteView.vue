<template>
  <div class="container-reporteview">
    <NavBarReporte />
    <div class="content-reporte">
      <!-- Aquí se montarán los hijos (ReporteConsultar, etc.) -->
      <router-view />
    </div>
  </div>
</template>

<script>
import NavBarReporte from "@/components/reporte/NavBarReporte.vue";

export default {
  name: "ReporteView",
  components: {
    NavBarReporte,
  },
};
</script>

<style scoped>
.container-reporteview {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  min-height: 100vh;
  background: #f9f9f9;
  padding-bottom: 2rem;
}

.content-reporte {
  width: 80%;
  max-width: 960px;
  margin-top: 1.5rem;
}
</style>
