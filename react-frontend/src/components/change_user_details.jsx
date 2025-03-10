import React from 'react'
import FormEdit from './form_edit'

const ChangeUserDetails = (props) => {
  return (
    <>
      <div className="outer-container container">
          <FormEdit
            jsonData = {props.propsObject.userData}
            setJsonData = {props.propsObject.setUserData}
           />
      </div>
    </>
  )
}

export default ChangeUserDetails
