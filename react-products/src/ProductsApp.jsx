// import { useState } from 'react';

import PropTypes from "prop-types"
import { ProductTable } from "./components/ProductTable"

export const ProductsApp = ({title = 'Hola que tal'}) => {
    return <div className='container my-4'>
        <h2>{title}</h2>
        <div className="row">
            <div className="col">
                <ProductTable/>
            </div>
        </div>
    </div>
}

ProductsApp.propTypes = {
    title: PropTypes.string.isRequired
}



