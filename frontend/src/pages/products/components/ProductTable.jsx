export default function ProductTable(props) {

    const handleDelete = async (productId) => {
        if (window.confirm("¿Estás seguro que deseas eliminar este producto?")) {
            console.log("Eliminar producto con ID:", productId);
            try {
                const response = await fetch(`/api/products/${productId}`, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json",
                    },
                });
        
                if (!response.ok) {
                    throw new Error("Error al eliminar el producto");
                }
        
                alert("Producto eliminado correctamente");
                props.onProductDeleted();
            } catch (error) {
                console.error("Error eliminando producto:", error);
                alert("Hubo un error al eliminar el producto");
            }
        }
    };
    return (
    <>
    <table className="table" id="productsTable">
        <thead className="table-primary">
            <tr>
                <th scope="col">ID</th>
                <th scope='col'>Descripción</th>
                <th scope="col">Precio</th>
                <th scope="col" className="text-center">Acciones</th>
            </tr>
        </thead>
        <tbody>
            {props.products.map(product => (
                <tr data-category={product.category.id}>
                    <th scope="row">{product.id}</th>
                    <td>{product.description}</td>
                    <td className="price-format">{product.price}</td>
                    <td>
                        <div className="d-flex justify-content-center gap-2">
                            <button className="btn btn-info btn-sm" type="button" data-bs-toggle="modal"
                                data-bs-target={ "#"+product.id}>
                                <i className="fas fa-eye"></i>
                            </button>

                            <div className="modal fade" id={product.id} tabindex="-1"
                                aria-labelledby={"modal"+product.id+"Label"} aria-hidden="true">
                                <div className="modal-dialog modal-dialog-centered modal-lg">
                                    <div className="modal-content">
                                        <div className="modal-header bg-primary text-white">
                                            <h5 className="modal-title" id={"modal"+product.id+"Label"}>Detalles del
                                                Producto</h5>
                                            <button type="button" className="btn-close btn-close-white"
                                                data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div className="modal-body">
                                            <div className="row">

                                                {product.img_path ? 
                                                    <div className="col-md-6 col-sm-12 mb-3">
                                                        <img src={product.img_path}
                                                            className="img-fluid rounded" alt="Producto 1" />
                                                    </div>
                                                :""}
                                                

                                                <div className={ (product.img_path) ? 'col-md-6 col-sm-12' : 'col-12' }>
                                                    <h4 className="mb-3">{product.description}</h4>

                                                    <ul className="list-group list-group-flush mb-4">
                                                        <li className="list-group-item">
                                                            <strong>Unidad de medida:</strong>{product.unitOfMeasure.name}
                                                        </li>
                                                        <li className="list-group-item">
                                                            <strong>Categoría:</strong>{product.category.name}
                                                        </li>
                                                        <li className="list-group-item">
                                                            <strong>Precio:</strong> <span
                                                                className="price-format">{product.price}</span>
                                                        </li>
                                                        <li className="list-group-item">
                                                            <strong>ID:</strong>{product.id}
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-secondary"
                                                data-bs-dismiss="modal">Cerrar</button>
                                            <a href="" className="btn btn-primary">Editar
                                                Producto</a>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            {/* Botón Editar */}
                            <a href={`/edit-product/${product.id}`} className="btn btn-primary btn-sm" title="Editar" aria-label="Editar">
                                <i className="fas fa-edit"></i>
                            </a>

                            {/* Botón Eliminar */}
                            <button className="btn btn-danger btn-sm" title="Eliminar" aria-label="Eliminar"
                                onClick={() => handleDelete(product.id)}>
                                <i className="fas fa-trash"></i>
                            </button>
                        </div>
                    </td>
                </tr>
            ))}
        </tbody>
        <div id="noResults" className="text-center py-4 d-none">
            <i className="fas fa-search fa-3x mb-3 text-muted"></i>
            <h5 className="text-muted">No se encontraron productos</h5>
            <p className="text-muted">Intenta con otros términos de búsqueda</p>
        </div>
    </table>
    </>
    );
}