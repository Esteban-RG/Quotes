import React from "react";
import { Routes, Route } from "react-router-dom";
import NavBar from "./components/navBar";
import Home from "./pages/Home";
import About from "./pages/About";
import NotFound from "./pages/NotFound";
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
      <Route path="/about" element={<About />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
    </>
  );
  

}

export default App;
