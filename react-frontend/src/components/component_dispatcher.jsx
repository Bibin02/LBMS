import React from 'react'
import PropTypes from 'prop-types'
import UserHome from './user_home';
import ChangeUserDetails from './change_user_details';
import ChangeUserPassword from './change_user_password';

const ComponentDispatcher = (props) => {

    const loadedComponents = {
        UserHome, ChangeUserPassword, ChangeUserDetails 
    }

    return (loadedComponents[props.targetComponentName] ? 
        React.createElement(loadedComponents[props.targetComponentName]) : <p>Some error occured !</p>);
}

ComponentDispatcher.propTypes = {
    targetComponentName: PropTypes.string.isRequired
}

export default ComponentDispatcher;
