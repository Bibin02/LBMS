import React from 'react'
import PropTypes from 'prop-types'
import { useSearchParams } from 'react-router-dom'
import SellerEditBook from './seller_edit_book';

const SellerViewBook = props => {
  const [searchParams] = useSearchParams();

  if (searchParams.get("isEdit") === 'true') {
    return (
    <SellerEditBook 
      bookUid = {searchParams.get("bookUid")} 
    />
    )
  }
  else{
    return (
      <>
          <div className="inner-container container">
              
          </div>
      </>
    )
  }
}

SellerViewBook.propTypes = {

}

export default SellerViewBook
