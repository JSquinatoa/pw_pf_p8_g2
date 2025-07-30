import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import BodegaView from "@/views/bodega/BodegaView.vue";
import ProductoView from "@/views/producto/ProductoView.vue";
import ServicioView from "@/views/servicio/ServicioView.vue";
import ClienteView from "@/views/cliente/ClienteView.vue";
import FacturaView from "@/views/factura/FacturaView.vue";
import ReporteView from "@/views/reporte/ReporteView.vue";
import ImpuestoView from "@/views/impuesto/ImpuestoView.vue";


import BodegaConsutlarView from "@/views/bodegaviews/BodegaConsultarView.vue";
import BodegaCrearView from "@/views/bodegaviews/BodegaCrearView.vue";
import BodegaModificarView from "@/views/bodegaviews/BodegaModificarView.vue";
import BodegaEliminarView from "@/views/bodegaviews/BodegaEliminarView.vue";

import CrearCliente from "@/components/cliente/CrearCliente.vue";
import ActualizarCliente from "@/components/cliente/ActualizarCliente.vue";
import BuscarCliente from "@/components/cliente/BuscarCliente.vue";
import BuscarTodosCliente from "@/components/cliente/BuscarTodosCliente.vue";
import BorrarCliente from "@/components/cliente/BorrarCliente.vue";

import ReporteConsultar from '@/components/reporte/ReporteConsultar.vue'

import ImpuestoConsultar from "@/components/impuesto/ImpuestoConsultar.vue";
import ImpuestoGuardar from "@/components/impuesto/ImpuestoGuardar.vue";
import ImpuestoActualizar from "@/components/impuesto/ImpuestoActualizar.vue";
import ImpuestoBorrar from "@/components/impuesto/ImpuestoBorrar.vue";

import ProductoConsultar from "@/components/producto/ProductoConsultar.vue";
import ProductoGuardar from "@/components/producto/ProductoGuardar.vue";
import ProductoActualizar from "@/components/producto/ProductoActualizar.vue";
import ProductoBorrar from "@/components/producto/ProductoBorrar.vue";

import ServicioConsultar from "@/components/servicio/ServicioConsultar.vue";
import ServicioGuardar from "@/components/servicio/ServicioGuardar.vue";
import ServicioActualizar from "@/components/servicio/ServicioActualizar.vue";
import ServicioBorrar from "@/components/servicio/ServicioBorrar.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/bodega",
    name: "bodega",
    component: BodegaView,
    children: [
      {
        path: "consultar",
        name: "bodega-consultar",
        component: BodegaConsutlarView,
      },
      {
        path: "crear",
        name: "bodega-crear",
        component: BodegaCrearView,
      },
      {
        path: "modificar",
        name: "bodega-modificar",
        component: BodegaModificarView,
      },
      {
        path: "eliminar",
        name: "bodega-eliminar",
        component: BodegaEliminarView,
      },
    ],
  },
  {
    path: "/producto",
    name: "producto",
    component: ProductoView,
    children: [
      {
        path: "/consultar",
        name: "producto-consultar",
        component: ProductoConsultar,
        props: { category: 'producto' }
      },
      {
        path: "/guardar",
        name: "producto-guardar",
        component: ProductoGuardar,
        props: { category: 'producto' }
      },
      {
        path: "/actualizar",
        name: "producto-actualizar",
        component: ProductoActualizar,
        props: { category: 'producto' }
      },
      {
        path: "/borrar",
        name: "producto-borrar",
        component: ProductoBorrar,
        props: { category: 'producto' }
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
        component: ServicioConsultar,
        props: { category: 'servicio' }
      },
      {
        path: "/guardarS",
        name: "servicio-guardar",
        component: ServicioGuardar,
        props: { category: 'servicio' }
      },
      {
        path: "/actualizarS",
        name: "servicio-actualizar",
        component: ServicioActualizar,
        props: { category: 'servicio' }
      },
      {
        path: "/borrarS",
        name: "servicio-borrar",
        component: ServicioBorrar,
        props: { category: 'servicio' }
      },
    ],
  },
  {
    path: "/impuesto",
    name: "impuesto",
    component: ImpuestoView,
    children: [
      {
        path: "/consultarI",
        name: "impuesto-consultar",
        component: ImpuestoConsultar,
      },
      {
        path: "/guardarI",
        name: "impuesto-guardar",
        component: ImpuestoGuardar,
      },
      {
        path: "/actualizarI",
        name: "impuesto-actualizar",
        component: ImpuestoActualizar,
      },
      {
        path: "/borrarI",
        name: "impuesto-borrar",
        component: ImpuestoBorrar,
      },
    ],
  },
  {
    path: "/cliente",
    name: "cliente",
    component: ClienteView,
    children: [
      {
        path: "/crearcliente",
        name: "crearcliente",
        component: CrearCliente,
      },
      {
        path: "/actualizarcliente",
        name: "actualizarcliente",
        component: ActualizarCliente,
      },
      {
        path: "/buscarcliente",
        name: "buscarcliente",
        component: BuscarCliente,
      },
      {
        path: "/buscartodosclientes",
        name: "buscartodosclientes",
        component: BuscarTodosCliente,
      },
      {
        path: "/borrarcliente",
        name: "borrarcliente",
        component: BorrarCliente,
      },
    ],
  },
  
  {
    path: "/factura",
    name: "factura",
    component: FacturaView,
  },
  {
    path: "/factura/:numeroDocumento",
    name: "factura",
    component: FacturaView,
    props: true
  },
    {
    path: '/reporte',
    name: 'reporte',
    component: ReporteView,
    children: [
      {
        path: 'todos',
        name: 'reporte-todos',
        component: ReporteConsultar,
      },
    ]
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
