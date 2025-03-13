import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import { Link } from 'react-router-dom';
import { fetchJSONQuery } from '../services/dataFetcher';
import { getLendRemainingDuration } from '../utils/utility';

const LendBooksDisplay = ({userId}) => {

    const [lendBooksJson, setLendBooksJson] = useState({data: []});

    useEffect( ()=>{
        const getData = async () => {
            setLendBooksJson(await fetchJSONQuery("/lend_books.json", {userId: userId}))
        }
        getData();
    }, [])
  return (
    <>
        <div className="lend-books-display-container">
            <h2 className="title">Lend Books</h2>
            {lendBooksJson.data?.map((lendBook, index)=>(
                <div className="lend-book-container" key={index}>
                    <figure className="lend-book-pic">
                        <img src={lendBook.imageSource} alt="Book.jpg" />
                    </figure>
                    <div className="lend-book-title">
                        {lendBook.bookName}
                    </div>
                    <div className="remaining-time">
                        {getLendRemainingDuration(lendBook.orderDate, lendBook.lendDuration)}
                    </div>
                    <div className="buttons-container">
                        <Link to={`/order?oid=${lendBook.orderId}`}> 
                            <button type="button">View Order</button>
                        </Link>
                        <button type="button">Return Book</button>
                    </div>
                </div>
            ))}
        </div>
    </>
  )
}

LendBooksDisplay.propTypes = {
    userId: PropTypes.string.isRequired
}

export default LendBooksDisplay
