import React from 'react'
import PropTypes from 'prop-types'
import { getRatingLabel } from '../utils/ratings'

const Reviews = props => {
  return (
    <>
        <li className="review-label">
            <span className="review-label">{getRatingLabel(props.review.rating)}</span>
            <span className="review-rating">{props.review.rating}</span>
            <details className="review-context">
              <summary className='review-summary'>Comments</summary>
              <p className='review-context-para'>{props.review.context}</p>
            </details>
        </li>
    </>
  )
}

Reviews.propTypes = {
    rating: PropTypes.number,
    context: PropTypes.string
}

export default Reviews
