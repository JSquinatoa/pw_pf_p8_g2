import { createRouter, createWebHashHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import BodegaView from "@/views/bodega/BodegaView.vue";
import ProductoView from "@/views/producto/ProductoView.vue";
import ServicioView from "@/views/servicio/ServicioView.vue";
import ClienteView from "@/views/cliente/ClienteView.vue";
import FacturaView from "@/views/factura/FacturaView.vue";
import ReporteView from "@/views/reporte/ReporteView.vue";
import ImpuestoView from "@/views/impuesto/ImpuestoView.vue";

import ProductoConsultarView from "@/views/producto/ProductoConsultarView.vue";
import ProductoActualizarView from "@/views/producto/ProductoActualizarView.vue";
import ProductoGuardarView from "@/views/producto/ProductoGuardarView.vue";
import ProductoBorrarView from "@/views/producto/ProductoBorrarView.vue";

import ServicioConsultarView from "@/views/servicio/ServicioConsultarView.vue";
import ServicioActualizarView from "@/views/servicio/ServicioActualizarView.vue";
import ServicioBorrarView from "@/views/servicio/ServicioBorrarView.vue";
import ServicioGuardarView from "@/views/servicio/ServicioGuardarView.vue";

import BodegaConsutlarView from "@/views/bodegaviews/BodegaConsultarView.vue"
import BodegaCrearView from "@/views/bodegaviews/BodegaCrearView.vue"
import BodegaModificarView from "@/views/bodegaviews/BodegaModificarView.vue"
import BodegaEliminarView from "@/views/bodegaviews/BodegaEliminarView.vue"

import CrearClienteView from '@/views/cliente/CrearClienteView.vue'
import ActualizarClienteView from '@/views/cliente/ActualizarClienteView.vue'
import BuscarClienteView from '@/views/cliente/BuscarClienteView.vue'
import BuscarTodosClienteView from '@/views/cliente/BuscarTodosClienteView.vue'
import BorrarClienteView from '@/views/cliente/BorrarClienteView.vue'

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
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
    path: "/producto",
    name: "producto",
    component: ProductoView,
    children: [
      {
        path: "/consultar",
        name: "producto-consultar",
        component: ProductoConsultarView
      },
      {
        path: "/guardar",
        name: "producto-guardar",
        component: ProductoGuardarView,
      },
      {
        path: "/actualizar",
        name: "producto-actualizar",
        component: ProductoActualizarView,
      },
      {
        path: "/borrar",
        name: "producto-borrar",
        component: ProductoBorrarView,
      },
    ],
  },
  {
    path: "/servicio",
    name: "servicio",
    component: ServicioView,
    children: [
      {
        path: "/consultarS",
        name: "servicio-consultar",
        component: ServicioConsultarView,
      },
      {
        path: "/guardarS",
        name: "servicio-guardar",
        component: ServicioGuardarView,
      },
      {
        path: "/actualizarS",
        name: "servicio-actualizar",
        component: ServicioActualizarView,
      },
      {
        path: "/borrarS",
        name: "servicio-borrar",
        component: ServicioBorrarView,
      },
    ],
  },
  {
    path: "/impuesto",
    name: "impuesto",
    component: ImpuestoView,
  },
  {
    path: "/cliente",
    name: "cliente",
    component: ClienteView,
  },
  {
    path: '/crearcliente',
    name: 'crearcliente',
    component: CrearClienteView
  },
  {
    path: '/actualizarcliente',
    name: 'actualizarcliente',
    component: ActualizarClienteView
  },
  {
    path: '/buscarcliente',
    name: 'buscarcliente',
    component: BuscarClienteView
  },
  {
    path: '/buscartodosclientes',
    name: 'buscartodosclientes',
    component: BuscarTodosClienteView
  },
   {
    path: '/borrarcliente',
    name: 'borrarcliente',
    component: BorrarClienteView
  },
  {
    path: '/factura',
    name: 'factura',
    component: FacturaView
  },
  {
    path: "/factura",
    name: "factura",
    component: FacturaView,
  },
  {
    path: "/reporte",
    name: "reporte",
    component: ReporteView,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
