export default function FiltersAndSearch({categories, toggleForm, showForm}){
    
    
    return (
        <>
        <div className="row mb-4">

            <div className="col-lg-2 col-sm-3 mb-3">
                <div className="input-group">
                    <button className="btn btn-primary" 
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
                    <select className="form-select" id="categoryFilter">
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
                    <input type="text" id="searchInput" className="form-control"
                        placeholder="Buscar productos..." />

                    <button className="btn btn-outline-secondary" type="button" id="clearSearch">
                        <i className="fas fa-times"></i>
                    </button>
                </div>
            </div>
        </div>
        </>
    );
}