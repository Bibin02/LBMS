import '../styles/book.css'

import React, { useContext, useEffect, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import BookDescriptionTable from './book_description_table';
import fetchJSON from '../services/dataFetcher'
import Reviews from './reviews';
import StarRating from './star_rating';
import NavigationMenu from './navigation_menu';
import { getLocalCurrency } from '../utils/paymentUtils';
import { calculateDiscount, calculateLendDuration, convertCurrency } from '../utils/utility';
import { AppContext } from './app_context';

const Book = () => {
    const { bookid } = useParams();
    const { currency, currencyVal } = getLocalCurrency();
    const [ bookJson, setBookJson ] = useState({});
    const [ reviewsJson, setReviewsJson ] = useState({data: []});
    const [ cartBasketCount, setBasketCount ] = useState(1);
    const { cartJson } = useContext(AppContext);

    function addCartCount() {
      if (cartBasketCount < 5 && cartBasketCount <= bookJson.stock) {
        setBasketCount(cartBasketCount+1);
      }
    }
    function subCartCount() {
      if (cartBasketCount > 1) {
        setBasketCount(cartBasketCount-1);
      }
    }

    function addToCart() {
      // Cart add logic
      if (true) { // If cart added, cartJson state Updates
          
          let foundIndex = -1;
          let notFoundMatch = cartJson.data.every((cartBook, index) => {
              if (cartBook.bookUid == bookJson.bookUid){
                  foundIndex = index
                  return false;
              }
              return true;
          });
  
          const cartJsonData = {
              bookUid: bookJson.bookUid,
              bookName: bookJson.bookName,
              previewImage: bookJson.imageSource,
              quantity: cartBasketCount,
              cost: calculateDiscount((cartBasketCount * bookJson.cost), bookJson.discount ? bookJson.discount : 0),
              isLend: (!bookJson.bookSellStatus),
              lendDuration: bookJson.bookLendDuration
          };
  
          if (notFoundMatch) {
              cartJson.data = [...cartJson.data, cartJsonData];
          }
          else{
              cartJson.data[foundIndex] = cartJsonData;
          }
  
          alert("Book added to Cart Successfully");
      }
      else{
        alert("Error Occured")
      }
  }

    useEffect( ()=>{
      const getData = async () => {
        setBookJson(await fetchJSON("/book.json"));
        setReviewsJson(await fetchJSON("/reviews.json"));
      }
      getData();
    }, [])
  return (
    <>
        <main className="outer-container container">

          <NavigationMenu/>

          <h1 className="text-indigo-600 m-12">Book {bookid}</h1>

          <div className="inner-container container">
            <div className="left-panel container">
              <figure className="image-preview">
              <img src={bookJson.imageSource} alt="preview_Book.jpg" />
              <figcaption>{bookJson.bookName}</figcaption>
            </figure>
            </div>

            <aside className="context-preview right-panel">
              <div className="book-name"><span className="book-name-span">{bookJson.bookName}</span></div>
              <div className="author-name"><span className="author-name-span">{bookJson.authorName}</span></div>
              <div className="seller-container">
                <Link to={`/users/${bookJson.sellerName}`}>
                  <div className="seller-name-span">{bookJson.sellerName}</div>
                </Link>
                <div className="publication-name-span">{bookJson.publicationName}</div>
              </div>
              {!bookJson.bookSellStatus ? 
                <div className="lend-properties">
                  <div className="lend-properties-span">
                    Lend Duration : <em>{calculateLendDuration(bookJson.bookLendDuration)}</em>
                  </div>
                </div>
                : null
              }
              <div className="book-prize-container">
                {bookJson.discount ? 
                <div className="prize-discount-container">
                  <div className="discount-prize">
                    <span className="prize-symbol-icon">{currency}</span>
                    <span className="book-prize-span">
                      {convertCurrency(calculateDiscount(bookJson.cost, bookJson.discount), currencyVal)}
                    </span>
                  </div>
                  <div className="discount-percent-box">
                    <div className="discount-percent-symbol">
                      <span>{bookJson.discount}%</span>
                    </div>
                  </div>
                  <div className="og-prize-old">
                    <del className='strike-through'>
                      <span className="prize-symbol-icon">{currency}</span>
                      <span className="book-prize-span">
                        {convertCurrency(bookJson.cost, currencyVal)}
                      </span>
                    </del>
                  </div>
                </div> 
                : 
                // else part
                <div className="og-prize">
                  <span className="prize-symbol"><i className="prize-symbol-icon">{currency}</i></span>
                  <span className="book-prize-span">{convertCurrency(bookJson.cost, currencyVal)}</span>
                </div>
                }
              </div>
              {bookJson.stock < 5 ? 
                <div className="limited-stock">
                  <div className="stock-span">
                    <span>Limited Number of Stocks <br /> Avalilable Books: {bookJson.stock}</span>
                  </div>
                </div> 
                : null
              }
              <div className="rating-panel">
                <div className="rating">
                  <div className="stars-icon">
                    <StarRating rating={bookJson.rating}/>
                  </div>
                  <div className="rating-val">
                    {bookJson.rating}
                  </div>
                </div>
              </div>
              <div className="cart-context-container">
                <button className="buttons add-to-cart" onClick={addToCart}>
                  <div className="cart-icon"> <img src="" alt="|_|" /> <span>Add to Cart</span> </div>
                </button>
                <div className="number-icons">
                  <div className="buttons add-icon" onClick={addCartCount}>
                    <img src="" alt="+" />
                  </div>
                  <div className="cart-count">
                    <span className="cart-count-span">{cartBasketCount}</span>
                  </div>
                  <div className="buttons subtract-icon" onClick={subCartCount}>
                    <img src="" alt="-" />
                  </div>
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
            </aside>

            <section className="book-description-container">
              <BookDescriptionTable
                tableData={bookJson.bookDescription ? bookJson.bookDescription : {}}
              />
            </section>

            <section className="reviews-container">
              <ul className="reviews-list">
                {reviewsJson.data.map((review, index)=>(
                  <Reviews
                    key = {index}
                    review = {review}
                  />
                ))}
              </ul>
            </section>
          </div>
        </main>
    </>
  )
}

export default Book
