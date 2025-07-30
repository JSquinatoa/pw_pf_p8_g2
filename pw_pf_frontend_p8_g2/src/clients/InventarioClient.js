import axios from "axios";

const URL_API = "http://localhost:8081/api/reportes/v1/inventarios"

const guardar = async (codigoBodega, codigoBarras, stock) => {
    return axios.post(`${URL_API}/${codigoBodega}/${codigoBarras}?stock=${stock}`).then(res => res.data);
}

// Funciones Fachada
export const guardarFachada = async (codigoBodega, codigoBarras, stock) => {
    return await guardar(codigoBodega, codigoBarras, stock);
}
