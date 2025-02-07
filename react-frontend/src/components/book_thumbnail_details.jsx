import React from 'react'
import PropTypes from 'prop-types'

const BookThumbnailDetails = (props) => {
  return (
    <>
        <div className="thumbnail-details-container">
            <div className="bookname">{props.bookname}</div>
            <div className="cost">{props.cost}</div>
            <div className="rating">{props.rating}</div>
        </div>
    </>
  )
}

BookThumbnailDetails.propTypes = {
    bookname: PropTypes.string.isRequired,
    rating: PropTypes.number
}

export default BookThumbnailDetails;