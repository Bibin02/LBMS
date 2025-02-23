import React from 'react'
import { Link } from 'react-router-dom'

function deleteItemFromCart() {
  alert("Book deleted")
}

const CartItem = (props) => {
  return (
    <>
        <Link className='linkage' to={`/books/${props.bookUid}`}>
          <div className="item-thumbnail">
              <span className="book-title">{props.bookName}</span>
              <div className="item-image"><img src={props.previewImage} alt="book.jpg" /></div>
          </div>
        </Link>
        <span className="book-cost-currency">{props.currency}</span>
        <em className="book-cost">{props.cost}</em>
        <span className="book-quantity">{props.quantity}</span>
        {props.isLend ? (<div className="lend-properties">
            <span className="lend-duration">{props.lendDuration}</span>
        </div>) : null}
        <div className="remove-from-cart">
          <button className="buttons remove-from-cart" onClick={deleteItemFromCart}>
            <i className="delete-item"></i> Remove 
          </button>
        </div>
        
    </>
  )
}

export default CartItem
