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
</script>

<style scoped>
.container-navbar-cliente {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    max-width: 960px;
    margin: 40px auto;
}

.container-navbar-cliente nav {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: stretch;
    border-radius: 5px;
    overflow: hidden;
    width: 100%;
}

.nav-item {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 15px 30px;
    background-color: #07265c;
    color: white;
    text-decoration: none;
    font-size: 1.1em;
    white-space: nowrap;
    transition: background-color 0.3s ease;
    border-right: 1px solid rgba(0, 0, 0, 0.2);
}

.nav-item:first-child {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
}

.nav-item:last-child {
    border-right: none;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
}

.nav-item:hover {
    background-color: #0c3e80;
}

.router-link-active,
.router-link-exact-active {
    background-color: #041a3d;
}

/* RESPONSIVE */
@media (max-width: 700px) {
    .container-navbar-cliente {
        width: 98%;
        margin: 20px auto;
    }
    .container-navbar-cliente nav {
        flex-direction: column;
        align-items: stretch;
        border-radius: 8px;
    }
    .nav-item {
        padding: 12px 10px;
        font-size: 1em;
        border-right: none;
        border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        border-radius: 0;
    }
    .nav-item:first-child {
        border-top-left-radius: 8px;
        border-top-right-radius: 8px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
    }
    .nav-item:last-child {
        border-bottom: none;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
}
</style>