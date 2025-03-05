import React from 'react'
import PropTypes from 'prop-types'

const RecommendedBooks = props => {
  return (
    <>
        <div className="recommended-books-container container">
            <h3 className="title">Recommended Books</h3>
            <div className="recommended-book-thumbnails">
                {props.keywords}
            </div>
        </div>
    </>
  )
}

RecommendedBooks.propTypes = {

}

export default RecommendedBooks
