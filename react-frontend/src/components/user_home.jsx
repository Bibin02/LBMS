import React, { useContext } from 'react'
import renderTableRows from '../services/renderTableRows'
import SellerDashboard from './seller_dashboard'
import LendBooksDisplay from './lend_books_display'
import { AppContext } from './app_context'

const UserHome = ({propsObject}) => {

  const { loginUserId } = useContext(AppContext);
  
  return (
    <>
        <div className="inner-container container">
          <div className='table-container container'>
            {propsObject.userData.userId ? 
              (<>
                {loginUserId != null &&
                  <>{propsObject.userData.isSeller ? 
                      <SellerDashboard 
                        sellerId={propsObject.userData.userId}
                      />
                      :
                      <LendBooksDisplay
                        userId={propsObject.userData.userId}
                      />}
                  </>
                }
                <table className="user-description table">
                    <tbody className="table-body">
                        {renderTableRows(propsObject.userData)}
                    </tbody>
                </table>
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
