import { useAuth0 } from '@auth0/auth0-react';
import Navbar from './Navbar';
import ErrorPage from './Error';
import { useEffect, useState } from 'react';
import Ticket from './Ticket';
import TicketsHeader from './TicketsHeader';
import "./css/tickets.css"

const Profile = () => {
    const { user, isAuthenticated } = useAuth0();
    const [flights, setFlights] = useState([]);

    useEffect(() => {
        try {
            let responseData = JSON.parse(getResponse()); 
            let out = Array()
    
            Object.entries(responseData).forEach(item => {
                out.push(item)
            })
            
            setFlights(out)
        } catch (error) {
            console.error("Error: ", error.message);
        }
    }, []);

    const getResponse = () => {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8080/aircorp-1.0-SNAPSHOT/userInfo?user_id=" + user.sub, false);
        xhr.send();
        return xhr.responseText;
    }
    
    return (
        isAuthenticated && (
            <div>
                <Navbar />
                <TicketsHeader></TicketsHeader>
                {flights.map((ticket) => <Ticket ticket={ticket} />)}
            </div>
        ) || (
            <ErrorPage />
        )
    )
}

export default Profile