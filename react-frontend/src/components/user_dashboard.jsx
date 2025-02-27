import React, { useContext, useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import NavigationMenu from './navigation_menu';
import ComponentDispatcher from './component_dispatcher';
import { getUserData } from '../services/userService';
import { AppContext } from './app_context';

import '../styles/user_dashboard.css'

const UserDashboard = () => {
  
    const { isUserLogin, loginUserId } = useContext(AppContext);
    const { userid } = useParams();
    const isLoginUser = (userid === loginUserId && isUserLogin);
    const menus = (isLoginUser) ? ["UserHome", "ChangeUserPassword", "ChangeUserDetails", "Logout"] : ["UserHome"];
    const [currMenu, setCurrMenu] = useState(menus[0]);
    
    const userData = getUserData(userid);

    function selectMenu(event) {
      setCurrMenu(menus[event.target.dataset.key]);
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
                        (<Link to={"/"}><div className="menuitem buttons" data-key={index}>{menuName}</div></Link>) 
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
                targetComponentProps = {userData}
              />
          </div>
        </div>
      
      </main>
    </>
  )
}

export default UserDashboard
