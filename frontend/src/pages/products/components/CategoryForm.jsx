import { useState } from "react";

export default function CategoryForm(props) {

    const [formData, setFormData] = useState ({
        name:""
    });

    

    const handleSubmit = (e) => {
        e.preventDefault();
    
        // Envía el formulario al backend 
        fetch("/api/categories", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData)
        })
          .then(res => res.json())
          .then(data => {
            alert("Categoria guardada correctamente");
            console.log(data);
            props.onCategoryAdded();
          })
          .catch(err => {
            console.error("Error al enviar:", err);
            alert("Error al guardar la categoria");
          });
    }


    return (
        <>
            <form onSubmit={handleSubmit} >
                <div className="form-group mb-3">
                    <label for="name"><b>Nombre</b></label>
                    <input 
                    type="text" 
                    className="form-control" 
                    id="name" 
                    name="name"
                    placeholder="Ingrese el nombre de la categoria"
                    value={formData.name} 
                    required />
                </div>

                <button type="submit" className="btn btn-primary col-12">Guardar</button>
            </form>
        
        </>

    );
}