import React, { useState } from 'react'
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';
import { uploadSellerBooks } from '../services/seller';

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
                <a href="/sample_file_upload.csv" download="sample.csv"><button type="button" className='buttons'>Download Sample CSV File</button></a>
            </label>

            <label htmlFor="xl-file">
                Upload the CSV file
                <input type="file" onChange={(e)=>uploadSellerBooks(e, setPreviewMessage)} accept='.csv'/>
            </label> 

            {/* <button type="submit" className='buttons form-submit' onChange={(e)=>uploadSellerBooks(e, setPreviewMessage)}>
                Parse Data
            </button> */}
        </form>
    </>
  )
}

export default SellerAddBookFile
