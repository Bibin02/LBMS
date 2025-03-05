import React from 'react'
import renderTableRows from '../services/renderTableRows'

const ChangeUserDetails = (props) => {
  return (
    <>
      <div className="outer-container container">
          <div className='table-container container'>
            {
              props.userData.userId ? 
              (<table className="user-description table">
                  <tbody className="table-body">
                      {renderTableRows(props.userData)}
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

export default ChangeUserDetails
