import React, { useEffect, useState } from 'react'
import { Link, useSearchParams } from 'react-router-dom'
import { fetchJSONQuery } from '../services/dataFetcher';
import { getExtraCharges, getLocalCurrency } from '../utils/paymentUtils';
import { calculateLendDuration, convertCurrency } from '../utils/utility';

import NavigationMenu from './navigation_menu';
import OrderProgress from './order_progress';

const OrderDispatch = () => {

    const [searchParams] = useSearchParams();
    const [orderJson, setOrderJson] = useState({});

    const { currency, currencyVal } = getLocalCurrency();

    const { charges } = getExtraCharges();
    let totalCharges = 0;

    useEffect( ()=>{
      const getData = async () => {
        setOrderJson(await fetchJSONQuery("/order.json", {orderId: searchParams.get("oid")}))
      }
      getData();
    }, [])
    
  return (
    <>
        <NavigationMenu/>
        <main className="container outer-container">
          <h1>Order ID : {searchParams.get("oid")}</h1>
            {orderJson.fetchStatus ? (
              <div className="order-dispatch-container">
                <div className="order-book-container">
                  {orderJson.items.map((book, index)=>(
                      <div className="order-book" key={index}>
                          <figure className="order-book-thumbnail">
                            <img src={book.imgsrc ? book.imgsrc : "/images/Book.jpg" } alt="src.jpg" />
                          </figure>
                          <table className="order-book-details">
                            <tbody>
                              <tr className="order-book-name">
                                <td>Book Title</td>
                                <td className='r-align'>{book.name}</td>
                              </tr>
                              <tr className="order-book-quantity">
                                <td> Quantity </td>
                                <td className='r-align'>{book.quantity}</td>
                              </tr>
                              {book.isLend && 
                                <>
                                  <tr className="order-book-lend">
                                    <td> Duration </td>
                                    <td className='r-align' >{calculateLendDuration(book.lendDuration)}</td>
                                  </tr>
                                  <tr className="order-book-lend">
                                    <td colSpan={2} className='r-align'>Took Lease</td>
                                  </tr>
                                </>
                              }
                            </tbody>
                          </table>
                          
                      </div>
                  ))}
                </div>
                <aside className="order-summary-panel container">
                    {!orderJson.isPaid && 
                      <table className="payment-table">
                        <tbody>
                          <tr className="payment-row">
                            <td><strong className="cost-name">Total Cost</strong></td>
                            <td className="currency c-align">{currency} {convertCurrency(orderJson.totalCost, currencyVal)}</td>
                          </tr>
                          
                          {charges.map( (charge, index)=>{
                            totalCharges += charge.value;
                            return(
                            <tr key={index} className="payment-row">
                              <td><em className="cost-name">{charge.chargeName}</em></td>
                              <td className="currency c-align"> <em>{currency} {convertCurrency(charge.value, currencyVal)}</em></td>
                            </tr>)
                          })}

                          <tr className="payment-row">
                            <td><strong className="cost-name">Final Cost</strong></td>
                            <td className="currency c-align"> 
                              <mark><strong>{currency} {convertCurrency(orderJson.totalCost + totalCharges, currencyVal)}</strong></mark>
                            </td>
                          </tr>
                          <tr className="payment-row">
                            <td colSpan={2}>
                              <Link to={'/'} className='pay-button-link'> 
                                <button className="pay-button buttons">
                                  Complete Payment
                                </button>
                              </Link>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    }
                    <OrderProgress
                      orderStatusCode={orderJson.orderStatusCode}
                      orderStatus={orderJson.orderStatus}
                    />
                </aside>
              </div>
            ) : (
              <div className="order-dispatch-container">
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
