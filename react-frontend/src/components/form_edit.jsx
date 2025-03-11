import React, { useState } from 'react'
import PropTypes from 'prop-types'
import useChangeHandler from '../hooks/useChangeHandler'
import { defaultSubmitHandler } from '../utils/submitHandlers';
import NotificationPanel from './notification_panel';
import { getDisplayName } from '../utils/utility';

const FormEdit = props => {

    const [previewMessage, setPreviewMessage] = useState(null);

    function changeDataValue(jsonData, datakey, event, setter) {
        let addons = jsonData[datakey];
        addons[event.target.name] = event.target.value;

        setter({...jsonData, [datakey] : addons});
    }

    function useAddHandler(jsonData, datakey, setter) {
        let addons = jsonData[datakey];
        addons["property"] = "value";

        setter({...jsonData, [datakey] : addons});
    }

    function changeDataKey(jsonData, datakey, event, setter) {
        let addons = jsonData[datakey];
        let oldVal = addons[event.target.id];
        delete addons[event.target.id];
        addons[event.target.value] = oldVal;

        setter({...jsonData, [datakey] : addons});
    }

    function useDeleteHandler(jsonData, datakey, datakeyinner, setter) {
        let addons = jsonData[datakey];
        delete addons[datakeyinner];
        setter({...jsonData, [datakey] : addons});
    }

  return (
    <>
        <NotificationPanel
            previewMessage= {previewMessage}
            setPreviewMessage={setPreviewMessage}
        />

        <form onSubmit={(e)=>defaultSubmitHandler(props.jsonData, props.formAction, e, setPreviewMessage)} 
        className='change-details-form container'>
            {Object.entries(props.jsonData)?.map(([datakey, datavalue], index)=>{

                if (typeof(datavalue) === 'object' && !Array.isArray(datavalue)) {
                    return(
                    <table className="label-container" key={index}>
                        <tbody className="table-body">
                            {Object.entries(datavalue)?.map(([datakeyinner, datavalueinner], innerindex)=>{
                                return (
                                <tr htmlFor={datakeyinner} className="label-item" key={innerindex}>
                                    <td className="label-item-property">
                                        <input type="text" id={datakeyinner} value={datakeyinner} 
                                            onChange={(e)=>{changeDataKey(props.jsonData, datakey, e, props.setJsonData)}}
                                        />
                                    </td>
                                    <td className="lable-item-value">
                                        <input type="text" name={datakeyinner} value={datavalueinner} 
                                            onChange={(e)=>{changeDataValue(props.jsonData, datakey, e, props.setJsonData)}}
                                        />
                                    </td>
                                    <td className="lable-item-remove">
                                        <button type="button" id={datakeyinner} className='buttons' 
                                            onClick={()=>{useDeleteHandler(props.jsonData, datakey, datakeyinner, props.setJsonData)}}
                                        >-</button>
                                    </td>
                                </tr>)
                            })}
                            <tr className="label-item">
                                <td className='label-item-add' colSpan={2}>
                                    <button type="button"className='buttons' 
                                        onClick={()=>{useAddHandler(props.jsonData, datakey, props.setJsonData)}}
                                    >+</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    )
                }
                else {
                    return (
                    <label htmlFor={datakey} className="label-item" key={index}>
                        {getDisplayName(datakey)}
                        {datakey === "imageSource" && 
                            <input type="file" name={datakey} id={datakey}
                                onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                            />
                        }
                        {datakey === "bookSellStatus" && 
                            <div className="radio-fields">
                                <label htmlFor="sale">Sale Book</label>
                                <input type="radio" name={datakey} value="true"
                                    onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                />
                                <label htmlFor="lend">Lend Book</label>
                                <input type="radio" name={datakey} value="false"
                                    onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                                />
                            </div>
                        }

                        {datakey !== "imageSource" && datakey !== "bookSellStatus" &&
                            <input type="text" name={datakey} id={datakey} value={datavalue} 
                                onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                            />
                        }
                    </label>
                    )
                }
            })}
            <button className="submit-form buttons" type='submit'>Save Changes</button>
        </form>
    </>
  )
}

FormEdit.propTypes = {
    jsonData: PropTypes.object.isRequired,
    setJsonData: PropTypes.func
}

export default FormEdit
