import React from 'react'
import BookThumbnailDetails from './book_thumbnail_details'
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom'

function openPreview() {
  alert("Preview")
}

const BookThumbnail = (props) => {
  return (
    <>
        <div className="thumbnail-outer-box">
            <Link className='linkage' to={`/books/${props.bkdata.bookUid}`}>
              <div className="book-image-container">
                  <img src={props.imageSource} alt="Book.jpg" />
              </div>
            </Link>
            {<BookThumbnailDetails
                bookUid={props.bkdata.bookUid}
                bookname={props.bkdata.bookname}
                cost={props.bkdata.cost}
                discount={props.bkdata.discount}
                rating={props.bkdata.rating}
            />}
            <button className="buttons preview-button" onClick={openPreview} disabled={props.bkdata.doPreview}>Preview</button>
            <div className="buttons view-button"><Link to={`/books/${props.bkdata.bookname}`}>View Book</Link></div>
        </div>
    </>
  )
}

BookThumbnailDetails.propTypes = {
    imageSource: PropTypes.string,
    bkdata: PropTypes.object,
}

export default BookThumbnail
