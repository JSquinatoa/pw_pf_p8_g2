import axios from "axios";
<<<<<<< HEAD
const API_URL = "http://localhost:8081/api/reportes/v1/facturas/reportes";
=======
>>>>>>> 374574fec6e83975ac331b746f97c3720c834a00

const BASE = "http://localhost:8081/api/reportes/v1/facturas";

// Consultar todos los reportes (ya lo tienes)
export const consultarTodosReportesFachada = async () => {
  const { data } = await axios.get(`${BASE}/reportes`);
  return data;
};

// Consultar la factura completa por su ID
export const consultarFacturaCompletaFachada = async (id) => {
  const { data } = await axios.get(`${BASE}/${id}`);
  return data;
};

// Consultar los detalles de compra de una factura
export const consultarDetallesCompraFachada = async (id) => {
  const { data } = await axios.get(`${BASE}/${id}/detalles`);
  return data;
};