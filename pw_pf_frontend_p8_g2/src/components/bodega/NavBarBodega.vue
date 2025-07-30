<template>
    <div class="container-navbar-cliente">
        <nav>
            <router-link to="/bodega/consultar" class="nav-item">Consultar</router-link>
            <router-link to="/bodega/crear" class="nav-item">Crear</router-link>
            <router-link to="/bodega/modificar" class="nav-item">Actualizar</router-link>
            <router-link to="/bodega/eliminar" class="nav-item">Eliminar</router-link>
        </nav>
    </div>
</template>

<script>
export default {};
import "@/css/NavBarCrudGenerico.css";
</script>

<style scoped>
</style>