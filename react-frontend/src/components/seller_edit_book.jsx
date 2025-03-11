import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import fetchJSON from '../services/dataFetcher'
import FormEdit from './form_edit';

const SellerEditBook = props => {

  const [ bookJson, setBookJson ] = useState({});

  useEffect (()=>{
    const getData = async () => {
      let jsonData = await fetchJSON("/book.json");
      setBookJson(jsonData);
    }
    getData();
  }, [])
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
          <FormEdit 
            jsonData = {bookJson}
            setJsonData = {setBookJson}
            formAction = {`/books/${props.bookUid}`}
          />
        </div>
    </>
  )
}

SellerEditBook.propTypes = {
  bookUid: PropTypes.number.isRequired
}

export default SellerEditBook
