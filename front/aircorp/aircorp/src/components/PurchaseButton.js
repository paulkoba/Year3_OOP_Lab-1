
import { Link } from "react-router-dom"

const PurchaseButton = (id) => {
    return (
        <Link to={"/purchase/" + id.id } >
            Purchase
        </Link>
    )
}

export default PurchaseButton