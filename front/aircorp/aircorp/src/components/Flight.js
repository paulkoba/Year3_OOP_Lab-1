import PurchaseButton from "./PurchaseButton"

const Flight = (flight) => {
    return (
        <div class="flight">
            <div class="flight-cell">
                {flight.flight[1].id}
            </div>
            <div class="flight-cell">
                {flight.flight[1].seats}
            </div>
            <div class="flight-cell">
                {flight.flight[1].seatPrice}
            </div>
            <div class="flight-cell">
                {flight.flight[1].date}
            </div>
            <div class="flight-cell">
                <PurchaseButton id={flight.flight[1].id} />
            </div>
        </div>
    )
}

export default Flight