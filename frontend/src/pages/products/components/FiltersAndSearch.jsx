import { useState } from "react";


export default function FiltersAndSearch({categories, toggleForm, showForm, onFilter}){
    const [formData, setFormData] = useState({
        description: "",
        categoryId: ""
      });
    
      const clearForm = () => {
        setFormData({
          description: "",
          categoryId: ""
        });
        handleSubmit({ description: "", categoryId: "" });
      };
    
      const handleChange = (e) => {
        const { name, value, type, files } = e.target;
        const updatedData = {
          ...formData,
          [name]: type === "file" ? files[0] : value
        };
    
        setFormData(updatedData);
        handleSubmit(updatedData);
      };
    
      const handleSubmit = async (dataToSend) => {
        const response = await fetch("/api/products/search", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(dataToSend)
        });
    
        const filteredProducts = await response.json();
        onFilter(filteredProducts);
      };
    
    
    return (
        <>
        <form onSubmit={handleChange}>
        <div className="row mb-4">


                <div className="col-lg-2 col-sm-3 mb-3">
                    <div className="input-group">
                        <button className="btn btn-primary" type="button" 
                        id="showForm"
                        onClick={toggleForm}>
                            <i className={`fas ${showForm ? 'fa-times' : 'fa-plus'}`}> </i>
                            {' '}{showForm ? 'Cancelar' : 'Nuevo'}
                            </button>
                    </div>
                </div>
                
                <div className="col-lg-4 col-sm-9 mb-3">
                    <div className="input-group">
                        <span className="input-group-text bg-primary text-white">
                            <i className="fas fa-filter"></i>
                        </span>
                        <select 
                        className="form-select" 
                        name="categoryId" 
                        onChange={handleChange}
                        value={formData.categoryId}>
                            <option value="0">Todas las categorías</option>
                                    {categories.map(cat => (
                            <option key={cat.id} value={cat.id}>{cat.name}</option>
                            ))}
                        </select>
                    </div>
                </div>

                <div className="col-lg-6 col-sm-12 mb-3">
                    <div className="input-group">
                        <span className="input-group-text bg-primary text-white">
                            <i className="fas fa-search"></i>
                        </span>
                        <input 
                        type="text" 
                        className="form-control" 
                        name="description" 
                        onChange={handleChange}
                        value={formData.description}
                        placeholder="Buscar productos..." />

                        <button className="btn btn-outline-secondary" type="button" onClick={clearForm}>
                            <i className="fas fa-times"></i>
                        </button>
                    </div>
                </div>
        </div>
        </form>
        </>
    );
}