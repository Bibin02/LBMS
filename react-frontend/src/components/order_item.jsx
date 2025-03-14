import React from 'react'
import { Link } from 'react-router-dom'

const OrderItem = (props) => {
  return (
    <>
      <div className="order-item">
        <section className="order-label container">
          <div className="order-id">Order Id <strong>{props.orderId}</strong></div>
          <div className="order-status"><strong>{props.orderStatus}</strong></div>
        </section>
        
        <div className="order-item-books">
          {props.items.map((book, index)=>(
            <div className="order-book" key={index}>
                <figure className="order-book-thumbnail">
                  <img src={book.imgsrc ? book.imgsrc : "/images/Book.jpg" } alt="src.jpg" />
                </figure>
                <aside className="order-desc container">
                  <div className="order-book-name">Book name {book.name}</div>
                  <div className="order-book-quantity">Quantity <mark>{book.quantity}</mark></div>
                  {book.isLend && <div className="order-book-lend" style={{color: 'orangered'}}> Took lease </div>}
                </aside>
            </div>
            ))
          }
        </div>

        <Link to={`/order?oid=${props.orderId}`} className='view-order-button'>
          <button type="button" className='buttons'>View Order</button>
        </Link>

      </div>
    </>
  )
}

export default OrderItem
