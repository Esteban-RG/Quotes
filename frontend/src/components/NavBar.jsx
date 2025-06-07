import React from "react";
import { NavLink } from "react-router-dom";

export default function NavBar() {
  return (
    <nav className="navbar navbar-expand-lg shadow p-3 mb-3" style={{ backgroundColor: "#55BCD1" }}>
      <div className="container-fluid">
        {/* 
        <NavLink className="navbar-brand" to="/">
          <img src="" alt="Logo" width="25" height="25" />
        </NavLink>
        */ }
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
          data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
          aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse justify-content-center" id="navbarNavDropdown">
          <ul className="navbar-nav">
            <li className="nav-item">
              <NavLink to="/" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
                Inicio
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink to="/products" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
                Productos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink to="/quotes" className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
                Cotizaciones
              </NavLink>
            </li>
            
          </ul>
        </div>
      </div>
    </nav>
  );
}
