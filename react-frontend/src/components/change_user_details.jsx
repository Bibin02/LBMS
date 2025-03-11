import React, { useState } from 'react'
import FormEdit from './form_edit'
import { formatUserDataToEdit } from '../services/userService'

const ChangeUserDetails = ({propsObject}) => {
  const [userDataEditable, setUserDataEditable] = useState(formatUserDataToEdit(propsObject.userData));
  return (
    <>
      <div className="outer-container container">
          <FormEdit
            jsonData = {userDataEditable}
            setJsonData = {setUserDataEditable}
            formAction = {`/users/${propsObject.userData.userId}`}
           />
      </div>
    </>
  )
}

export default ChangeUserDetails
