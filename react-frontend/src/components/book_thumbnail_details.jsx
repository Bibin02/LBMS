import React from 'react'
import PropTypes from 'prop-types'
import { getLocalCurrency, convertTo } from '../utils/converters';
import StarRating from './star_rating';
import { Link } from 'react-router-dom';

const BookThumbnailDetails = (props) => {

  const { currencyVal } = getLocalCurrency();
  return (
    <>
        <div className="thumbnail-details-container">
            <Link className='linkage' to={`/books/${props.bookUid}`}>
              <span className="bookname">{props.bookname}</span>
            </Link>
            <div className="currency">{convertTo(props.cost, currencyVal)}</div>
            <em className="cost">{props.cost}</em>
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