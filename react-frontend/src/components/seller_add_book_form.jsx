import React from 'react'

const SellerAddBookForm = () => {
  return (
    <>
        <form action="" method="get" className='add-book-form form-container container'>
            <label htmlFor="bookname">
            Book name
            <input type="text" id='bookname' />
            </label>

            <label htmlFor="authors">
            Authors
            <input type="text" id='authors' />
            </label>

            <label htmlFor="publication">
            Publication
            <input type="text" id='publication' />
            </label>

            <label htmlFor="prize">
            Prize
            <input type="number" id='prize' />
            </label>

            <label htmlFor="discount">
            Discount Percentage
            <input type="number" id='discount' />
            </label>

            <label htmlFor="stock">
            Available Stock
            <input type="number" id='stock' />
            </label>

            <label htmlFor="description">
            Description
            <input type="text" id='description' />
            </label>

            <label htmlFor="keywords">
            Related Keywords / Captions (Use comma seperated values)
            <input type="text" id='keywords' />
            </label>

            <label htmlFor="additional">
            Additional Details
            {/* use key vlue pair logic */}
            <input type="text" id='additional' hidden/>
            </label>

        </form>
    </>
  )
}

export default SellerAddBookForm
