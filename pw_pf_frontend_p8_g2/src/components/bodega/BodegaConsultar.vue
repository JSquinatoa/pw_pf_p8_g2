<template>
    <div class="container-bconsultar">
        <button class="boton_consultar" @click="consultarBodegasConProductos()">
            Consultar Todas las Bodegas </button>
        <div class="table-responsive" v-if="todosBodegas && todosBodegas.length > 0">
            <table>
                <thead>
                    <tr>
                        <th>Identificación</th>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Ubicación</th>
                        <th>Productos Asociados</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="bodega in todosBodegas" :key="bodega.id">
                        <td>{{ bodega.id }}</td>
                        <td>{{ bodega.codigo }}</td>
                        <td>{{ bodega.nombre }}</td>
                        <td>{{ bodega.ubicacion }}</td>
                        <td>
                            <ul v-if="bodega.productos && bodega.productos.length > 0">
                                <li v-for="producto in bodega.productos" :key="producto.id">
                                    {{ producto.nombre }} (Cód. Barras: {{ producto.codigoBarras }})
                                </li>
                            </ul>
                            <span v-else>Sin productos</span>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <h2 v-if="!todosBodegas || todosBodegas.length === 0">
            Presiona el botón para consultar todas las bodegas
        </h2>
        <h2 v-if="errorCargando">
            Ocurrió un error al cargar las bodegas. Inténtalo de nuevo.
        </h2>
    </div>
</template>

<script>
import { consultarBodegasFachada, consultarProductosPorBodegaUrlFachada } from "@/clients/BodegaClient";

export default {
    data() {
        return {
            todosBodegas: null,
            errorCargando: false,
        };
    },

    methods: {
        async consultarBodegasConProductos() {
            this.errorCargando = false;
            this.todosBodegas = null;

            try {
                const bodegas = await consultarBodegasFachada();
                if (bodegas && bodegas.length > 0) {
                    const bodegasWithProducts = await Promise.all(
                        bodegas.map(async (bodega) => {
                            if (bodega._links && bodega._links.productos) {
                                try {
                                    const productos = await consultarProductosPorBodegaUrlFachada(bodega._links.productos);
                                    bodega.productos = productos;
                                } catch (productError) {
                                    console.warn(`No se pudieron cargar los productos para la bodega ${bodega.codigo}:`, productError);
                                    bodega.productos = []; 
                                }
                            } else {
                                bodega.productos = []; 
                            }
                            return bodega;
                        })
                    );
                    this.todosBodegas = bodegasWithProducts;
                } else {
                    this.todosBodegas = [];
                }
                console.log("Bodegas con productos:", this.todosBodegas);
            } catch (error) {
                console.error("Error general al consultar bodegas o productos:", error);
                this.errorCargando = true;
                this.todosBodegas = [];
            }
        },
    },
};
</script>

<style scoped>
.container-bconsultar {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    padding: 15px;
    min-height: 50vh;
    box-sizing: border-box;
}

.boton_consultar {
    width: 40%;
    max-width: 300px;
    height: auto;
    min-height: 40px;
    
    font-size: 25px;
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;

    transition: all 0.1s ease;
    background-color: #07265c;
    color: white;
    cursor: pointer;
    padding: 10px 15px;
    margin-bottom: 20px;
}

.boton_consultar:hover {
    background-color: #217dbb;
}


.table-responsive {
    width: 100%;
    overflow-x: auto;
    max-height: 60vh;
    overflow-y: auto;
    margin-top: 1rem;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    background-color: white;
}

table {
    width: 100%;
    min-width: 600px;
    border-collapse: collapse;
    font-family: Arial, sans-serif;
    font-size: 0.95rem;
}

h2 {
    margin-top: 50px;
    text-transform: uppercase;
    text-align: center;
    padding: 0 10px;
}

thead {
    background-color: #003366;
    color: #ffffff;
}

thead th {
    padding: 0.75rem;
    text-align: center;
    font-weight: bold;
    border-bottom: 1px solid #ccc;
    border-right: 1px solid #ccc;
    text-transform: uppercase;
}

thead th:hover {
    background-color: #07294a;
}

tbody tr {
    border-bottom: 1px solid #e0e0e0;
    transition: background-color 0.3s;
}

tbody tr:last-child {
    border-bottom: none;
}

tbody tr:hover {
    background-color: #f2f2f2;
}

tbody td {
    padding: 0.75rem;
    border-left: 1px solid #33333370;
    color: #333;
}

tbody td ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
    text-align: left;
}

tbody td li {
    margin-bottom: 5px;
}

@media screen and (max-width: 768px) {
    .boton_consultar {
        width: 60%;
        font-size: 20px;
        min-height: 35px;
    }
    table {
        font-size: 0.85rem;
    }
    thead th,
    tbody td {
        padding: 0.5rem;
    }
    .table-responsive {
        max-height: 70vh;
    }
}

@media screen and (max-width: 480px) {
    .boton_consultar {
        width: 80%;
        font-size: 16px; 
        letter-spacing: 1px;
        min-height: 30px; 
    }
    table {
        font-size: 0.75rem;
    }
    thead th,
    tbody td {
        padding: 0.4rem;
    }
    .table-responsive {
        max-height: 75vh;
    }
}
</style>