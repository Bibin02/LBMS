import React, { useContext } from 'react'
import { Link, useLocation, useNavigate } from "react-router-dom";
import { AppContext } from './app_context';

import '../styles/nav_menu.css'

const NavigationMenu = () => {

  const { searchBook, setSearchBook, isUserLogin, loginUserId, cartBookCount } = useContext(AppContext);
  const isHomelocation = useLocation().pathname === '/';
  const navigator = useNavigate();

  return (
    <>
        <div className="navbar">
            <div className="navbar-links navbar-container">
              <div className='home-page-url nav-bar-item'>
                <div className="nav-home-url">
                  <Link to={"/"}>
                    E-library
                  </Link>
                </div>
              </div>

              {isHomelocation ? 
              <div className="search-bar nav-bar-item">
                <div className="search-bar-container">
                  <form action="" method="get">
                    <input type="text" name="search" id="searchContent" onChange={(e)=>setSearchBook(e.target.value)}/>
                    <div className="search-icon-box" onClick={()=>navigator(`/?search=${searchBook}`)}>
                      <img src="" alt="?" />
                    </div>
                  </form>
                </div>
              </div> : null }

              {isUserLogin ? 
                <div className='profile-container nav-bar-item'>
                  <div className="nav-profile">
                    <Link to={`/users/${loginUserId}`}>
                      Welcome {loginUserId}
                    </Link>
                  </div>
                </div> 
              : // Else part
                <div className='profile-container nav-bar-item'>
                  <div className="nav-profile"><Link to={"/login"}>Sign in</Link></div>
                </div>
              }

              {isUserLogin ? 
                <div className='nav-orders-container nav-bar-item'>
                  <Link to={"/orders"}>Orders</Link>
                </div> 
                : null 
              }
              
              <div className='nav-cart-container nav-bar-item'>
                <Link to={"/cart"}>
                  <div className="cart-icon-container">
                    <div className="cart-icon">
                      <img src="" alt="|_|" />
                      <div className="cart-item-count">
                        {cartBookCount}
                      </div>
                    </div>
                    Cart
                  </div>
                </Link>
              </div>

            </div>
        </div>
    </>
  )
}

export default NavigationMenu;
