<template>
    <div class="container_consultarcliente">
        <button class="boton_consulta" @click="rellenarTabla()">Consultar Todos los {{ category === 'producto' ? 'Productos' : 'Servicios' }}</button>

        <table class="tabla-clientes" v-if="itemsFiltrados">
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
                <tr v-for="producto in itemsFiltrados" :key="producto.id">
                    <td>{{ producto.id }}</td>
                    <td>{{ producto.codigoBarras }}</td>
                    <td>{{ producto.nombre }}</td>
                    <td>{{ producto.categoria }}</td>
                    <td>{{ producto.precio }}</td>
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
            todosProductos: null,
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