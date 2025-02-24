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
        <div className="thumbnail-outer-box container">
            <Link className='linkage' to={`/books/${props.bkdata.bookUid}`}>
              <div className="book-image-container container">
                  <img src={props.imageSource} alt="Book.jpg" />
              </div>
            </Link>
            {
              <BookThumbnailDetails
                bookUid={props.bkdata.bookUid}
                bookname={props.bkdata.bookname}
                cost={props.bkdata.cost}
                discount={props.bkdata.discount}
                rating={props.bkdata.rating}
              />
            }
            <div className="buttons-container">
              <Link to={`/books/${props.bkdata.bookname}`}>
                <button className="preview-button buttons">View Book</button>
              </Link>
              {props.bkdata.doPreview ? <button className="preview-button buttons" onClick={openPreview}>Preview</button> : null}
            </div>
        </div>
    </>
  )
}

BookThumbnailDetails.propTypes = {
    imageSource: PropTypes.string,
    bkdata: PropTypes.object,
}

export default BookThumbnail
