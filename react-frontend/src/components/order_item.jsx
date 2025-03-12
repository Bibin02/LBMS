import React from 'react'
import { Link } from 'react-router-dom'

const OrderItem = (props) => {
  return (
    <>
        <Link to={`/order?oid=${props.orderId}`} className='container'>
          <section className="order-label container">
            <div className="order-id">Order Id <em>{props.orderId}</em></div>
            <div className="order-status"><strong>{props.orderStatus}</strong></div>
          </section>
        </Link>
        {props.items.map((item, index)=>(
            <div className="order-book" key={index}>
                <figure className="order-book-thumbnail">
                  <img src={item.imgsrc ? item.imgsrc : "/images/Book.jpg" } alt="src.jpg" />
                </figure>
                <aside className="order-desc container">
                  <div className="order-book-name">{item.name}</div>
                  <div className="order-book-quantity">{item.quantity}</div>
                </aside>
                
            </div>
        ))}
    </>
  )
}

export default OrderItem
