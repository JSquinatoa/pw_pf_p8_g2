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
