<template>
  <nav class="navbar-reporte">
    <router-link to="/reporte/todos" class="nav-item">
      Reportes
    </router-link>
  </nav>
</template>

<script>
export default {
  name: "NavBarReporte",
};
</script>

<style scoped>
.navbar-reporte {
  display: flex;
  width: 100%;
  justify-content: center;
  background: #07265c;
  border-radius: 5px;
  overflow: hidden;
  margin: 1rem 0;
}

.nav-item {
  flex: 1;
  padding: 0.75rem 1rem;
  text-align: center;
  color: #fff;
  text-decoration: none;
  font-weight: 500;
  transition: background 0.2s;
}

.nav-item:not(:last-child) {
  border-right: 1px solid rgba(0, 0, 0, 0.2);
}

.router-link-active {
  background: #041a3d;
}

.nav-item:hover {
  background: #0c3e80;
}
</style>
