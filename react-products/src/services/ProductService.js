import axios from "axios";

const baseUrl = 'http://127.0.0.1:8081';

export const findAll = async ()=> {
    try {
        const response = await axios.get(baseUrl);
        return response;
        
    } catch (error) {
        console.log(error);
        
    }
    return null;
}

export const create = async ({name, description, price})=> {
    try {
        return await axios.post(baseUrl, {
            name: name,
            description: description,
            price: price
        })
    } catch (error) {
        console.log(error);
        
    }
    return undefined;
}

export const update = async ({id, name, description, price})=> {
    try {
        return await axios.put(`${baseUrl}/${id}`,{
            name: name,
            description: description,
            price: price
        }
        )
    } catch (error) {
        console.log(error);
        
    }
    return undefined;
}

export const remove = async (id)=> {
    try {
        await axios.delete(`${baseUrl}/${id}`)
    } catch (error) {
        console.log(error);
        
    }
    
}