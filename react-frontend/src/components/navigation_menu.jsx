import React, { useContext } from 'react'
import { Link, useLocation } from "react-router-dom";
import { AppContext } from './app_context';

import '../styles/nav_menu.css'

const NavigationMenu = () => {

  const { isUserLogin, loginUserId } = useContext(AppContext);
  const isHomelocation = useLocation().pathname === '/';

  return (
    <>
        <div className="navbar">
            {isHomelocation ? <div className="search-bar">
              <form action="" method="get">
                <input type="text" name="searchbar" id="searchContent" />
              </form>
            </div> : null }
            <div className="navbar-links">
                <ul>
                    <li><Link to={"/"}>Home</Link></li>
                    {isUserLogin ? <li><Link to={`/users/${loginUserId}`}>Hello {loginUserId}</Link></li> : <li><Link to={"/login"}>Login</Link></li>}
                    <li><Link to={"/cart"}>Cart</Link></li>
                    {isUserLogin ? <li><Link to={"/orders"}>Orders</Link></li> : null }
                </ul>
            </div>
        </div>
    </>
  )
}

export default NavigationMenu;
