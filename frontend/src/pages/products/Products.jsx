import { Link } from "react-router-dom";
import React, { useEffect, useState } from "react";
import ProductForm from "./components/ProductForm";
import FiltersAndSearch from "./components/FiltersAndSearch";
import ProductTable from "./components/ProductTable";
import Modal from "../../components/Modal";
import CategoryForm from "./components/CategoryForm";
import UnitForm from "./components/UnitForm";
import CategoryForm from "./components/CategoryForm";
import CategoryTable from "./components/CategoryTable";
import UnitTable from "./components/UnitTable";

export default function Products() {
    const [categories, setCategories] = useState([]);
    const [units, setUnits] = useState([]);
    const [products, setProducts] = useState([]);

    const fetchProducts = () => {
        fetch("/api/products")
          .then(res => res.json())
          .then(data => setProducts(data))
          .catch(err => console.error("Error cargando productos:", err));
    };

    const fetchCategories = () => {
        fetch("/api/categories")
            .then(res => res.json())
            .then(data => setCategories(data))
            .catch(err => console.error("Error cargando categorías:", err));
    }

    const fetchUnits = () => {
        fetch("/api/units-of-measure")
            .then(res => res.json())
            .then(data => setUnits(data))
            .catch(err => console.error("Error cargando unidades:", err));
    }

    useEffect(() => {
        fetchCategories();
        fetchUnits();
        fetchProducts();

    }, []);

    const [showForm, setShowForm] = useState(false);

    const toggleForm = () => {
        setShowForm(prev => !prev);
    };

    const handleFilteredProducts = (filteredProducts) => {
        setProducts(filteredProducts);
    };




    return (

        <>
        
        <section className="container-fluid">
            <div className={`new row justify-content-center ${showForm ? '' : 'd-none'}`}>
                <div className="card col-sm-11 col-md-7 col-lg-6 shadow p-3 mb-5 bg-white border-0 rounded">
                <div className="card-body">
                    <h2 className="text-center">Registrar producto</h2>
                    <ProductForm categories={categories} units={units} reloadProducts={fetchProducts} action={"create"} />
                       
                </div>
                </div>
            </div>
        </section>

        <section className="container-fluid">
            <div className="row mb-4">
                <div className="card col-12 shadow mb-5 rounded border-0">
                    <div className="card-body">

                        <FiltersAndSearch  categories={categories} toggleForm={toggleForm} showForm={showForm} onFilter={handleFilteredProducts}/>
                        <ProductTable products={products} onProductAdded={fetchProducts} onProductDeleted={fetchProducts}/>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <Modal id='NewCategory' title="Registrar Categoria">
                <CategoryForm onCategoryAdded={fetchCategories} />
            </Modal>

            <Modal id='NewUnit' title="Registrar Unidad de Medida">
                <UnitForm onUnitAdded={fetchUnits} />
            </Modal>
            
            <Modal id='ShowCategories' title='Categorias'>
                <CategoryTable categories={categories} reloadCategories={fetchCategories} />            
            </Modal>
            
            <Modal id='ShowUnits' title='Unidades de medida'>
                <UnitTable units={units} reloadUnits={fetchUnits}/>
            </Modal>

            



        </section>
        </>
    
    )
  }