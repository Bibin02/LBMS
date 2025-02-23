import React, { useEffect, useState } from 'react'
import { Link, useSearchParams } from 'react-router-dom'
import fetchJSON from '../services/dataFetcher';
import { convertTo, getExtraCharges, getLocalCurrency } from '../utils/converters';

const OrderDispatch = () => {

    const [searchParams] = useSearchParams();
    const [orderJson, setOrderJson] = useState({});

    const { currency, currencyVal } = getLocalCurrency();

    const { charges } = getExtraCharges();
    let totalCharges = 0;

    async function getData() {
      setOrderJson(await fetchJSON("/order.json"))
    }

    useEffect( ()=>{
      getData();
    }, [])
    
  return (
    <>
        <div className="container outer-container">
            <h1>Order ID : {searchParams.get("oid")}</h1>
            {orderJson.fetchStatus ? (
              <div className="container inner-container">
                <div className="container">
                  {orderJson.items.map((item, index)=>(
                      <div className="order-book" key={index}>
                          <span className="order-book-thumbnail"><img src={item.imgsrc} alt="src.jpg" /></span>
                          <span className="order-book-name">{item.name}</span>
                          <span className="order-book-quantity">{item.quantity}</span>
                      </div>
                  ))}
                </div>
                <div className="summary-panel">
                  <div className="order-details">

                    {!orderJson.isPaid ? 
                      <div className="payment">
                        <div className="prize-tag">
                          <span className="cost-name">Total Cost</span>
                          <span className="currency">{currency}</span>
                          <span className="total-cost">{convertTo(orderJson.totalCost, currencyVal)}</span>
                        </div>
                        {charges.map( (charge, index)=>{
                          totalCharges += charge.value;
                          return(<div key={index} className="prize-tag">
                            <span className="cost-name">{charge.chargeName}</span>
                            <span className="currency">{currency}</span>
                            <span className="charges-cost">{convertTo(charge.value, currencyVal)}</span>
                          </div>)
                        })}
                        <div className="prize-tag">
                          <span className="currency">{currency}</span>
                          <span className="final-cost">{convertTo(orderJson.totalCost + totalCharges, currencyVal)}</span>
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

                  </div>
                </div>
              </div>
            ) : (
              <div className="inner-container">
                <p className="fetch-fail-reason">
                  {orderJson.reason}
                </p>
              </div>
            )}
        </div>
        
    </>
  )
}

export default OrderDispatch
