import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "@/css/EstiloGenerico.css";
import "@/css/NavBarCrudGenerico.css";

createApp(App).use(router).mount('#app')
