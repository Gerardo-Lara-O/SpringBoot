import { useEffect, useState } from 'react';

import PropTypes from "prop-types"
import { ProductTable } from "./components/ProductTable"
import { ProductForm } from './components/ProductForm';

const initProducts = [{
    id: 1,
    name: 'Monitor Asus',
    description: 'El monitor es perfecto para juegos',
    price: 1000
},

{
    id: 2,
    name: 'Iphone 16 Pro',
    description: 'Telefono excelente',
    price: 3000
}]

export const ProductsApp = ({ title = 'Hola que tal' }) => {

    const [products, setProducts] = useState([]);
    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price:''
    })

    useEffect(() => {
        setProducts(initProducts);
        console.log('cargando la pagina ...')
    }, []);

    const handlerAddProduct = (product) => {
    // Si el ID es mayor a 0, significa que estamos EDITANDO un producto existente
    if (product.id > 0) {
        setProducts(
            products.map(prod => { // <-- ¡Corregido! Iteramos sobre la lista 'products'
                if (prod.id === product.id) {
                    return { ...product }; // Si encontramos el que editamos, lo reemplazamos por el nuevo
                }
                return prod; // Si no es el que editamos, lo dejamos exactamente igual
            })
        );
    } else {
        // Si el ID es 0, estamos CREANDO uno nuevo
        setProducts([...products, { ...product, id: Date.now() }]);
    }
}

    const handlerProductSelected = (product) => {
        setProductSelected({...product})
        console.log(productSelected)
    }

    const handlerRemoveProduct = (id) => {
        setProducts(
            products.filter(product => product.id != id)
        );
    }

    return <div className='container my-4'>
        <h2>{title}</h2>
        <div className="row">
            <div className='col'>
                <ProductForm handlerAdd={handlerAddProduct} productSelected={ productSelected}></ProductForm>
            </div>
            <div className="col">
            {
                (products.length > 0) ? 
                <ProductTable products={products} 
                handlerProductSelected ={handlerProductSelected}
                handlerRemoveProduct = {handlerRemoveProduct}
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



