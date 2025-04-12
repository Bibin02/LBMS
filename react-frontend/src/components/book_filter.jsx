import '../styles/book_filter.css';

import React from 'react'
import SearchIcon from '../assets/images/search.svg';
import PropTypes from 'prop-types'
import useChangeHandler from '../hooks/useChangeHandler';
import { getDisplayName } from '../utils/utility';

const BookFilter = ({bookFilter, setBookFilter, preventFormAction, formAction}) => {
  return (
    <>
        <form onSubmit={
                    (event)=>{
                        if (preventFormAction) {
                            event.preventDefault();
                            formAction();
                        }
                    }
                } 
          className="search-bar-container">
            <select name="filterBy" id="filterBy" className='filter-select'
                onChange={(e)=>useChangeHandler(bookFilter, e, setBookFilter)}>
                <option value="bookName">Book Name</option>
                <option value="author">Author</option>
                <option value="genre">Genre</option>
                <option value="rating">Rating</option>
            </select>
            <input type="text" name="search" id="searchContent" 
                placeholder={getDisplayName(bookFilter.filterBy)}
                onChange={(e)=>useChangeHandler(bookFilter, e, setBookFilter)}
            />
            <button className="search-icon-box" type='submit'>
                <img src={SearchIcon} alt="?" />
            </button>
        </form>
    </>
  )
}

BookFilter.propTypes = {
    setBookFilter: PropTypes.func.isRequired,
    preventFormAction: PropTypes.bool,
    formAction: PropTypes.func
}

export default BookFilter
