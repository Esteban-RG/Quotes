import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";

import ProductForm from "./ProductForm";
import Modal from "../../../components/Modal";
import CategoryForm from "./CategoryForm";
import CategoryTable from "./CategoryTable";
import UnitTable from "./UnitTable";
import UnitForm from "./UnitForm";

export default function EditProduct(){

    const { id } = useParams();

    const [product, setProduct] = useState({});
    const [categories, setCategories] = useState([]);
    const [units, setUnits] = useState([]);

    const fetchCategories = () => {
        fetch("/api/categories")
            .then(res => res.json())
            .then(data => setCategories(data))
            .catch(err => console.error("Error cargando categorías: ", err));
    }

    const fetchUnits = () => {
        fetch("/api/units-of-measure")
            .then(res => res.json())
            .then(data => setUnits(data))
            .catch(err => console.error("Error cargando unidades: ", err));
    }

    const fetchProduct = () =>{
        fetch(`/api/products/${id}`)
            .then(res => res.json())
            .then(data => setProduct(data))
            .catch(err => console.error("Error cargando el producto: ", err))
    }

    useEffect(() => {
        fetchCategories();
        fetchUnits();
        fetchProduct();
    }, [id]);

    return (

        <section className="container-fluid">
            <div className={`row justify-content-center`}>
                <div className="card col-sm-11 col-md-7 col-lg-6 shadow p-3 mb-5 bg-white border-0 rounded">
                <div className="card-body">
                    <h2 className="text-center">Editar producto</h2>
                    <ProductForm categories={categories} units={units} action={"update"} product={product}/>
                </div>
                </div>
            </div>


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
    );
}