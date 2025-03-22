import '../styles/seller_view_edit_book.css'

import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import SellerEditBook from './seller_edit_book';
import NotificationPanel from './notification_panel';
import { fetchJSONQuery } from '../services/dataFetcher';
import { getLocalCurrency } from '../utils/paymentUtils';
import { convertCurrency } from '../utils/utility';
import { useRef } from 'react';
import { removeSellerBook } from '../services/seller';

const SellerViewBook = props => {
  const [sellerBooksJson, setSellerBooksJson] = useState({ data: []});
  const [pageNo, setPageNo] = useState(1);
  const [searchBook, setSearchBook] = useState("");
  const [doEditBook, setDoEditBook] = useState(false);
  const [editBookId, setEditBookId] = useState(null);
  const [previewMessage, setPreviewMessage] = useState(null);
  const {currency, currencyVal} = getLocalCurrency();

  const ref = useRef();

  useEffect( ()=>{
    const getData = async () => {
      setSellerBooksJson(
        await fetchJSONQuery("/sellerbooks.json", 
          {
            searchBook: searchBook, 
            pageNo: pageNo
          }
        )
      );
    }
    getData();
  }, [pageNo, searchBook]);

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
            <div className="filter-books-container">
              <div className="search-seller-book">
                <input type="text" ref={ref}/> 
                <button className='buttons' 
                  onClick={()=>{
                    if(pageNo !== 1) {
                      setPageNo(1)
                     } 
                     setSearchBook(ref.current.value)
                    }}>
                    Find Book
                </button>
              </div>
            </div>
            <NotificationPanel
              previewMessage={previewMessage}
              setPreviewMessage={setPreviewMessage}
            />
            <table className='seller-books-container'>
              <thead>
                <tr>
                  <th>S.no</th>
                  <th>Book name</th>
                  <th>In Stock</th>
                  <th>Prize</th>
                  <th colSpan={2}>Alter Book</th>
                </tr>
              </thead>
              <tbody>
                {sellerBooksJson?.data.map( (book, index)=>(
                  <tr className="seller-book" key={index}>
                    <td className="seller-book-sno">{index+1}</td>
                    <td className="seller-book-name">{book.bookName}</td>
                    <td className="seller-book-stock">{book.stock}</td>
                    <td className="seller-book-prize"><em>{currency}{convertCurrency(book.prize, currencyVal)}</em></td>
                    <td>
                      <button type="button" className='buttons'
                      onClick={()=>{
                        setEditBookId(book.bookUid);
                        setDoEditBook(true);
                      }}
                      >
                      Edit Book</button>
                    </td>
                    <td>
                      <button type="button" className='buttons'
                      onClick={()=>{
                        // Remove Seller Book function/API Call.
                        setPreviewMessage(removeSellerBook(book.bookUid));
                        setPageNo(1);
                      }}
                      >
                      Remove Book</button>
                    </td>
                  </tr>
                ))}
                <tr>
                  <td colSpan={6}>
                    {Array.from(
                      {length: sellerBooksJson?.totalPages}, 
                      (_, index)=>{
                        return (
                        <button className="seller-book-pageno buttons" 
                          key={index}
                          onClick={()=>setPageNo(index+1)}
                          >
                          {index+1}
                        </button>)
                      }
                    )
                    }
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
      </>
    )
  }
}

export default SellerViewBook
