import React from 'react'

const InputField = () => {
  return (
    <>
        <div className="login-container outer-box">
            <div className="input-container">
                <input className='input-field-login' type="text" name="username" id="uid"/>
                <input className='input-field-login' type="password" name="password" id="pwd" />
            </div>

            <button id='lgn-btn' className='login-button' type="button">Login</button>

            <div className="signup-query">Create an account ?</div>

            <button id='sgnup-btn' className='login-button' type="button">Sign-Up</button>
        </div>
    </>
  )
}

export default InputField
