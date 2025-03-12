import React from 'react'
import PropTypes from 'prop-types'
import { changeDataKey, changeDataValue, useAddHandler, useDeleteHandler } from '../hooks/useChangeHandler'

const BookDescriptionTable = ({tableData, doEdit, setTableData}) => {
    
  return (
    <>
        <table className="book-description table">
            <thead className="table-header">
                <tr className="table-header-row">
                    <th className="table-header-col">Book Property</th>
                    <th className="table-header-col">Description</th>
                </tr>
            </thead>
            {(doEdit === undefined) && 
            <tbody className="table-body">
                {Object.entries(tableData)?.map(([property, value], index)=>{
                    return (<tr className="table-data-row" key={index}>
                        <td className="table-data-col book-property">{property}</td>
                        <td className="table-data-col book-property-value">{value}</td>
                    </tr>)
                })}
            </tbody>}

            {doEdit && <tbody className="table-body">
                {Object.entries(tableData.bookDescription)?.map(([property, value], index)=>{
                    return (
                    <tr className="table-data-row" key={index}>
                        <td className="table-data-col book-property">
                            <input type="text" name={property} value={property}
                                onChange={(e)=>changeDataKey(tableData, "bookDescription", e, setTableData)}/>
                        </td>
                        <td className="table-data-col book-property-value">
                            <input type="text" name={property} value={value}
                                onChange={(e)=>changeDataValue(tableData, "bookDescription", e, setTableData)}/>
                        </td>
                        <td className="table-data-col book-property-delete">
                            <button type="text" name={property} value={value}
                                className='buttons' 
                                onClick={(e)=>{
                                    useDeleteHandler(tableData, "bookDescription", property, setTableData); 
                                    e.stopPropagation();
                                }}
                            >-</button>
                        </td>
                    </tr>
                    )
                })}
                <tr className="add-desc-button">
                    <td className="add-button" colSpan={3}>
                        <button type="button" className='buttons'
                         onClick={()=>useAddHandler(tableData, "bookDescription", setTableData)}>+
                        </button>
                    </td>
                </tr>
            </tbody>}
        </table>
    </>
  )
}

BookDescriptionTable.propTypes = {
    tableData: PropTypes.object
}

export default BookDescriptionTable
