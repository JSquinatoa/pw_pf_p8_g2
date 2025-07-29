import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import BodegaView from "@/views/bodegaviews/BodegaView.vue"
import ProductoView from "@/views/ProductoView.vue"
import ServicioView from "@/views/ServicioView.vue"
import ClienteView from "@/views/ClienteView.vue"
import FacturaView from "@/views/FacturaView.vue"
import ReporteView from "@/views/ReporteView.vue"
import BodegaConsutlarView from "@/views/bodegaviews/BodegaConsultarView.vue"
import BodegaCrearView from "@/views/bodegaviews/BodegaCrearView.vue"
import BodegaModificarView from "@/views/bodegaviews/BodegaModificarView.vue"
import BodegaEliminarView from "@/views/bodegaviews/BodegaEliminarView.vue"

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/bodega',
    name: 'bodega',
    component: BodegaView,
    children: [
      {
        path: 'consultar', 
        name: 'bodega-consultar',
        component: BodegaConsutlarView
      },
      {
        path: 'crear', 
        name: 'bodega-crear',
        component: BodegaCrearView
      },
      {
        path: 'modificar', 
        name: 'bodega-modificar',
        component: BodegaModificarView
      },
      {
        path: 'eliminar', 
        name: 'bodega-eliminar',
        component: BodegaEliminarView
      },
    ]
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
