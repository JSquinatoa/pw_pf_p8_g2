import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/impuestos"

// Consultar todos
const consultarTodosImpuestos = async () => {
    const respuesta = axios.get(`${URL_API}`).then(res => res.data);
    return respuesta;
}

//ConsultarId

const consultarImpuestosId = async (id) => {
    const respuesta = axios.get(`${URL_API}/${id}`).then(res => res.data);
    return respuesta;
}


// Actulizar parcial
const actulizarParcialPorId = async (body, id) => {
    const data = axios.put(`${URL_API}/${id}`, body).then(res => res.data)
}

// guardar producto
const guardar = async (body) => {
    const data = axios.post(URL_API, body).then(res => res.data);
}

// boorar por id
const borrarPorId = async (id) => {
    axios.delete(`${URL_API}/${id}`).then(res => res.data)
}


// Funciones Fachada
export const consultarTodosImpuestosFachada = async () => {
    return await consultarTodosImpuestos();
}

export const consultarImpuestosIdFachada = async (id) => {
    return await consultarImpuestosId(id);
}

export const actulizarParcialPorIdFachada = async (body, id) => {
    return await actulizarParcialPorId(body, id)
}

export const guardarFachada = async (body) => {
    return await guardar(body)
}

export const borrarPorIdFachada = async (id) => {
    return await borrarPorId(id)
}