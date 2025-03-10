import React from 'react'
import renderTableRows from '../services/renderTableRows'

const UserHome = (props) => {
  
  return (
    <>
        <div className="inner-container container">
          <div className='table-container container'>
            {
              props.propsObject.userData.userId ? 
              (<table className="user-description table">
                  <tbody className="table-body">
                      {renderTableRows(props.propsObject.userData)}
                  </tbody>
              </table>)
              : 
              (<div className="error-message">
                User not found !
              </div>)
            }
          </div>
        </div>
    </>
  )
}

export default UserHome
