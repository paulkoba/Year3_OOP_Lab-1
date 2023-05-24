import {  Link } from "react-router-dom";
import LoginButton from "./LoginButton"
import LogoutButton from "./LogoutButton"
import ProfileButton from "./ProfileButton";

const Navbar = () => {
    return (
        <nav>
            <Link to="/flights">Flights</Link>
            <Link to="/">About</Link>
            <ProfileButton />
            <LoginButton />
            <LogoutButton />
        </nav>
    )
}

export default Navbar