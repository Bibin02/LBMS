import React from 'react'
import PropTypes from 'prop-types'
import UserHome from './user_home';
import ChangeUserDetails from './change_user_details';
import ChangeUserPassword from './change_user_password';
import ViewBook from './view_book';
import RemoveBook from './remove_book';
import AddBook from './add_book';

const ComponentDispatcher = (props) => {

    const loadedComponents = {
        UserHome, ChangeUserPassword, ChangeUserDetails, AddBook, RemoveBook, ViewBook
    }

    return (loadedComponents[props.targetComponentName] ? 
        React.createElement(loadedComponents[props.targetComponentName], {userData: props.targetComponentProps}) : <p>Some error occured !</p>);
}

ComponentDispatcher.propTypes = {
    targetComponentName: PropTypes.string.isRequired
}

export default ComponentDispatcher;
