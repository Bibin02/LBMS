import React, { useEffect, useState } from 'react'
import { Link, useSearchParams } from 'react-router-dom'
import fetchJSON from '../services/dataFetcher';
import { getExtraCharges, getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency } from '../utils/utility';

import '../styles/order_dispatch.css'
import NavigationMenu from './navigation_menu';

const OrderDispatch = () => {

    const [searchParams] = useSearchParams();
    const [orderJson, setOrderJson] = useState({});

    const { currency, currencyVal } = getLocalCurrency();

    const { charges } = getExtraCharges();
    let totalCharges = 0;

    useEffect( ()=>{
      const getData = async () => {
        setOrderJson(await fetchJSON("/order.json"))
      }
      getData();
    }, [])
    
  return (
    <>
        <main className="container outer-container">
            <NavigationMenu/>
            <h1>Order ID : {searchParams.get("oid")}</h1>
            {orderJson.fetchStatus ? (
              <div className="container inner-container">
                <div className="container">
                  {orderJson.items.map((item, index)=>(
                      <div className="order-book" key={index}>
                          <figure className="order-book-thumbnail">
                            <img src={item.imgsrc ? item.imgsrc : "/images/Book.jpg" } alt="src.jpg" />
                          </figure>
                          <aside className="details-container">
                            <div className="order-book-name">{item.name}</div>
                            <div className="order-book-quantity">{item.quantity}</div>
                          </aside>
                      </div>
                  ))}
                </div>
                <aside className="summary-panel container">
                    {!orderJson.isPaid ? 
                      <div className="payment">
                        <strong className="cost-name">Total Cost</strong>
                        <div className="prize-tag">
                          <span className="currency">{currency}</span>
                          <span className="total-cost">{convertCurrency(orderJson.totalCost, currencyVal)}</span>
                        </div>
                        {charges.map( (charge, index)=>{
                          totalCharges += charge.value;
                          return(<div key={index} className="prize-tags container">
                            <em className="cost-name">{charge.chargeName}</em>
                            <div className="prize-tag">
                              <span className="currency">{currency}</span>
                              <span className="charges-cost">{convertCurrency(charge.value, currencyVal)}</span>
                            </div>
                          </div>)
                        })}
                        <strong className="cost-name">Final Cost</strong>
                        <div className="prize-tag">
                          <span className="currency">{currency}</span>
                          <span className="final-cost">{convertCurrency(orderJson.totalCost + totalCharges, currencyVal)}</span>
                        </div>
                        <div className="payment-button">
                          <Link to={'/'}> 
                            <button className="buttons">
                              Complete Payment
                            </button>
                          </Link>
                        </div>
                      </div> : null
                    }
                    
                    <div className="order-progress">
                      {orderJson.orderStatus}
                    </div>
                </aside>
              </div>
            ) : (
              <div className="inner-container">
                <p className="fetch-fail-reason">
                  {orderJson.reason}
                </p>
              </div>
            )}
        </main>
        
    </>
  )
}

export default OrderDispatch
