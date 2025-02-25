import React from 'react'

const UserHome = (props) => {

  return (
    <>
        <div className="inner-container container">
          <pre>
            {
              props.userData.userId ? 
              (<div className="data-preview">
                {JSON.stringify(props.userData)}
              </div>)
              : 
              (<div className="error-message">
                User not found !
              </div>)
            }
          </pre>
        </div>
    </>
  )
}

export default UserHome
