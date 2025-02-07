import React from 'react'
import BookThumbnailDetails from './book_thumbnail_details'

const BookThumbnail = (props) => {
  return (
    <>
        <div className="thumbnail-outer-box">
            <div className="book-image-container">
                <img src={props.img_src} alt="Book.jpg" />
            </div>
            {<BookThumbnailDetails
                bookname={props.bkdata.bookname}
                cost={props.bkdata.cost}
                rating={props.bkdata.rating}
            />}
            <button className="button preview-button">Preview</button>
        </div>
    </>
  )
}

export default BookThumbnail
