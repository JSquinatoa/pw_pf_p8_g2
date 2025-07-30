import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/facturas"

const consultarTodosReportes = async () => {
    const respuesta = axios.get(`${URL_API}`).then(res => res.data);
    return respuesta;
}

const guardar = async (body) => {
    const data = axios.post(URL_API, body).then(res => res.data);
}

//Fachadas
export const consultarTodosReportesFachada = async () => {
    return await consultarTodosReportes();
}
export const guardarFachada = async (body) => {
    return await guardar(body)
}
