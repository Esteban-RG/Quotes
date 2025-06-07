import { useState } from "react";

export default function UnitsForm(props) {

    const [formData, setFormData] = useState ({
        name:""
    });


    const handleSubmit = (e) => {
        e.preventDefault();
    
        // Envía el formulario al backend 
        fetch("/api/units-of-measure", {
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
            props.onUnitAdded();
          })
          .catch(err => {
            console.error("Error al enviar:", err);
            alert("Error al guardar la unidad de medida");
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
                    placeholder="Ingrese el nombre de la unidad de medida"
                    value={formData.name} 
                    required />
                </div>

                <button type="submit" className="btn btn-primary col-12">Guardar</button>
            </form>
        
        </>

    );
}