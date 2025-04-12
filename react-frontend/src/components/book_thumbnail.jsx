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
        <div className="thumbnail-container">
            <Link className='linkage' to={`/books/${props.bkdata.bookUid}`}>
              <figure className="book-image-container container">
                  <img src={props.imageSource ? props.imageSource : "/images/Book.jpg" } alt="Book.jpg" />
              </figure>
              <div className='book-title'>{props.bkdata.bookname}</div>
              <div className="bookthumbnail-author">By, {props.bkdata.author}</div>
            </Link>
            {
              <BookThumbnailDetails
                cost={props.bkdata.cost}
                discount={props.bkdata.discount}
                rating={props.bkdata.rating}
              />
            }
            <div className="buttons-container">
              <Link to={`/books/${props.bkdata.bookUid}`}>
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
