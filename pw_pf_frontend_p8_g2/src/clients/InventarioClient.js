import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/inventarios"

const guardar = async (codigoBodega, codigoBarras, stock) => {
    return axios.post(`${URL_API}/${codigoBodega}/${codigoBarras}?stock=${stock}`).then(res => res.data);
}

const descontarStock = async (codigoBodega, codigoBarras, cantidad) => {
    const descontado = axios.post(`${URL_API}/descontar-stock/${codigoBodega}/${codigoBarras}?cantidad=${cantidad}`).then(res => res.data);
    return descontado;
}

const restaurarStock = async (codigoBodega, codigoBarras, cantidad) => {
    axios.post(`${URL_API}/restaurar-stock/${codigoBodega}/${codigoBarras}?cantidad=${cantidad}`);
}

// Funciones Fachada
export const guardarFachada = async (codigoBodega, codigoBarras, stock) => {
    return await guardar(codigoBodega, codigoBarras, stock);
}

export const descontarStockFachada = async (codigoBodega, codigoBarras, cantidad) => {
    return await descontarStock(codigoBodega, codigoBarras, cantidad);
}

export const restaurarStockFachada = async (codigoBodega, codigoBarras, cantidad) => {
    await restaurarStock(codigoBodega, codigoBarras, cantidad);
}
