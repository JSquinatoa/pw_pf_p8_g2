import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import BodegaView from "@/views/BodegaView.vue"
import ProductoView from "@/views/ProductoView.vue"
import ClienteView from "@/views/ClienteView.vue"
import FacturaView from "@/views/FacturaView.vue"
import ReporteView from "@/views/ReporteView.vue"

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/bodega',
    name: 'bodega',
    component: BodegaView
  },
  {
    path: '/producto',
    name: 'producto',
    component: ProductoView
  },
  {
    path: '/cliente',
    name: 'cliente',
    component: ClienteView
  },
  {
    path: '/factura',
    name: 'factura',
    component: FacturaView
  },
  {
    path: '/reporte',
    name: 'reporte',
    component: ReporteView
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
