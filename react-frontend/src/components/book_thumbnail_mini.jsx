import React from 'react'
import PropTypes from 'prop-types'
import StarRating from './star_rating'

const BookThumbnailMini = ({ bookThumbnailJson }) => {
  return (
    <>
        <div className="scroll-container">
            {bookThumbnailJson?.data.map((bookThumbnail, index)=>{
                return(
                  <div className="thumbnail-container" key={index}>
                    <div className="book-image-container">
                        <figure className="image-box">
                            <img src={bookThumbnail.imgSrc} alt="Book" />
                            <figcaption className='book-title'>
                                {bookThumbnail.bookName}
                            </figcaption>
                        </figure>
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
