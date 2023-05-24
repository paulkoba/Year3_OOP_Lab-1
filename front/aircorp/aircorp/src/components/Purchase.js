import { useAuth0 } from '@auth0/auth0-react';
import Navbar from './Navbar';
import ErrorPage from './Error'
import { Link, useParams } from 'react-router-dom';

const Purchase = () => {
    const { isAuthenticated, user } = useAuth0();
    const { id } = useParams() 
    const doPurchase = (str) => () => {
      var xhr = new XMLHttpRequest();
      xhr.open("GET", "http://localhost:8080/aircorp-1.0-SNAPSHOT/purchaseTicket?user_id=" + user.sub + "&flight=" + str, false);
      xhr.send();
      return xhr.responseText;
    }

    return (
        isAuthenticated && (
            <div>
                <Navbar />
                <div>
                  <h2>Ticket purchase confirmation</h2>
                  <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc</p>
                  <Link to="/flights/" onClick={doPurchase(id)}>Purchase ticket</Link>
                </div>
            </div>
        ) || (
            <ErrorPage />
        )
    )
}

export default Purchase