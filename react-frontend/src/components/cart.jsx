import React, { useEffect, useState } from 'react'
import Navigate from './navigate'
import fetchJSON from '../services/dataFetcher';
import { getLocalCurrency, convertTo, calculateLendDuration } from '../utils/converters';
import CartItem from './cart_item';

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
        <Navigate/>
        <div className="outer-container body-container">
            <div className="cart-list-container">
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
                    cost = {convertTo(item.cost, currencyVal)}
                    isLend = {item.isLend}
                    lendDuration = {calculateLendDuration(item.lendDuration)}
                  />)
                })}
              </div>
            </div>
            <div className="summary-container">
              <div className="summary-inner-container">
                <div className="purchase-cost">
                  <span className="currency">{currency}</span>
                  <span className="total-cost">{convertTo(totalCost, currencyVal)}</span>
                </div>
                <button className="purchase-button">Purchase</button>
              </div>
            </div>
        </div>
    </>
  )
}

export default Cart
