import React, { useState } from 'react'
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';

const SellerAddBookFile = () => {
    const [previewMessage, setPreviewMessage] = useState(null);
  return (
    <>
        <NotificationPanel
            previewMessage= {previewMessage}
            setPreviewMessage={setPreviewMessage}
        />
        <form onSubmit={(e)=>defaultSubmitHandler("bookJson", "props.formAction", e, setPreviewMessage)} 
          className='file-upload-form'>
            <label htmlFor="xl-file">
                Upload the Excel file
                <input id='xl-file' type="file" />
            </label> 

            <button type="submit" className='buttons form-submit'>
                Parse Data
            </button>
        </form>
    </>
  )
}

export default SellerAddBookFile
