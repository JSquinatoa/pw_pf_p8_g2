<template>
    <div class="container_consultarcliente">
        <button class="boton_consulta" @click="rellenarTabla()">Consultar Todos los {{ category === 'producto' ? 'Productos' : 'Servicios' }}</button>

        <table v-if="itemsFiltrados" class="tabla-clientes">
            <thead>
                <tr>
                    <th>Identificador</th>
                    <th>C&oacute;digo de Barras</th>
                    <th>Nombre</th>
                    <th>Categor&iacute;a</th>
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
import "@/css/EstiloGenerico.css";

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

<style >

</style>