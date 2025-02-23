import React from 'react'
import PropTypes from 'prop-types'
import { getRatingLabel } from '../utils/ratingLabelDefiner'

const Reviews = props => {
  return (
    <>
        <li className="review-label">
            <span className="review-label">{getRatingLabel(props.review.rating)}</span>
            <span className="review-rating">{props.review.rating}</span>
            <span className="review-context">{props.review.context}</span>
        </li>
    </>
  )
}

Reviews.propTypes = {
    rating: PropTypes.number,
    context: PropTypes.string
}

export default Reviews
