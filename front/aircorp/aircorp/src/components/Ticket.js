
const Ticket = (ticket) => {
    return (
        <div className="ticket">
            <div className="ticket-cell">
                {ticket.ticket[1].id}
            </div>
            <div className="ticket-cell">
                {ticket.ticket[1].date}
            </div>
        </div>
    )
}

export default Ticket