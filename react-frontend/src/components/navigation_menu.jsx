import React, { useEffect, useState } from 'react'
import { Link } from "react-router-dom";
import getUserId from '../services/getUserId';

const NavigationMenu = () => {

  const [isLogin, setIsLogin] = useState(false);
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
