
const Ticket = (ticket) => {
    return (
        <div className="ticket">
            <div className="ticket-cell">
                {ticket.ticket.flight}
            </div>
            <div className="ticket-cell">
                {ticket.ticket.dateBooked}
            </div>
        </div>
    )
}

export default Ticket