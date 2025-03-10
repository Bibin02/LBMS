import React, { useState } from 'react'
import { useSearchParams } from 'react-router-dom';
import { getStatusMessage } from '../exceptions/exceptionHandlers';

const SellerAddBookFile = () => {
    const [searchParams] = useSearchParams();
    const statusCode = searchParams.get("statusCode");
    const [doPreviewMessage, setDoPreviewMessage] = useState(statusCode ? true : false);
  return (
    <>
        <form action="" className='file-upload-form'>
            <label htmlFor="xl-file">
                Upload the Excel file
                <input id='xl-file' type="file" />
            </label> 
        </form>
        {doPreviewMessage ? 
        <div className="preview-panel container">
            <div className="preview-message">
                <span className="message-span">
                    {getStatusMessage(Number(statusCode))}
                </span>
            </div>
            <div className="close-message" onClick={()=>setDoPreviewMessage(false)}>
                <div className="close-icon button">X</div>
            </div>
        </div>
        : null }
    </>
  )
}

export default SellerAddBookFile
