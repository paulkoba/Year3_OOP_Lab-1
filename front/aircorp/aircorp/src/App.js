import Navbar from "./components/Navbar"
import Profile from "./components/Profile"
import Flights from "./components/Flights"
import Purchase from "./components/Purchase"
import About from "./components/About"
import "./components/css/style.css"
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/flights" element={<Flights/>} />
        <Route path="/profile" element={<Profile/>} />
        <Route path="/purchase/:id" element={<Purchase/>} />
        <Route path="/" element={<About/>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;