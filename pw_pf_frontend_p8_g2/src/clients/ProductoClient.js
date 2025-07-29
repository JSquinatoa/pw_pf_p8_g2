import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/productos"

// Consultar todos
const consultarTodosProductos = async () => {
    const respuesta = axios.get(`${URL_API}`).then(res => res.data);
    return respuesta;
}

//ConsultarId

const consultarProductosId = async (id) => {
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

const buscarImpuestosPorIdProducto = async (idProducto) => {
        const response = await axios.get(`${URL_API}/${idProducto}/impuestos`);
        return response.data;
};

// Funciones Fachada
export const consultarTodosProductosFachada = async () => {
    return await consultarTodosProductos();
}

export const consultarProductosIdFachada = async (id) => {
    return await consultarProductosId(id);
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

export const buscarImpuestosPorIdProductoFachada = async (idProducto) => {
    return buscarImpuestosPorIdProducto(idProducto);
};