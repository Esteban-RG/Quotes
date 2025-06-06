import { Link } from "react-router-dom";
import React, { useEffect, useState } from "react";
import ProductForm from "./components/ProductForm";
import FiltersAndSearch from "./components/FiltersAndSearch";
import ProductTable from "./components/ProductTable";

export default function Products() {
    const [categories, setCategories] = useState([]);
    const [units, setUnits] = useState([]);
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("/api/categories")
            .then(res => res.json())
            .then(data => setCategories(data))
            .catch(err => console.error("Error cargando categorías:", err));

        fetch("/api/units-of-measure")
            .then(res => res.json())
            .then(data => setUnits(data))
            .catch(err => console.error("Error cargando unidades:", err));
        
        fetch("/api/products")
            .then(res => res.json())
            .then(data => setProducts(data))
            .catch(err => console.error("Error cargando productos:", err));
    }, []);


    return (

        <>
        <section className="container-fluid">
            <div className="new row justify-content-center">
                <div className="card col-sm-11 col-md-7 col-lg-6 shadow p-3 mb-5 bg-white border-0 rounded">
                <div className="card-body">
                    <h2 className="text-center">Registrar producto</h2>
                    <ProductForm categories={categories} units={units}  />          
                </div>
                </div>
            </div>
        </section>

        <section className="container-fluid">
            <div className="row mb-4">
                <div className="card col-12 shadow mb-5 rounded border-0">
                    <div className="card-body">
                        <FiltersAndSearch  categories={categories} units={units} />
                        <ProductTable products={products} />
                    </div>
                </div>
            </div>
        </section>



        </>
    
    )
  }