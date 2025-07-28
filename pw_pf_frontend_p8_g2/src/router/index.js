import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import BodegaView from "@/views/bodega/BodegaView.vue"
import ProductoView from "@/views/producto/ProductoView.vue"
import ServicioView from "@/views/servicio/ServicioView.vue"
import ClienteView from "@/views/cliente/ClienteView.vue"
import FacturaView from "@/views/factura/FacturaView.vue"
import ReporteView from "@/views/reporte/ReporteView.vue"

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
    path: '/servicio',
    name: 'servicio',
    component: ServicioView
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
