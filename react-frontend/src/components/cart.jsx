import React, { useEffect, useState } from 'react'
import Navigate from './navigate'
import fetchJSON from '../services/dataFetcher';
import CartItem from './cart_item';

const Cart = () => {

  const [cartJson, setCartJson] = useState({data: []})
  let totalCost = 0;

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
                    bookName = {item.name}
                    previewImage = {item.img}
                    quantity = {item.quantity}
                    cost = {item.cost}
                    isLend = {item.isLend}
                    lendDuration = {item.lendDuration}
                  />)
                })}
              </div>
            </div>
            <div className="summary-container">
              <div className="summary-inner-container">
                <div className="purchase-cost">
                  <span className="currency">{"Rs"}</span>
                  <span className="total-cost">{totalCost}</span>
                </div>
                <button className="purchase-button">Purchase</button>
              </div>
            </div>
        </div>
    </>
  )
}

export default Cart
