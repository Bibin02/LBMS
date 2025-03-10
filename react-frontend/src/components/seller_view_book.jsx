import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import SellerEditBook from './seller_edit_book';
import fetchJSON from '../services/dataFetcher';
import { getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency } from '../utils/utility';

const SellerViewBook = props => {
  const [sellerBooksJson, setSellerBooksJson] = useState({ data: []});
  const [doEditBook, setDoEditBook] = useState(false);
  const [editBookId, setEditBookId] = useState(null);
  const {currency, currencyVal} = getLocalCurrency();

  useEffect( ()=>{
    const getData = async () => {
      let jsonData = await fetchJSON("/sellerbooks.json");
      setSellerBooksJson(jsonData);
    }
    getData();
  }, [])

  if (doEditBook) {
    return (
    <SellerEditBook 
      bookUid = {editBookId} 
      setEditBookId = {setEditBookId}
      setDoEditBook = {setDoEditBook}
    />
    )
  }
  else{
    return (
      <>
          <div className="inner-container container">
              <div className="seller-books-container container">
                {sellerBooksJson?.data.map( (book, index)=>(
                  <div className="seller-book" key={index}>
                    <div className="book-name">{book.bookName}</div>
                    <div className="book-stock">Quantity in Stock {book.stock}</div>
                    <div className="book-prize">Prize <em>{currency}{convertCurrency(book.prize, currencyVal)}</em></div>
                    <button type="button" className='buttons'
                      onClick={()=>{
                        setEditBookId(book.bookUid);
                        setDoEditBook(true);
                      }}
                      >
                      Edit Book</button>
                    <button type="button" className='buttons'
                      onClick={()=>{
                        // Remove Seller Book function/API Call.
                      }}
                      >
                      Remove Book</button>
                  </div>
                ))}
              </div>
          </div>
      </>
    )
  }
}

SellerViewBook.propTypes = {
  userData: PropTypes.object.isRequired
}

export default SellerViewBook
