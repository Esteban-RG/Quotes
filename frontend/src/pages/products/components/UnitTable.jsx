import { useEffect, useState } from "react";

export default function UnitTable({ units }) {
    const [editedCategories, setEditedCategories] = useState([]);

    useEffect(() => {
        if (units.length > 0) {
            setEditedCategories(units.map(c => ({ ...c, isEditing: false })));
        }
    }, [units]);


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
        const unit = editedCategories.find(cat => cat.id === id);
        
        try {
            const response = await fetch(`/api/units-of-measure/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ name: unit.name })
            });
    
            if (!response.ok) {
                throw new Error("Error al guardar la categoría");
            }
    
            console.log("Guardado con éxito:", unit);
    
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
            const response = await fetch(`/api/units-of-measure/${id}`, {
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
                {editedCategories.map(unit => (
                    <tr key={unit.id}>
                        <td>{unit.id}</td>
                        <td>
                            {unit.isEditing ? (
                                <input
                                    type="text"
                                    className="form-control"
                                    value={unit.name}
                                    onChange={e => handleNameChange(unit.id, e.target.value)}
                                />
                            ) : (
                                <span>{unit.name}</span>
                            )}
                        </td>
                        <td>
                            <div className="d-flex justify-content-center gap-2">
                                {unit.isEditing ? (
                                    <button
                                        className="btn btn-success btn-sm"
                                        onClick={() => handleSave(unit.id)}
                                    >
                                        <i className="fas fa-save"></i>
                                    </button>
                                ) : (
                                    <button
                                        className="btn btn-primary btn-sm"
                                        onClick={() => handleToggleEdit(unit.id)}
                                    >
                                        <i className="fas fa-edit"></i>
                                    </button>
                                )}

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() => handleDelete(unit.id)}
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
