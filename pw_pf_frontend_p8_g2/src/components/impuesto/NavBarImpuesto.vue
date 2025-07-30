<template>
    <div class="container-navbar-impuesto">
        <nav>
            <router-link to="/consultarI" class="nav-item">Consultar</router-link>
            <router-link to="/guardarI" class="nav-item">Crear</router-link>
            <router-link to="/actualizarI" class="nav-item">Actualizar</router-link>
            <router-link to="/borrarI" class="nav-item">Eliminar</router-link>
        </nav>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.container-navbar-impuesto {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    max-width: 960px;
    margin: 40px auto;
}

.container-navbar-impuesto nav {
    display: flex; 
    flex-direction: row; 
    justify-content: center;
    align-items: stretch;
    border-radius: 5px;
    overflow: hidden; 
    flex-wrap: wrap; 
}

.nav-item {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #07265c;
    color: white;
    text-decoration: none;
    font-weight: bold;
    white-space: nowrap;
    transition: background-color 0.3s ease;
    border-right: 1px solid rgba(0, 0, 0, 0.2);
    padding: 15px 30px; 
    font-size: 1.1em; 
    flex-grow: 1; 
    flex-basis: auto; 
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

@media screen and (max-width: 768px) {
    .container-navbar-impuesto nav {
        gap: 3px 0px; 
    }
    

}

@media screen and (max-width: 480px) {
    .nav-item {
        font-size: 0.9em;
        gap: 3px 2px; 
    }
}


</style>