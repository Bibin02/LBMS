import '../styles/cart.css'

import React, { useContext } from 'react'
import { getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency, calculateLendDuration } from '../utils/utility';
import { AppContext } from './app_context';
import { placeOrder } from '../services/cart';
import CartItem from './cart_item';
import NavigationMenu from './navigation_menu';
import FooterContent from './footer_content';
import { Link } from 'react-router-dom';


const Cart = () => {

  const { cartJson, setCartJson, totalCartCost, cartBookCount } = useContext(AppContext);
  const { currency, currencyVal } = getLocalCurrency();

  return (
    <>
        <NavigationMenu/>
        <main className="outer-container container">
          <div className="cart-inner-container">
            { cartBookCount > 0 ? 
            <>
              <div className="cart-list cart-inner-container-item">
                <div className="cart-items-box">
                  {cartJson.data.map((item, index)=>(
                    <div className="single-cart-item" key={index}>
                      <CartItem
                        bookUid = {item.bookUid}
                        bookName = {item.bookName}
                        previewImage = {item.img ? item.img : "/images/Book.jpg" }
                        quantity = {item.quantity}
                        currency = {currency}
                        cost = {convertCurrency(item.cost, currencyVal)}
                        isLend = {item.isLend}
                        lendDuration = {calculateLendDuration(item.lendDuration)}
                      />
                    </div>
                    ))
                  }
                </div>
              </div>
              <aside className="cart-inner-container-item">
                <div className="summary-container">
                  <div className="summary-inner-item">                 
                    <div className="cost-summary">
                      <strong className='summary-inner-head '> Total Cost </strong>
                    </div>
                    <em className="total-cost">
                      <mark className="currency">
                        {currency}
                        {convertCurrency(totalCartCost, currencyVal)}
                      </mark>
                    </em>
                  </div>
                  <div className="summary-inner-item">                 
                    <div className="quantity-summary">
                      <strong className='summary-inner-head'> Total Cart Items </strong>
                    </div>
                    <strong className="quantity">{cartBookCount}</strong>
                  </div>
                  <div className="summary-inner-item">
                    <button className="summary-inner-head purchase-button buttons" 
                      onClick={()=>placeOrder(cartJson, setCartJson)}>
                        Purchase
                    </button>
                  </div>
                </div>
              </aside>
            </>
            :
            <div className="cart-empty-bgimage-container cart-inner-container-item">
              <figure className="cart-empty-bg-image">
                <img src="/images/cart_empty.jpg" alt="! |_|" />
                <figcaption>Cart is Empty !</figcaption>
              </figure>
            </div>
            }
          </div>

          <div className="cart-buttons-container">
            <Link to={"/"}>
            <button type="button" className='buttons'>
              Search Books
            </button>
            </Link>
            <Link to={'/orders'}>
            <button type='button' className='buttons'>
              Orders
            </button>
            </Link>
          </div>
          

        </main>
        <FooterContent/>
    </>
  )
}

export default Cart
