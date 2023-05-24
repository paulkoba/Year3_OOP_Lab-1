import PurchaseButton from "./PurchaseButton"

const Flight = (flight) => {
    return (
        <div class="flight">
            <div class="flight-cell">
                {flight.flight.id}
            </div>
            <div class="flight-cell">
                {flight.flight.seats}
            </div>
            <div class="flight-cell">
                {flight.flight.price}
            </div>
            <div class="flight-cell">
                {flight.flight.date}
            </div>
            <div class="flight-cell">
                <PurchaseButton id={flight.flight.id} />
            </div>
        </div>
    )
}

export default Flight