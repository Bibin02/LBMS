import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import fetchJSON from '../services/dataFetcher'
import Reviews from './reviews';
import StarRating from './star_rating';
import NavigationMenu from './navigation_menu';
import { calculateDiscount, convertTo, getLocalCurrency } from '../utils/converters';

const Book = () => {
    const {bookid} = useParams();
    const [bookJson, setBookJson] = useState({});
    const [reviewsJson, setReviewsJson] = useState({data: []});
    const [cartItemCount, setCartItemCount] = useState(1);
    const { currency, currencyVal } = getLocalCurrency();

    async function getData() {
      setBookJson(await fetchJSON("/book.json"));
      setReviewsJson(await fetchJSON("/reviews.json"));
    }

    function addCartCount() {
      if (cartItemCount < 5) {
        setCartItemCount(cartItemCount+1);
      }
    }
    function subCartCount() {
      if (cartItemCount > 1) {
        setCartItemCount(cartItemCount-1);
      }
    }
    function deleteCartItem() {
      // Cart delete logic
      alert("Book deleted from Cart")
    }
    function addToCart() {
      // Cart add logic
      alert("Book added to Cart Successfully")
    }

    useEffect( ()=>{
      getData();
    }, [])
  return (
    <>

        <NavigationMenu/>

        <h1 className="text-indigo-600 m-12">Book {bookid}</h1>

        <div className="preview-panel">
          <div className="image-preview left-panel">
            <img src={bookJson.imageSource} alt="preview_Book.jpg" />
          </div>

          <div className="context-preview right-panel">
            <div className="book-name"><span className="book-name-span">{bookJson.bookName}</span></div>
            <div className="author-name"><span className="author-name-span">{bookJson.authorName}</span></div>
            <div className="seller-container">
              <span className="seller-name-span">{bookJson.sellerName}</span>
              <span className="publication-name-span">{bookJson.publicationName}</span>
            </div>
            <div className="book-prize-container">
              {bookJson.discount ? 
              <div className="prize-discount-container">
                <div className="discount-prize">
                  <span className="prize-symbol"><i className="prize-symbol-icon">{currency}</i></span>
                  <span className="book-prize-span">{convertTo(calculateDiscount(bookJson.cost, bookJson.discount), currencyVal)}</span>
                </div>
                <div className="discount-percent-box">
                  <div className="discount-percent-symbol">
                    <span>{bookJson.discount}%</span>
                  </div>
                </div>
                <div className="og-prize-old">
                  <del className='strike-through'>
                    <span className="prize-symbol"><i className="prize-symbol-icon">{currency}</i></span>
                    <span className="book-prize-span">{convertTo(bookJson.cost, currencyVal)}</span>
                  </del>
                </div>
              </div> 
              : 
              // else part
              <div className="og-prize">
                <span className="prize-symbol"><i className="prize-symbol-icon">{currency}</i></span>
                <span className="book-prize-span">{convertTo(bookJson.cost, currencyVal)}</span>
              </div>
              }
            </div>
            <div className="rating-panel">
              <div className="rating">
                <i className="stars-icon"><StarRating rating={bookJson.rating} /></i>
                <div className="rating-val">{bookJson.rating}</div>
              </div>
            </div>
            <div className="cart-context-container">
              <button className="buttons add-to-cart" onClick={addToCart}><i className="cart-icon"> </i>Add to Cart</button>
              <div className="number-icons">
                <i className="buttons delete-icon" onClick={deleteCartItem}>#</i>
                <i className="buttons add-icon" onClick={addCartCount}>+</i>
                <span className="cart-count">{cartItemCount}</span>
                <i className="buttons subtract-icon" onClick={subCartCount}>-</i>
              </div>
            </div>
            <div className="link-buttons">
              <Link to={"/cart"}><button className="buttons add-to-cart">View Cart</button></Link>
              <Link to={"/orders"}>
                <button className="buttons buy-direct">
                  { bookJson.bookSellStatus ? "Buy Now" : "Lend Now" }
                </button>
              </Link>
            </div>
          </div>

          <div className="book-description-container">
            <ul className="description-points">
              <li className="description-points">{bookJson.bookDescription}</li>
            </ul>
          </div>

          <div className="reviews-container">
            <ul className="reviews-list">
              {reviewsJson.data.map((review, index)=>(
                <Reviews
                  key = {index}
                  review = {review}
                />
              ))}
            </ul>
          </div>
        </div>
    </>
  )
}

export default Book
