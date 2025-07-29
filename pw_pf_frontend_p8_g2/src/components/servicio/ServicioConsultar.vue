<template>
    <div class="container-consultarServicio">
        <button class="boton-consultar" @click="rellenarTabla()">Consultar Todos los {{ category === 'producto' ? 'Productos' : 'Servicios' }}</button>

        <table v-if="itemsFiltrados">
            <thead>
                <tr>
                    <th>Identificador</th>
                    <th>Codigo de Barras</th>
                    <th>Nombre</th>
                    <th>Categoria</th>
                    <th>Precio</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in itemsFiltrados" :key="item.id">
                    <td>{{ item.id }}</td>
                    <td>{{ item.codigoBarras }}</td>
                    <td>{{ item.nombre }}</td>
                    <td>{{ item.categoria }}</td>
                    <td>{{ item.precio }}</td>
                </tr>
            </tbody>
        </table>

        <h2 v-if="!itemsFiltrados">
            Presiona el botón para consultar todos los {{ category === 'producto' ? 'Productos' : 'Servicios' }}
        </h2>

    </div>
</template>

<script>
import { consultarTodosProductosFachada } from "@/clients/ProductoClient.js"; 

export default {
    props: {
        category: {
            type: String,
            required: true,
            validator: (value) => ['producto', 'servicio'].includes(value),
        },
    },
    data() {
        return {
            todosItems: null, 
            itemsFiltrados: null,
        };
    },

    methods: {
        async rellenarTabla() {
            const allItems = await consultarTodosProductosFachada(); 
            this.itemsFiltrados = allItems.filter(item => item.categoria === this.category);
            console.log(this.itemsFiltrados);
        },
    },
};
</script>

<style scoped>
.container-consultarServicios {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin-top: 5px;
}
 
.boton-consultar {
    margin-bottom: 25px;
    padding: 0.75rem 2rem;
    font-size: 1.1rem;
    background: #003366;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background 0.2s;
}
 
.boton-consultar:hover {
    background: #041a3d;
}
 
table {
    width: 90%;
    max-width: 960px;
    margin: 0 auto;
    border-collapse: collapse;
    background: #fff;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    border-radius: 6px;
    overflow: hidden;
}
 
thead {
    background: #003366;
    color: #fff;
}
 
thead th {
    padding: 0.75rem;
    text-align: center;
    font-weight: 600;
}
 
tbody tr {
    border-bottom: 1px solid #e0e0e0;
}
 
tbody tr:hover {
    background: #f2f2f2;
}
 
tbody td {
    padding: 0.75rem;
    text-align: center;
}
 
h2 {
    margin-top: 2rem;
    color: #666;
    font-style: italic;
    text-align: center;
}

@media (max-width: 1024px) {
    table {
        width: 95%;
    }
}

@media (max-width: 768px) {
    .boton-consultar {
        width: 90%;
        padding: 0.6rem 1.5rem;
        font-size: 1em;
    }
    table {
        width: 95%;
    }
    thead th, tbody td {
        padding: 0.5rem;
        font-size: 0.9em;
    }
    h2 {
        font-size: 1.1em;
    }
}

@media (max-width: 480px) {
    .boton-consultar {
        width: 95%;
        font-size: 0.9em;
        padding: 0.5rem 1rem;
    }
    table {
        width: 98%;
        font-size: 0.8em;
    }
    thead th, tbody td {
        padding: 0.3rem;
    }
    h2 {
        font-size: 1em;
    }
}
</style>