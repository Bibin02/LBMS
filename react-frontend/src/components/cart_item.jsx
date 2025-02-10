import React from 'react'

const CartItem = (props) => {
  return (
    <>
        <div className="item-thumbnail">
            <span className="book-title">{props.bookName}</span>
            <div className="item-image"><img src={props.previewImage} alt="book.jpg" /></div>
        </div>
        <span className="book-cost">{props.cost}</span>
        <span className="book-quantity">{props.quantity}</span>
        {props.isLend ? (<div className="lend-properties">
            <span className="lend-duration">{props.lendDuration}</span>
        </div>) : null}
    </>
  )
}

export default CartItem
