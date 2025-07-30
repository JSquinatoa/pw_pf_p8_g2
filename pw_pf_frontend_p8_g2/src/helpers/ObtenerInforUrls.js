import axios from "axios";

const obtenerInformacionUrls = async (URL_API) => {
    const response = axios.get(URL_API).then(res => res.data);
    return response;
}

// Funciones Fachada
export const obtenerInformacionUrlsFachada = async (URL_API) => {
    return await obtenerInformacionUrls(URL_API);
}