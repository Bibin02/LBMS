import React from 'react'
import { Link } from 'react-router-dom'
import { convertCurrency, convertMilliSecToDateTime } from '../utils/utility';
import { getLocalCurrency } from '../utils/paymentUtils';

const {currency, currencyVal} = getLocalCurrency();

const OrderItem = (props) => {
  return (
    <>
      <div className="order-item">
        <section className="order-label container">
          <div className="order-id">Order Id <strong>{props.orderId}</strong></div>
          <div className="order-status"><strong>{props.orderStatus}</strong></div>
        </section>
        
        <div className="order-details">
            <div className="order-details-title">Total Cost </div>
            <div className="order-details-value">{currency}{convertCurrency(props.price, currencyVal)}</div>
        </div>

        <div className="order-details">
            <div className="order-details-title">Total Books </div>
            <div className="order-details-value">{props.totalBooks}</div>
        </div>

        <div className="order-details">
          <div className="order-details-title">Lend Books </div>
          <div className="order-details-value">{props.lendBooksCount}</div>
        </div>

        <div className="order-details">
          <div className="order-details-title">Order Time </div>
          <div className="order-details-value">{convertMilliSecToDateTime(props.datetime)}</div>
        </div>

        <Link to={`/order?oid=${props.orderId}`} className='view-order-button'>
          <button type="button" className='buttons'>View Order</button>
        </Link>

      </div>
    </>
  )
}

export default OrderItem
