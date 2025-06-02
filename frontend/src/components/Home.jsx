import { Link } from "react-router-dom";

export default function Home() {
    return (
        <div>
            <h1>Bienvenido a la página principal</h1>
            <Link to="/about">Acerca de</Link>
        </div>
    )
  }