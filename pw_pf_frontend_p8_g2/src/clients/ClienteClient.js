import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/clientes"


const consultarTodosClientes = async () => {
    const respuesta = axios.get(`${URL_API}`).then(res => res.data);
    return respuesta;
}

const consultarClientePorId = async (id) => {
        const respuesta = await axios.get(`${URL_API}/${id}`).then(res => res.data);
        return respuesta;
};

const actulizarParcialPorId = async (body, id) => {
    const data = axios.put(`${URL_API}/${id}`, body).then(res => res.data)
}


const guardar = async (body) => {
    const data = axios.post(URL_API, body).then(res => res.data);
}

const borrarPorId = async (id) => {
    await axios.delete(`${URL_API}/${id}`).then(res => res.data)
}


export const consultarTodosClientesFachada = async () => {
    return await consultarTodosClientes();
}

export const consultarClientePorIdFachada = async (id) => {
    return await consultarClientePorId(id);
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