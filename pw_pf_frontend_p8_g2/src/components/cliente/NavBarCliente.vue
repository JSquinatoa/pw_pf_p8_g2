<template>
    <div class="container-navbar-cliente">
        <nav>
            <router-link to="/crearcliente" class="nav-item">Crear</router-link>
            <router-link to="/actualizarcliente" class="nav-item">Actualizar</router-link>
            <router-link to="/buscarcliente" class="nav-item">Buscar</router-link>
            <router-link to="/buscartodosclientes" class="nav-item">Obtener Todos</router-link>
            <router-link to="/borrarcliente" class="nav-item">Eliminar</router-link>
        </nav>
    </div>
</template>

<script>
export default {};
import "@/css/NavBarCrudGenerico.css";
</script>

<style>

</style>