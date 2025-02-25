import React from 'react'
import PropTypes from 'prop-types'

const BookDescriptionTable = (props) => {
    
  return (
    <>
        <table className="book-description table">
            <thead className="table-header">
                <tr className="table-header-row">
                    <th className="table-header-col">Book Property</th>
                    <th className="table-header-col">Description</th>
                </tr>
            </thead>
            <tbody className="table-body">
                {Object.entries(props.tableData).map((property, index)=>{
                    return (<tr className="table-data-row" key={index}>
                        <td className="table-data-col book-property">{property[0]}</td>
                        <td className="table-data-col book-property-value">{property[1]}</td>
                    </tr>)
                })}
            </tbody>
        </table>
    </>
  )
}

BookDescriptionTable.propTypes = {
    tableData: PropTypes.object
}

export default BookDescriptionTable
