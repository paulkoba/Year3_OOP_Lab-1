import { useAuth0 } from '@auth0/auth0-react';
import {  Link } from "react-router-dom";

const ProfileButton = () => {
    const { isAuthenticated } = useAuth0();

    return (
        isAuthenticated && (
            <Link to="/profile">Profile</Link>
        )
    )
}

export default ProfileButton