import React from "react"
import ReactDOM  from "react-dom"
import {Home} from "./views/home"
import { createRoot } from "react-dom/client"

const home = document.getElementById("home")
const root = createRoot(home)
root.render(<Home />)