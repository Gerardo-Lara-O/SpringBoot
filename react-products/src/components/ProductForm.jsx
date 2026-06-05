import { useState } from "react"
import PropTypes from "prop-types"

const initialDataForm = {
    id: 0,
    name: '',
    description: '',
    price: ''
}

export const ProductForm = ({handlerAdd}) => {

    const [form, setForm] = useState(initialDataForm);

    const { id, name, description, price } = form;

    return <form onSubmit={event => {
        event.preventDefault();
        if(!name || !description || !price){
            alert('Debe completar los datos del formulario');
            return;
        }
        console.log(form);
        handlerAdd(form)
        setForm(initialDataForm);
    }}>

    <div>
        <input placeholder="Name" className="form-control my-3 w-75" value={name} onChange={(event) => setForm({...form, name: event.target.value})}></input>
    </div>
    <div>
        <input placeholder="Description" className="form-control my-3 w-75" value={description} onChange={(event) => setForm({...form, description: event.target.value})}></input>
    </div>
    <div>
        <input placeholder="price" className="form-control my-3 w-75" value={price} onChange={(event) => setForm({...form, price: event.target.value})}></input>
    </div>

    <div>
        <button className="btn btn-primary" type="submit">
            {id>0? 'Update' : 'Create'}
        </button>
    </div>

    </form>

}

ProductForm.propTypes = {
    handlerAdd: PropTypes.func.isRequired
}