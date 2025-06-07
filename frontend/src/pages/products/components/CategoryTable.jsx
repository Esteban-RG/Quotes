import { useEffect, useState } from "react";

export default function CategoryTable({ categories }) {
    const [editedCategories, setEditedCategories] = useState([]);

    useEffect(() => {
        if (categories.length > 0) {
            setEditedCategories(categories.map(c => ({ ...c, isEditing: false })));
        }
    }, [categories]);


    const handleToggleEdit = (id) => {
        setEditedCategories(prev =>
            prev.map(cat =>
                cat.id === id ? { ...cat, isEditing: !cat.isEditing } : cat
            )
        );
    };

    const handleNameChange = (id, newName) => {
        setEditedCategories(prev =>
            prev.map(cat =>
                cat.id === id ? { ...cat, name: newName } : cat
            )
        );
    };

    const handleSave = async (id) => {
        const category = editedCategories.find(cat => cat.id === id);
        
        try {
            const response = await fetch(`/api/categories/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name: category.name })
            });
    
            if (!response.ok) {
                throw new Error("Error al guardar la categoría");
            }
    
            console.log("Guardado con éxito:", category);
    
            setEditedCategories(prev =>
                prev.map(cat =>
                    cat.id === id ? { ...cat, isEditing: false } : cat
                )
            );
        } catch (error) {
            console.error("Error al guardar la categoría:", error);
        }
    };
    
    const handleDelete = async (id) => {
        if (!confirm("¿Estás seguro de que deseas eliminar esta categoría?")) return;
    
        try {
            const response = await fetch(`/api/categories/${id}`, {
                method: "DELETE"
            });
    
            if (!response.ok) {
                throw new Error("Error al eliminar la categoría");
            }
    
            console.log("Eliminado con éxito:", id);
    
            setEditedCategories(prev =>
                prev.filter(cat => cat.id !== id)
            );
        } catch (error) {
            console.error("Error al eliminar la categoría:", error);
        }
    };
    

    return (
        <table className="table">
            <thead className="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th className="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                {editedCategories.map(category => (
                    <tr key={category.id}>
                        <td>{category.id}</td>
                        <td>
                            {category.isEditing ? (
                                <input
                                    type="text"
                                    className="form-control"
                                    value={category.name}
                                    onChange={e => handleNameChange(category.id, e.target.value)}
                                />
                            ) : (
                                <span>{category.name}</span>
                            )}
                        </td>
                        <td>
                            <div className="d-flex justify-content-center gap-2">
                                {category.isEditing ? (
                                    <button
                                        className="btn btn-success btn-sm"
                                        onClick={() => handleSave(category.id)}
                                    >
                                        <i className="fas fa-save"></i>
                                    </button>
                                ) : (
                                    <button
                                        className="btn btn-primary btn-sm"
                                        onClick={() => handleToggleEdit(category.id)}
                                    >
                                        <i className="fas fa-edit"></i>
                                    </button>
                                )}

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => handleDelete(category.id)}
                                >
                                    <i className="fas fa-trash"></i>
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}
