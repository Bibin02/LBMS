import React from 'react'
import PropTypes from 'prop-types'
import { getLocalCurrency } from '../utils/paymentUtils';
import StarRating from './star_rating';
import { Link } from 'react-router-dom';
import { calculateDiscount, convertCurrency } from '../utils/utility';

const BookThumbnailDetails = (props) => {

  const { currency, currencyVal } = getLocalCurrency();
  return (
    <>
        <div className="thumbnail-details-container">
            <Link className='linkage' to={`/books/${props.bookUid}`}>
              <span className="bookname">{props.bookname}</span>
            </Link>
            <div className="cost-panel">
              {props.discount ?
              <div className="discount-percent-box prize-tag">
                <em className="prize">
                  <span className="currency">{currency}</span>
                  {convertCurrency(calculateDiscount(props.cost, props.discount), currencyVal)}
                </em>
                <div className="discount-percent-container">
                  <span className="dicount-percent">{props.discount}% off</span>
                </div>
              </div>
              : ( // Else part
              <div className="prize-tag">
                <span className="currency">{currency}</span>
                <em className="prize">{convertCurrency(props.cost, currencyVal)}</em>
              </div>
              )
              }
            </div>

            <div className="rating">
              <i className="stars-icon"><StarRating rating={props.rating} /></i>
              <span className="rating-val">{props.rating}</span>
            </div>
        </div>
    </>
  )
}

BookThumbnailDetails.propTypes = {
    bookname: PropTypes.string.isRequired,
    rating: PropTypes.number
}

export default BookThumbnailDetails;