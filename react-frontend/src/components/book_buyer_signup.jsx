import React from 'react'
import PropTypes from 'prop-types'
import useChangeHandler from '../hooks/useChangeHandler'

const BookBuyerSignup = ({user, setUser}) => {
  return (
    <>
        <label className='signup-label' htmlFor="email">
            Email ID
            <input className='input-field-signup' type="text" id="email" 
                required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
        </label>

        <label className='signup-label' htmlFor="password">
            Password
            <input className='input-field-signup' type="password" id="password" 
                required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
        </label>

        <label className='signup-label' htmlFor="confirmPassword">
            Confirm your Password
            <input className='input-field-signup' type="text" id="confirmPassword"
                required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
        </label>
    </>
  )
}

BookBuyerSignup.propTypes = {
    user: PropTypes.object.isRequired,
    setUser: PropTypes.func.isRequired
}

export default BookBuyerSignup
