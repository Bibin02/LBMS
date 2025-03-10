import React, { useState } from 'react'
import useChangeHandler from '../hooks/useChangeHandler'

const ChangeUserPassword = () => {

  const [pass, setPass] = useState({});

  function changePassword() {
    alert("password changed Successfully");
  }
  
  return (
    <>
        <div className="form-container container">
          <label htmlFor="oldPassword">
              Enter your old password
              <input className='input-field' type="password" id="oldPassword"
                  required onChange={(e)=>useChangeHandler(pass, e, setPass)}/>
          </label>
          <label htmlFor="newPassword">
              Enter your new password
              <input className='input-field' type="text" id="newPassword" 
                  required onChange={(e)=>useChangeHandler(pass, e, setPass)}/>
          </label>
          <button className="buttons submit-button" onClick={changePassword}>
            Change
          </button>
        </div>
    </>
  )
}

export default ChangeUserPassword
