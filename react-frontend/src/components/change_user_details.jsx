import React from 'react'
import FormEdit from './form_edit'

const ChangeUserDetails = (props) => {
  return (
    <>
      <div className="outer-container container">
          <FormEdit
            jsonData = {props.propsObject.userData}
            setJsonData = {props.propsObject.setUserData}
            formAction = {`/users/${props.propsObject.userData.userId}`}
           />
      </div>
    </>
  )
}

export default ChangeUserDetails
