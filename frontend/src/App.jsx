import React from "react";
import { Routes, Route } from "react-router-dom";
import NavBar from "./components/NavBar";
import Home from "./pages/Home";

import Products from "./pages/products/Products";

function App() {
  return (
  <> 
    <header>
      <NavBar />
    </header>

    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/products" element={<Products />} />
      
    </Routes>
    </>
  );
  

}

export default App;
