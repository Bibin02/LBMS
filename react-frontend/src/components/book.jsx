import React, { useContext, useEffect, useState } from 'react'
import { Link, useNavigate, useParams } from 'react-router-dom'
import BookDescriptionTable from './book_description_table';
import fetchJSON, { fetchJSONQuery } from '../services/dataFetcher'
import Reviews from './reviews';
import StarRating from './star_rating';
import NavigationMenu from './navigation_menu';
import BookThumbnailMini from './book_thumbnail_mini';
import PrizeTag from './prize_tag';
import GetBookReview from './get_book_review';
import { getLocalCurrency } from '../utils/paymentUtils';
import { calculateLendDuration } from '../utils/utility';
import { AppContext } from './app_context';
import { addToCart, placeSingleOrder } from '../services/cart';

import addIcon from '../assets/images/add_icon.svg'; 
import subIcon from '../assets/images/sub_icon.svg'; 

const Book = () => {
    const { bookid } = useParams();
    const navigate = useNavigate();
    const { currency, currencyVal } = getLocalCurrency();
    const [ bookJson, setBookJson ] = useState({});
    const [ reviewsJson, setReviewsJson ] = useState({data: []});
    const [ cartBasketCount, setBasketCount ] = useState(1);
    const { setCartJson } = useContext(AppContext);
    const [bookThumbnailJson, setBookThumbnailJson] = useState({ data: []});
    
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

    function bookBuyHandler() {
      const orderId = placeSingleOrder(bookJson, cartBasketCount);
      orderId !== null && navigate(`/order?oid=${orderId}`);
    }

    useEffect( ()=>{
      const getData = async () => {
        setBookJson(await fetchJSONQuery("/book.json", {bookUid: bookid}));
        setReviewsJson(await fetchJSON("/reviews.json"));
        setBookThumbnailJson(await fetchJSONQuery("/recommend_book.json", {keywords: bookJson.keywords}))
      }
      getData();
    }, [])

  return (
    <>
        <NavigationMenu/>
        <main className="outer-container container">
          {/* <h1 id='book-title' className="book-dispatch-title">{bookJson.bookName}</h1> */}
          <div className="book-preview-container">
            <div className="left-panel container">
              <figure className="image-preview">
                <img src={bookJson.imageSource ? bookJson.imageSource : "/images/Book.jpg" } alt="preview_Book.jpg" />
                <figcaption>{bookJson.bookName}</figcaption>
              </figure>
            </div>

            <aside className="context-preview right-panel">
              <div className="book-name">
                {bookJson.bookName}
              </div>
              <div className="author-name">
                {bookJson.authorName}
              </div>
              
              <div className="key-words-container">
                <ul className="key-words-list">
                  {bookJson.keywords?.map((keyword, index)=>(
                    <li className="key-word" key={index}>
                      {keyword}
                    </li>
                  ))}
                </ul>
              </div>
              
              <div className="book-prize-container">
                <PrizeTag
                  discount={bookJson.discount}
                  cost={bookJson.cost}
                  currency={currency}
                  currencyVal={currencyVal}
                />
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
                <div className="stars-container">
                  <StarRating rating={bookJson.rating}/>
                </div>
              </div>

              <div className="publication-name-span">
                Publication: {bookJson.publicationName}
              </div>

              <div className="seller-container">
                <Link to={`/users/${bookJson.sellerName}`}>
                  <div className="seller-name-span">Seller: {bookJson.sellerName}</div>
                </Link>
              </div>
              
              <div className="cart-context-container">
                <button className="buttons add-to-cart" onClick={()=>addToCart(setCartJson, bookJson, cartBasketCount)}>
                  {/* <img src={CartIcon} alt="|_|" />  */}
                  Add to Cart 
                </button>
                <div className="number-icons">
                  <div className="buttons count-change-icon" onClick={addCartCount}>
                    <img src={addIcon} alt="+" />
                  </div>
                  <div className="cart-count">
                    <span className="cart-count-span">{cartBasketCount}</span>
                  </div>
                  <div className="buttons count-change-icon" onClick={subCartCount}>
                    <img src={subIcon} alt="-" />
                  </div>
                </div>
              </div>
              <div className="proceed-buttons">
                <Link to={"/cart"}><button className="buttons">View Cart</button></Link>
                <button className="buttons buy-direct" onClick={bookBuyHandler}>
                  { bookJson.bookSellStatus ? "Buy Now" : "Lend Now" }
                </button>
              </div>

              {!bookJson.bookSellStatus ? 
                <div className="lend-properties">
                  <div className="lend-properties-span">
                    Duration <em>{calculateLendDuration(bookJson.bookLendDuration)}</em>
                  </div>
                </div>
                : null
              }
            </aside>

            <section className="book-description-container">
              <BookDescriptionTable
                tableData={bookJson.bookDescription ? bookJson.bookDescription : {}}
              />
            </section>

            <section className="get-review-container">
              <GetBookReview
                bookUid={bookJson.bookUid}
              />
            </section>

            <section className="reviews-container">
              <h3 className="book-dispatch-title">Reviews</h3>
              <div className="reviews-list">
                {reviewsJson.data.map((review, index)=>(
                  <Reviews
                    key = {index}
                    review = {review}
                  />
                ))}
              </div>
            </section>

            <section className="books-recommended container">
              <h3 className="book-dispatch-title">You may also like</h3>
                <BookThumbnailMini
                  bookThumbnailJson={bookThumbnailJson}
                />
            </section>
          </div>
        </main>
    </>
  )
}

export default Book
