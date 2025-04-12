import React, { useContext, useState } from 'react'
import { Link, useLocation } from "react-router-dom";
import { AppContext } from './app_context';

import CartIcon from '../assets/images/cart.svg';
import BookFilter from './book_filter';

const NavigationMenu = () => {

  const [bookFilter, setBookFilter] = useState({filterBy: "Book Name"});
  const { isUserLogin, loginUserId, cartBookCount } = useContext(AppContext);
  const isHomelocation = useLocation().pathname === '/';

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

              {isHomelocation && 
              <div className="search-bar nav-bar-item">
                <BookFilter
                    bookFilter = {bookFilter}
                    setBookFilter = {setBookFilter}
                    preventFormAction={false}
                />
              </div>
              }

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
                <div className='nav-bar-item'>
                  <Link to={"/orders"}>Orders</Link>
                </div> 
                : 
                <div className="nav-bar-item">
                  <Link to={"/signup"}>Register</Link>
                </div>
              }
              
              <div className='nav-cart-container nav-bar-item'>
                <Link to={"/cart"}>
                  <div className="cart-icon-container">
                    <div className="cart-icon">
                      <img src={CartIcon} alt="|_|" />
                      <div className="cart-item-count">
                        {cartBookCount}
                      </div>
                    </div>
                    <div className="cart-name">
                      Cart
                    </div>
                  </div>
                </Link>
              </div>

            </div>
        </div>
    </>
  )
}

export default NavigationMenu;
