import React from 'react'
import PropTypes from 'prop-types'
import { getLocalCurrency } from '../utils/paymentUtils';
import StarRating from './star_rating';
import PrizeTag from './prize_tag';

const BookThumbnailDetails = ({rating, cost, discount}) => {

  const { currency, currencyVal } = getLocalCurrency();
  return (
    <>
        <div className="thumbnail-details-container">
            <div className="cost-panel">
              <PrizeTag 
                cost={cost}
                discount={discount}
                currency={currency}
                currencyVal={currencyVal}
              />
            </div>

            <div className="rating">
              <i className="stars-icon"><StarRating rating={rating} /></i>
              {/* <span className="rating-val">{props.rating}</span> */}
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