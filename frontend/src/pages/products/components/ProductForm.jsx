import React, { useState } from "react";

export default function ProductForm(props) {
  
  const [formData, setFormData] = useState({
    description: "",
    imgPath: "",
    price: "",
    categoryId: "",
    unitOfMeasureId: "",
  });

  const handleChange = (e) => {
    const { name, value, type, files } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === "file" ? files[0] : value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    
    // Envía el formulario al backend 
    fetch("/api/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData)
    })
      .then(res => res.json())
      .then(data => {
        alert("Producto guardado correctamente");
        console.log(data);
        props.onProductAdded();
      })
      .catch(err => {
        console.error("Error al enviar:", err);
        alert("Error al guardar el producto");
      });

    

  };

  return (
    <form onSubmit={handleSubmit} >
        {/* Categoría */}
        <div className="form-group mb-3">
            <label htmlFor="categoryId"><b>Categoría</b></label>
            <div className="row">
                <div className="col-8 col-lg-10">
                    <select
                    id="categoryId"
                    name="categoryId"
                    className="form-control"
                    value={formData.category}
                    onChange={handleChange}
                    required
                    >
                    <option value="" >Seleccione categoría</option>
                    {props.categories.map(cat => (
                        <option key={cat.id} value={cat.id}>{cat.name}</option>
                    ))}
                    </select>
                </div>
                <div className="col-4 col-lg-2 align-content-center">
                    <button type="button" className="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#NewCategory">
                    <i className="fa fa-plus"></i>
                    </button>
                    <button type="button" className="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#ShowCategories">
                    <i className="fa fa-eye"></i>
                    </button>
                </div>
            </div>
        </div>

        {/* Unidad de medida */}
        <div className="form-group mb-3">
            <label htmlFor="unitOfMeasureId"><b>Unidad de medida</b></label>
            <div className="row">
                <div className="col-8 col-lg-10">
                    <select
                    id="unitOfMeasureId"
                    name="unitOfMeasureId"
                    className="form-control"
                    value={formData.measurement}
                    onChange={handleChange}
                    required
                    >
                    <option value="">Seleccione unidad de medida</option>
                    {props.units.map(unit => (
                        <option key={unit.id} value={unit.id}>{unit.name}</option>
                    ))}
                    </select>
                </div>
                <div className="col-4 col-lg-2 align-content-center">
                    <button type="button" className="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#NewUnit">
                    <i className="fa fa-plus"></i>
                    </button>
                    <button type="button" className="btn btn-info btn-sm" data-bs-toggle="modal" data-bs-target="#ShowUnits">
                    <i className="fa fa-eye"></i>
                    </button>
                </div>
            </div>
        </div>

        {/* Descripción */}
        <div className="form-group mb-3">
            <label htmlFor="description"><b>Descripción</b></label>
            <textarea
            className="form-control"
            id="description"
            name="description"
            rows="3"
            placeholder="Ingrese la descripción del producto"
            value={formData.description}
            onChange={handleChange}
            required
            ></textarea>
        </div>

        {/* Precio */}
        <div className="form-group mb-3">
            <label htmlFor="price"><b>Precio</b></label>
            <input
            type="number"
            className="form-control"
            id="price"
            name="price"
            placeholder="Ingrese el precio del producto"
            min="0"
            step="0.01"
            value={formData.price}
            onChange={handleChange}
            required
            />
        </div>

        {/* Imagen 
        <div className="form-group mb-3">
            <label htmlFor="imagePath"><b>Imagen</b></label>
            <input
            type="file"
            className="form-control-file"
            id="image"
            name="image"
            accept="image/*"
            onChange={handleChange}
            />
        </div>
        */}

        {/* Botón */}
        <button type="submit" className="btn btn-primary col-12">Guardar</button>
    </form>
   
  );
}
