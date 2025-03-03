import React, { useContext, useEffect, useState } from 'react'
import { getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency, calculateLendDuration } from '../utils/utility';
import { AppContext } from './app_context';
import CartItem from './cart_item';
import NavigationMenu from './navigation_menu';

import '../styles/cart.css'

const Cart = () => {

  const { cartJson, totalCartCost, cartBookCount } = useContext(AppContext);
  const { currency, currencyVal } = getLocalCurrency();

  return (
    <>
        <NavigationMenu/>
        <main className="outer-container container">
          { cartBookCount > 0 ? 
          <div className="inner-container container">
            <div className="cart-list container">
              <div className="cart-items-box">
                {cartJson.data.map((item, index)=>{
                  return (<CartItem
                    key = {index}
                    bookUid = {item.bookUid}
                    bookName = {item.bookName}
                    previewImage = {item.img}
                    quantity = {item.quantity}
                    currency = {currency}
                    cost = {convertCurrency(item.cost, currencyVal)}
                    isLend = {item.isLend}
                    lendDuration = {calculateLendDuration(item.lendDuration)}
                  />)
                })}
              </div>
            </div>
            <aside className="summary container">
              <div className="summary-inner container">
                <div className="summary-inner-item">                 
                  <div className="cost-summary">
                    <strong> Total Cost </strong>
                  </div>
                  <div className="prize-tag">
                    <em className="currency">{currency}</em>
                    <em className="total-cost">{convertCurrency(totalCartCost, currencyVal)}</em>
                  </div>
                </div>
                <div className="summary-inner-item">                 
                  <div className="quantity-summary">
                    <strong> Total Cart Items </strong>
                  </div>
                  <div className="prize-tag">
                    <em className="quantity">{cartBookCount}</em>
                  </div>
                </div>
                <div className="purchace-flex">
                  <button className="purchase-button buttons">Purchase</button>
                </div>
              </div>
            </aside>
          </div>
          :
          <div className="inner-container container">
            <div className="bg-image-container">
              <figure className="bg-image">
                <img src="empty-cart" alt="! |_|" />
                <figcaption>Cart is Empty !</figcaption>
              </figure>
            </div>
          </div>
          }
        </main>
    </>
  )
}

export default Cart
