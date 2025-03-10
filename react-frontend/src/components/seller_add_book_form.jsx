import React from 'react'

const SellerAddBookForm = () => {
  return (
    <>
        <form action="" method="get" className='add-book-form form-container container'>
            <label htmlFor="bookname">
            Book name
            <input type="text" id='bookname' />
            </label>
        </form>
    </>
  )
}

export default SellerAddBookForm
