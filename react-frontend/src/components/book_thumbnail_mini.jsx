import React from 'react'
import PropTypes from 'prop-types'
import StarRating from './star_rating'
import PrizeTag from './prize_tag'
import { getLocalCurrency } from '../utils/paymentUtils'
import { useNavigate } from 'react-router-dom'

const {currency, currencyVal} = getLocalCurrency();

const BookThumbnailMini = ({ bookThumbnailJson }) => {
    const navigate = useNavigate();

    function navigateTop(bookUid) {
        navigate(`/books/${bookUid}`);
        document.body.scrollTop = 0; // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    }
    
  return (
    <>
        <div className="scroll-container">
            {bookThumbnailJson?.data.map((bookThumbnail, index)=>{
                return(
                  <div className="thumbnail-container" key={index}>
                    <div className="book-image-container" onClick={()=>navigateTop(bookThumbnail.bookUid)}>
                        <figure className="image-box">
                            <img src={bookThumbnail.imgSrc} alt="Book" />
                        </figure>
                        <div className='book-title'>
                            {bookThumbnail.bookName}
                        </div>
                        <PrizeTag 
                            cost={bookThumbnail.cost}
                            discount={bookThumbnail.discount}
                            currency={currency}
                            currencyVal={currencyVal}
                        />
                        <div className="rating-container">
                            <StarRating
                                rating={bookThumbnail.rating}
                            />
                        </div>
                    </div>
                  </div>
                )
            })}
        </div>
    </>
  )
}

BookThumbnailMini.propTypes = {
    bookThumbnailJson: PropTypes.object.isRequired
}

export default BookThumbnailMini
