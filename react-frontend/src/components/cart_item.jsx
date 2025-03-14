import '../styles/cart_item.css';

import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { AppContext } from './app_context';
import { deleteFromCart } from '../services/cart';

const CartItem = (props) => {

  const { setCartJson } = useContext(AppContext);

  return (
    <>
        <Link className='book-thumbnail container' to={`/books/${props.bookUid}`}>
          <figure className="book-thumbnail">
              <img src={props.previewImage} alt="book.jpg" />
          </figure>
        </Link>
        <div className="book-title">{props.bookName}</div>
        <div className="prize-tag">
          <em className="prize">
            <span className="currency">{props.currency}</span>{props.cost}
          </em>
        </div>
        <div className="book-quantity">
          <span className="book-quantity">Quantity {props.quantity}</span>
        </div>
        {props.isLend ? (<div className="lend-properties">
            <span className="lend-duration">{props.lendDuration}</span>
        </div>) : null}
        <div className="remove-from-cart">
          <button className="buttons remove-from-cart" onClick={()=>deleteFromCart(setCartJson, props.bookUid)}>
            <i className="delete-book"></i> Remove 
          </button>
        </div>
        
    </>
  )
}

export default CartItem
