import React, { useState } from 'react'
import useChangeHandler from '../hooks/useChangeHandler';
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';

const SellerAddBookForm = () => {

  const [bookJson, setBookJson] = useState({});
  const [previewMessage, setPreviewMessage] = useState(null);

  return (
    <>
        <NotificationPanel
          previewMessage= {previewMessage}
          setPreviewMessage={setPreviewMessage}
        />
        
        <form onSubmit={(e)=>defaultSubmitHandler(bookJson, "props.formAction", e, setPreviewMessage)} 
          className='add-book-form form-container container'>
            <label htmlFor="bookname">
            Book name
            <input type="text" id='bookname' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="authors">
            Authors
            <input type="text" id='authors' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="publication">
            Publication
            <input type="text" id='publication' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="prize">
            Prize
            <input type="number" id='prize' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="discount">
            Discount Percentage
            <input type="number" id='discount' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="stock">
            Available Stock
            <input type="number" id='stock' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="description">
            Description
            <input type="text" id='description' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="keywords">
            Related Keywords / Captions (Use comma seperated values)
            <input type="text" id='keywords' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/>
            </label>

            <label htmlFor="additional">
            Additional Details
            {/* use key vlue pair logic */}
            <input type="text" id='additional' hidden/>
            </label>

            <button type="submit" className="buttons form-submit">
              Submit
            </button>

        </form>
    </>
  )
}

export default SellerAddBookForm
