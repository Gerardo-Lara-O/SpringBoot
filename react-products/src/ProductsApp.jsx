import { useEffect, useState } from 'react';

import PropTypes from "prop-types"
import { ProductTable } from "./components/ProductTable"
import { ProductForm } from './components/ProductForm';
import { findAll, remove } from './services/ProductService';
import { update } from './services/ProductService';
import { create } from './services/ProductService';
import Swal from 'sweetalert2';

// const initProducts = [{
//     id: 1,
//     name: 'Monitor Asus',
//     description: 'El monitor es perfecto para juegos',
//     price: 1000
// },

// {
//     id: 2,
//     name: 'Iphone 16 Pro',
//     description: 'Telefono excelente',
//     price: 3000
// }]

export const ProductsApp = ({ title = 'Hola que tal' }) => {

    const [products, setProducts] = useState([]);
    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    const getProducts = async () => {
        const result = await findAll();
        setProducts(result.data)
    }

    useEffect(() => {
        getProducts();
        console.log('cargando la pagina ...')
    }, []);

    const handlerAddProduct = async (product) => {
        // Si el ID es mayor a 0, significa que estamos EDITANDO un producto existente
        if (product.id > 0) {
            const response = await update(product);
            setProducts(
                products.map(prod => { // <-- ¡Corregido! Iteramos sobre la lista 'products'
                    if (prod.id === product.id) {
                        return { ...response.data }; // Si encontramos el que editamos, lo reemplazamos por el nuevo
                    }
                    return prod; // Si no es el que editamos, lo dejamos exactamente igual
                })
            );
            Swal.fire({
                title: "Actualizado con exito!!",
                text: `Producto ${product.name} actualizado con exito!`,
                icon: "success"
            });
        } else {
            // Si el ID es 0, estamos CREANDO uno nuevo
            const response = await create(product);
            setProducts([...products, { ...response.data }]);
            Swal.fire({
                title: "Creado con exito!!",
                text: `Producto ${product.name} creado con exito!`,
                icon: "success"
            });
        }
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
        console.log(productSelected)
    }

    const handlerRemoveProduct = (id) => {
        Swal.fire({
            title: "Estas seguro que quiere eliminar?",
            text: "No se va a poder revertir este cambio!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Si, Eliminalo!"
        }).then(async(result) => {
            if (result.isConfirmed) {
                await remove(id);
                setProducts(
                    products.filter(product => product.id != id)
                );
                Swal.fire({
                    title: "Eliminado con exito!!",
                    text: `Producto eliminado con exito!`,
                    icon: "success"
                });
            };
        });

    }

    return <div className='container my-4'>
        <h2>{title}</h2>
        <div className="row">
            <div className='col'>
                <ProductForm handlerAdd={handlerAddProduct} productSelected={productSelected}></ProductForm>
            </div>
            <div className="col">
                {
                    (products.length > 0) ?
                        <ProductTable products={products}
                            handlerProductSelected={handlerProductSelected}
                            handlerRemoveProduct={handlerRemoveProduct}
                        /> :
                        <div className='alert alert-warning'>No hay productos en el sistema</div>
                }

            </div>
        </div>
    </div>
}

ProductsApp.propTypes = {
    title: PropTypes.string.isRequired
}



