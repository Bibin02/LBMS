import React, { useState } from 'react'
import { Link, useParams } from 'react-router-dom';
import NavigationMenu from './navigation_menu';
import ComponentDispatcher from './component_dispatcher';

const UserDashboard = () => {
    const {userid} = useParams();

    const menus = ["UserHome", "ChangeUserPassword", "ChangeUserDetails", "Logout"];
    
    const [currMenu, setCurrMenu] = useState(menus[0]);

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
              <div className="profile-pic"><img src={"&"} /></div>
              <div className="add-profile-pic">+</div>
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
              />
          </div>
        </div>
      
      </main>
    </>
  )
}

export default UserDashboard
