import React, { useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import fetchJSON from '../services/dataFetcher'
import Reviews from './reviews';

const Book = () => {
    const {bookid} = useParams();
    const [bookJson, setBookJson] = useState({});
    const [reviewsJson, setReviewsJson] = useState({data: []});
    const [cartItemCount, setCartItemCount] = useState(1);

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
            <div className="book-prize">
              <span className="prize-symbol"><i className="prize-symbol-icon">{bookJson.costSymbol}</i></span>
              <span className="book-prize-span">{bookJson.cost}</span>
              <div className="rating-panel">
                <div className="rating-stars"><i className="stars-img"></i></div>
                <span className="book-rating-span">{bookJson.rating}</span>
              </div>
            </div>
            <div className="cart-context-container">
              <button className="buttons add-to-cart" onClick={addToCart}>Add to Cart</button>
              <div className="number-icons">
                <i className="delete-icon" onClick={deleteCartItem}>#</i>
                <i className="add-icon" onClick={addCartCount}>+</i>
                <span className="cart-count">{cartItemCount}</span>
                <i className="subtract-icon" onClick={subCartCount}>-</i>
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
