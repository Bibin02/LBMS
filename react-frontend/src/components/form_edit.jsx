import '../styles/form_edit.css'

import React, { useState } from 'react'
import PropTypes from 'prop-types'
import useChangeHandler, { changeDataKey, changeDataValue, useAddHandler, useDeleteHandler } from '../hooks/useChangeHandler'
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';
import { getDisplayName } from '../utils/utility';

const FormEdit = props => {

    const [previewMessage, setPreviewMessage] = useState(null);

  return (
    <>
        <NotificationPanel
            previewMessage= {previewMessage}
            setPreviewMessage={setPreviewMessage}
        />

        <form onSubmit={(e)=>defaultSubmitHandler(props.jsonData, props.formAction, e, setPreviewMessage)} 
        className='change-details-form container'>
            <table className='change-details-form-table'>
                <tbody className='change-details-form-tbody'>
                    {Object.entries(props.jsonData)?.map(([datakey, datavalue], index)=>{
                        
                        if (typeof(datavalue) === 'object' && !Array.isArray(datavalue)) {
                            return(
                                <tr key={index}> 
                                    <td colSpan={2}>
                                        <table className="change-details-form-inner-table">
                                            <tbody className="change-details-form-inner-tbody">
                                                {Object.entries(datavalue)?.map(([datakeyinner, datavalueinner], innerindex)=>{
                                                    return (
                                                    <tr htmlFor={datakeyinner} className="label-item" key={innerindex}>
                                                        <td className="label-item-property">
                                                            <input type="text" name={datakeyinner} value={datakeyinner} 
                                                                onChange={(e)=>{changeDataKey(props.jsonData, datakey, e, props.setJsonData)}}
                                                            />
                                                        </td>
                                                        <td className="lable-item-value">
                                                            <input type="text" name={datakeyinner} value={datavalueinner} 
                                                                onChange={(e)=>{changeDataValue(props.jsonData, datakey, e, props.setJsonData)}}
                                                            />
                                                        </td>
                                                        <td className="lable-item-remove">
                                                            <button type="button" name={datakeyinner} className='buttons' 
                                                                onClick={()=>{useDeleteHandler(props.jsonData, datakey, datakeyinner, props.setJsonData)}}
                                                            >-</button>
                                                        </td>
                                                    </tr>)
                                                })}
                                                <tr className="label-item">
                                                    <td className='label-item-add' colSpan={3}>
                                                        <button type="button"className='buttons' 
                                                            onClick={()=>{useAddHandler(props.jsonData, datakey, props.setJsonData)}}
                                                        >+</button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            )
                        }
                        else {
                            return (
                            <tr htmlFor={datakey} className="label-item" key={index}>
                                <td>{getDisplayName(datakey)}</td>
                                {datakey === "imageSource" && 
                                    <td><input type="file" name={datakey} id={datakey}
                                        onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                    />
                                    </td>
                                }
                                {datakey === "bookSellStatus" && 
                                    <td colSpan={2} className="radio-fields">
                                        <label htmlFor="sale">Sale Book</label>
                                        <input type="radio" name={datakey} value="true"
                                            onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                        />
                                        <label htmlFor="lend">Lend Book</label>
                                        <input type="radio" name={datakey} value="false"
                                            onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                        />
                                    </td>
                                }

                                {datakey !== "imageSource" && datakey !== "bookSellStatus" &&
                                    <td><input type="text" name={datakey} id={datakey} value={datavalue} 
                                        onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                    />
                                    </td>

                                }
                            </tr>
                            )
                        }
                    })}
                    
                    <tr>
                        <td colSpan={2}><button className="submit-form buttons" type='submit'>Save Changes</button>
                        </td>
                    </tr>

                </tbody>
            </table>
        </form>
    </>
  )
}

FormEdit.propTypes = {
    jsonData: PropTypes.object.isRequired,
    setJsonData: PropTypes.func
}

export default FormEdit
