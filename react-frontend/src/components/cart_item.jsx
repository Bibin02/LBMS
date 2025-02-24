import React from 'react'
import { Link } from 'react-router-dom'

function deleteItemFromCart() {
  alert("Book deleted")
}

const CartItem = (props) => {
  return (
    <>
        <Link className='book-thumbnail container' to={`/books/${props.bookUid}`}>
          <div className="book-thumbnail">
              <span className="book-title">{props.bookName}</span>
              <div className="book-image"><img src={props.previewImage} alt="book.jpg" /></div>
          </div>
        </Link>
        <div className="prize-tag">
          <em className="book-cost-currency">{props.currency}</em>
          <em className="book-cost">{props.cost}</em>
        </div>
        <div className="book-quantity">
          Quantity: <span className="book-quantity">{props.quantity}</span>
        </div>
        {props.isLend ? (<div className="lend-properties">
            <span className="lend-duration">{props.lendDuration}</span>
        </div>) : null}
        <div className="remove-from-cart">
          <button className="buttons remove-from-cart" onClick={deleteItemFromCart}>
            <i className="delete-book"></i> Remove 
          </button>
        </div>
        
    </>
  )
}

export default CartItem
