import React from 'react'
import PropTypes from 'prop-types'
import { getLocalCurrency, convertTo, calculateDiscount } from '../utils/converters';
import StarRating from './star_rating';
import { Link } from 'react-router-dom';

const BookThumbnailDetails = (props) => {

  const { currency, currencyVal } = getLocalCurrency();
  return (
    <>
        <div className="thumbnail-details-container">
            <Link className='linkage' to={`/books/${props.bookUid}`}>
              <span className="bookname">{props.bookname}</span>
            </Link>
            <div className="cost-panel">
              <em className="currency">{currency}</em>
              {props.discount ?
              <div className="discount-percent-box">
                <em className="prize">{convertTo(calculateDiscount(props.cost, props.discount), currencyVal)}</em>
                <span className="dicount-percent">{props.discount}% off</span>
              </div>
              : <em className="prize">{convertTo(props.cost, currencyVal)}</em>
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