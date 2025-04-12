import '../styles/reviews.css';

import React from 'react'
import PropTypes from 'prop-types'
import { getRatingLabel } from '../utils/ratings'

const Reviews = props => {
  return (
    <>
        <li className="review-list-item">

            <div className="review-label">
              # {getRatingLabel(props.review.rating)}
            </div>
            
            <details className="review-context">
              <summary className='review-summary'>Comments</summary>
              <p className='review-context-para'>{props.review.context}</p>
            </details>

            <div className="review-rating">
              {props.review.rating}
            </div>
        </li>
    </>
  )
}

Reviews.propTypes = {
    rating: PropTypes.number,
    context: PropTypes.string
}

export default Reviews
