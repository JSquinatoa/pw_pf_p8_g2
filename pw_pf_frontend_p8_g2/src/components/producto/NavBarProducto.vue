<template>
    <div class="container-navbar-cliente">
        <nav>
            <router-link to="/consultar" class="nav-item">Consultar</router-link>
            <router-link to="/guardar" class="nav-item">Crear</router-link>
            <router-link to="/actualizar" class="nav-item">Actualizar</router-link>
            <router-link to="/borrar" class="nav-item">Eliminar</router-link>
        </nav>
    </div>
</template>

<script>
export default {};
import "@/css/NavBarCrudGenerico.css";
</script>

<style >

</style>