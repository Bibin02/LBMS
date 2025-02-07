import React from 'react'
import { useParams } from 'react-router-dom'

const Book = () => {

    const {bookid} = useParams();
  return (
    <>
        <h1 className="text-indigo-600 m-12">Book {bookid}</h1>
    </>
  )
}

export default Book
