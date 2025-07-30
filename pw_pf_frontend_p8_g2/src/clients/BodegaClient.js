import axios from "axios";

const URL_API = `http://localhost:8081/api/reportes/v1/bodegas`

const consultarBodegas = async () => {
  try {
    const response = await axios.get(URL_API).then((res) => res.data);
    return response;
  } catch (error) {
    console.error("Error obteniendo bodegas:", error);
    throw error;
  }
}

const consultarBodegaPorId = async (id) => {
  try {
    const response = await axios.get(`${URL_API}/${id}`).then((res) => res.data);
    return response;
  } catch (error) {
    console.error("Error obteniendo bodegas por Id:", error);
    throw error;
  }
}
export const consultarBodegaPorIdFachada = async (id) => {
  return await consultarBodegaPorId(id);
}

export const consultarBodegasFachada = async () => {
  return await consultarBodegas();
}


const consultarProductosPorBodegaUrl = async (productosUrl) => {
  try {
    const response = await axios.get(productosUrl).then((res) => res.data);
    return response;
  } catch (error) {
    console.error(`Error obteniendo productos desde ${productosUrl}:`, error);
    throw error; 
  }
};

export const consultarProductosPorBodegaUrlFachada = async (productosUrl) => {
  return await consultarProductosPorBodegaUrl(productosUrl);
};

export async function obtenerProductosPorBodegaFachada(codigoBodega) {
  try {
    const response = await axios.get(`http://localhost:8081/api/reportes/v1/inventarios/productos-por-bodega/${codigoBodega}`).then((res) => res.data);
    console.log(response);
    
    return response;
  } catch (error) {
    console.error(`Error al obtener productos de la bodega ${codigoBodega}:`, error);
    throw error;
  }
}


const insertarBodega = async (body) => {
    try {
       await axios.post(URL_API, body).then((res) => res.data);
    } catch (error) {
        console.error("Error insertando bodega:", error);
        throw error;
    }
}

export const insertarBodegaFachada = async (body) => {
    await insertarBodega(body);
}

const actualizarBodega = async (id, body) => {
    try {
        await axios.patch(`${URL_API}/${id}`, body).then((res) => res.data);
    } catch (error) {
        console.error("Error actualizando bodega:", error);
        throw error;
    }
}
export const actualizarBodegaFachada = async (id, body) => {
    await actualizarBodega(id, body);
}

const eliminarBodega = async (codigo) => {
    try {
        await axios.delete(`${URL_API}/${codigo}`).then((res) => res.data);
    } catch (error) {
        console.error("Error eliminando bodega:", error);
        throw error;
    }
}

export const eliminarBodegaFachada = async (codigo) => {
    await eliminarBodega(codigo);
}