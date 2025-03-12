import React from 'react'
import PropTypes from 'prop-types'
import { getLocalCurrency } from '../utils/paymentUtils';
import StarRating from './star_rating';
import { calculateDiscount, convertCurrency } from '../utils/utility';

const BookThumbnailDetails = (props) => {

  const { currency, currencyVal } = getLocalCurrency();
  return (
    <>
        <div className="thumbnail-details-container">
            <div className="cost-panel">
              {props.discount ?
              <div className="discount-percent-box prize-tag">
                <em className="discount-prize">
                  <span className="currency">{currency}
                    {convertCurrency(calculateDiscount(props.cost, props.discount), currencyVal)}
                  </span>
                  <sub className='strike-container'><del>
                    <span className="strike">{convertCurrency(props.cost, currencyVal)}</span>
                  </del></sub>
                </em>
                <div className="discount-percent-container">
                  <div className="discount-percent">- {props.discount}%</div>
                </div>
              </div>
              : ( // Else part
              <div className="prize-tag">
                <em className="prize">
                  <span className="currency">{currency}</span>
                  {convertCurrency(props.cost, currencyVal)}
                </em>
              </div>
              )
              }
            </div>

            <div className="rating">
              <i className="stars-icon"><StarRating rating={props.rating} /></i>
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