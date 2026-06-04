import { useEffect, useState } from 'react';

import PropTypes from "prop-types"
import { ProductTable } from "./components/ProductTable"

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

    useEffect(() => {
        setProducts(initProducts);
        console.log('cargando la pagina ...')
    }, []);

    return <div className='container my-4'>
        <h2>{title}</h2>
        <div className="row">
            <div className="col">
                <ProductTable products={ products }/>
            </div>
        </div>
    </div>
}

ProductsApp.propTypes = {
    title: PropTypes.string.isRequired
}



