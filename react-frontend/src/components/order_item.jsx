import React from 'react'
import { Link } from 'react-router-dom'

const OrderItem = (props) => {
  return (
    <>
        <Link to={`/order?oid=${props.orderId}`}>
          <div className="order-label-container">
            <span className="order-id">{props.orderId}</span>
            <span className="order-status">{props.orderStatus}</span>
          </div>
        </Link>
        {props.items.map((item, index)=>(
            <div className="order-book" key={index}>
                <span className="order-book-thumbnail"><img src={item.imgsrc} alt="src.jpg" /></span>
                <span className="order-book-name">{item.name}</span>
                <span className="order-book-quantity">{item.quantity}</span>
            </div>
        ))}
    </>
  )
}

export default OrderItem
