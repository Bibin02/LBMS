import React from 'react'
import renderTableRows from '../services/renderTableRows'
import SellerDashboard from './seller_dashboard'

const UserHome = ({propsObject}) => {
  
  return (
    <>
        <div className="inner-container container">
          <div className='table-container container'>
            {propsObject.userData.userId ? 
              (<>
                <table className="user-description table">
                    <tbody className="table-body">
                        {renderTableRows(propsObject.userData)}
                    </tbody>
                </table>
                {propsObject.userData.isSeller && <SellerDashboard sellerId={propsObject.userData.userId}/>}
              </>)
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
