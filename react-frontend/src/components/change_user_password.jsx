import React, { useState } from 'react'
import useChangeHandler from '../hooks/useChangeHandler'
import NotificationPanel from './notification_panel';
import { defaultSubmitHandler } from '../utils/submitHandlers';

const ChangeUserPassword = () => {

  const [pass, setPass] = useState({});
  const [previewMessage, setPreviewMessage] = useState(null);
  
  return (
    <>
        <NotificationPanel
          previewMessage= {previewMessage}
          setPreviewMessage={setPreviewMessage}
        />

        <form onSubmit={(e)=>defaultSubmitHandler(pass, "props.formAction", e, setPreviewMessage)} 
         className="form-container container">
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
          <button className="buttons submit-button" type='submit'>
            Change
          </button>
        </form>
    </>
  )
}

export default ChangeUserPassword
