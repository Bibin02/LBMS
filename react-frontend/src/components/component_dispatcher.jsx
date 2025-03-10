import React from 'react'
import PropTypes from 'prop-types'
import UserHome from './user_home';
import ChangeUserDetails from './change_user_details';
import ChangeUserPassword from './change_user_password';
import SellerAddBook from './seller_add_book';
import SellerViewBook from './seller_view_book';

const ComponentDispatcher = (props) => {

    const loadedComponents = {
        UserHome, ChangeUserPassword, ChangeUserDetails, SellerAddBook, SellerViewBook
    }

    return (loadedComponents[props.targetComponentName] ? 
        React.createElement(loadedComponents[props.targetComponentName], {userData: props.targetComponentProps}) : <p>Some error occured !</p>);
}

ComponentDispatcher.propTypes = {
    targetComponentName: PropTypes.string.isRequired
}

export default ComponentDispatcher;
