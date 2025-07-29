import axios from "axios";
const API_URL = "/api/reportes/v1/reportes";

// Consultar todos
const consultarTodosReportes = async () => {
    const respuesta = axios.get(`${API_URL}`).then(res => res.data);
    return respuesta;
}

// Funciones Fachada
export const consultarTodosReportesFachada = async () => {
    return await consultarTodosReportes();
}
