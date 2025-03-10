import React from 'react'
import PropTypes from 'prop-types'

const SellerEditBook = props => {
  return (
    <>
        <div className="inner-container container">
          <div className="back-button"
              onClick={()=>{
                props.setEditBookId(null);
                props.setDoEditBook(false)
              }}
            >
            <div className="back-icon">{"<-"}</div>
          </div>
          <h1>bookUid: {props.bookUid}</h1>
        </div>
    </>
  )
}

SellerEditBook.propTypes = {
  bookUid: PropTypes.number.isRequired
}

export default SellerEditBook
