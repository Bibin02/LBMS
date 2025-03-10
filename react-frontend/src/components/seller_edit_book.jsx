import React from 'react'
import PropTypes from 'prop-types'

const SellerEditBook = props => {
  return (
    <>
        <div className="inner-container container">
            <h1>bookUid: {props.bookUid}</h1>
        </div>
    </>
  )
}

SellerEditBook.propTypes = {
  bookUid: PropTypes.string.isRequired
}

export default SellerEditBook
