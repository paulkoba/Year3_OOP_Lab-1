import NavBar from "./Navbar"
import Flight from "./Flight"
import React, { useState, useEffect } from 'react';
import FlightsHeader from "./FlightsHeader.js"
import "./css/flights.css"

const Flights = () => {
    const [flights, setFlights] = useState([]);

    const getResponse = () => {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "http://localhost:8080/aircorp-1.0-SNAPSHOT/flights", false);
        xhr.send();
        return xhr.responseText;
    }

    useEffect(() => {
        try {
            let responseData = JSON.parse(getResponse()); 
            let out = Array()
    
            Object.entries(responseData).forEach(item => {
                out.push(item)
            })
            
            setFlights(out)
    
            setFlights(out)
        } catch (error) {
            console.error("Error: ", error.message);
        }
    }, []);

    return (
        <div>
            <NavBar />
            <div class="flights">
                <FlightsHeader />
                {flights.map((flight) => <Flight flight={flight} />)}
            </div>
        </div>
    )
}

export default Flights