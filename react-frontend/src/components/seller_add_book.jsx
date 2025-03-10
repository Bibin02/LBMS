import React, { useState } from 'react'
import PropTypes from 'prop-types'
import SellerAddBookForm from './seller_add_book_form';
import SellerAddBookFile from './seller_add_book_file';
import { useNavigate } from 'react-router-dom';

const SellerAddBook = props => {
  const [isUseForm, setIsUseForm] = useState(true);
  const navigate = useNavigate();
  return (
    <>
        <div className="inner-container container">
          <div className="container input-choice-buttons">
            <button className='buttons input-choice' type="button" onClick={()=>setIsUseForm(true)}>Add via form</button>
            <button className='buttons input-choice' type="button" 
              onClick={
                ()=>{setIsUseForm(false); 
                    navigate(`/users/${props.propsObject.userData.userId}`)}
                }>
                Add via Excel file</button>
          </div>
            {isUseForm ? <SellerAddBookForm/> : <SellerAddBookFile/>}
        </div>
    </>
  )
}

SellerAddBook.propTypes = {

}

export default SellerAddBook
