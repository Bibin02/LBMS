import React, { useContext, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import NavigationMenu from './navigation_menu';
import ComponentDispatcher from './component_dispatcher';
import { getUserData } from '../services/userService';
import { AppContext } from './app_context';

import '../styles/user_dashboard.css'

const UserDashboard = () => {
  
    const { isUserLogin, setIsUserLogin, loginUserId, setLoginUserId } = useContext(AppContext);
    const { userid } = useParams();
    const isLoginUser = (userid === loginUserId && isUserLogin);
    let menus = (isLoginUser) ? ["UserHome", "ChangeUserPassword", "ChangeUserDetails", "Logout"] : ["UserHome"];
    const [userData, setUserData] = useState(getUserData(userid));
    (isLoginUser && userData.isSeller) ? menus = [...menus, "SellerAddBook","SellerViewBook"] : null;
    const [currMenu, setCurrMenu] = useState(menus[0]);
    

    function selectMenu(event) {
      setCurrMenu(menus[event.target.dataset.key]);
    }

    function logoutAction() {
      setLoginUserId(null);
      setIsUserLogin(false);
    }

  return (
    <>
      <main className="outer-container container">
        <NavigationMenu/>
        <div className="outer-container container">
          <h1 className="text-indigo-600 m-12">User {userid}</h1>
          <div className="profile-container">

            <div className="profile-pic-container">
              <div className="profile-pic"><img src={""} />&</div>
              {isLoginUser ? <div className="add-profile-pic">+</div> : null}
            </div>

            <div className="profile-menus">
              {
                menus.map( (menuName, index)=>{
                  return (
                    <div key={index}> {
                      menuName == "Logout" ? 
                        (<Link to={"/"} onClick={logoutAction}><div className="menuitem buttons" data-key={index}>{menuName}</div></Link>) 
                        : 
                        (<div className="menuitem buttons" data-key={index} onClick={selectMenu}>{menuName}</div>)
                      }
                    </div>
                  )
                })
              }
            </div>
          </div>
          <div className="content-container container">
              <ComponentDispatcher
                targetComponentName = {currMenu}
                targetComponentProps = {{userData: userData, setUserData: setUserData}}
                
              />
          </div>
        </div>
      
      </main>
    </>
  )
}

export default UserDashboard
