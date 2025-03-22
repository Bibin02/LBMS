import '../styles/seller_add_book.css';

import React, { useState } from 'react'
import useChangeHandler from '../hooks/useChangeHandler';
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';
import BookDescriptionTable from './book_description_table';

const SellerAddBookForm = () => {

  const [bookJson, setBookJson] = useState({bookDescription: {}});
  const [previewMessage, setPreviewMessage] = useState(null);

  return (
    <>
        <NotificationPanel
          previewMessage= {previewMessage}
          setPreviewMessage={setPreviewMessage}
        />
        
        <form onSubmit={(e)=>defaultSubmitHandler(bookJson, "props.formAction", e, setPreviewMessage)} 
          className='add-book-form form-container container'>
            <table>
              <tbody className="add-book-form-tbody container">
                <tr htmlFor="bookname">
                  <td>Book name </td>
                  <td><input type="text" id='bookname' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="authors">
                  <td>Authors </td>
                  <td><input type="text" id='authors' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="publication">
                  <td>Publication </td>
                  <td><input type="text" id='publication' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="prize">
                  <td>Prize </td>
                  <td><input type="number" id='prize' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="discount">
                  <td>Discount Percentage </td>
                  <td><input type="number" id='discount' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="stock">
                  <td>Available Stock </td>
                  <td><input type="number" id='stock' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr htmlFor="description">
                  <td>Description </td>
                  <td><input type="text" id='description' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/></td>
                </tr>

                <tr htmlFor="keywords">
                  <td>Related Keywords / Captions (Use comma seperated values) </td>
                  <td><input type="text" id='keywords' onChange={(e)=>useChangeHandler(bookJson, e, setBookJson)}/> </td>
                </tr>

                <tr className='book-desc-label'>
                  <td colSpan={2}>
                    <BookDescriptionTable 
                      tableData={bookJson}
                      doEdit={true}
                      setTableData={setBookJson}
                    />
                  </td> 
                </tr>

                <tr className="submit-button">
                  <td colSpan={2}>
                    <button type="submit" className="add-book-form-submit-button buttons">
                      Submit
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>

        </form>
    </>
  )
}

export default SellerAddBookForm
