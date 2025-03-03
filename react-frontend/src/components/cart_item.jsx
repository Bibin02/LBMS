import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { AppContext } from './app_context';

const CartItem = (props) => {

  const { cartJson, setCartJson } = useContext(AppContext);

  function deleteFromCart() {
    // Cart remove logic
    let foundIndex = -1;
    cartJson.data.every((cartBook, index) => {
        if (cartBook.bookUid === props.bookUid){
            foundIndex = index
            return false;
        }
        return true;
    });

    if (true) { // If removed, cartJson state Updates
        setCartJson(prevCartJson => ({
          ...prevCartJson, // Keep other properties of cartJson
          data: prevCartJson.data.filter((_, index) => index !== foundIndex) // Remove item immutably
        }));
        alert("Book removed from Cart Successfully")
    }
    else{

    }
  }
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
          <button className="buttons remove-from-cart" onClick={deleteFromCart}>
            <i className="delete-book"></i> Remove 
          </button>
        </div>
        
    </>
  )
}

export default CartItem
