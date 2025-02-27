import React, { useEffect, useState } from 'react'
import fetchJSON from '../services/dataFetcher';
import { getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency, calculateLendDuration } from '../utils/utility';
import CartItem from './cart_item';
import NavigationMenu from './navigation_menu';

import '../styles/cart.css'

const Cart = () => {

  const [cartJson, setCartJson] = useState({data: []})
  let totalCost = 0;
  const { currency, currencyVal } = getLocalCurrency();

  async function getData() {
    setCartJson(await fetchJSON("/cart.json"));
  }

  useEffect( ()=>{
    getData();
  }, [])

  return (
    <>
        <NavigationMenu/>
        <main className="outer-container body-container">
            <div className="cart-list container">
              <div className="cart-items-box">
                {cartJson.data.map((item, index)=>{
                  totalCost += item.cost;
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
                <div className="purchase-cost">
                  <span className="currency">{currency}</span>
                  <span className="total-cost">{convertCurrency(totalCost, currencyVal)}</span>
                </div>
                <button className="purchase-button">Purchase</button>
              </div>
            </aside>
        </main>
    </>
  )
}

export default Cart
