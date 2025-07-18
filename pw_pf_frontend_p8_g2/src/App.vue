<template>
  <NavBar/>
  <router-view/>
</template>

<script>
import NavBar from "@/components/NavBar.vue"
export default {
  components:{
    NavBar
  }  
}
</script>

<style>

*{
  margin: 0; 
  padding: 0; 
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  height: 100vh;
  box-sizing: content-box;
  display: flex;
}

</style>
