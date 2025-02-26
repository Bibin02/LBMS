import React from 'react'
import renderTableRows from '../services/renderTableRows'

const UserHome = (props) => {
  
  return (
    <>
        <div className="inner-container container">
          <pre>
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
          </pre>
        </div>
    </>
  )
}

export default UserHome
