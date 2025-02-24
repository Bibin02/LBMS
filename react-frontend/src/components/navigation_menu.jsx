import React, { useEffect, useState } from 'react'
import { Link, useLocation } from "react-router-dom";
import getUserId from '../services/getUserId';

const NavigationMenu = () => {

  const [isLogin, setIsLogin] = useState(false);
  const [isHomelocation, setIsHomeLocation] = useState(useLocation().pathname === '/');

  const userid = getUserId();

  function checkLogin() {
    // local check only
    let isLoggedIn = true;

    if (isLoggedIn) {
      setIsLogin(true)
    }
    else{
      setIsLogin(false);
    }
  }

  useEffect( () => {
    checkLogin()
  }, [])

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
                    {isLogin ? <li><Link to={`/users/${userid}`}>Hello {userid}</Link></li> : <li><Link to={"/login"}>Login</Link></li>}
                    <li><Link to={"/cart"}>Cart</Link></li>
                    <li><Link to={"/orders"}>Orders</Link></li>
                </ul>
            </div>
        </div>
    </>
  )
}

export default NavigationMenu;
