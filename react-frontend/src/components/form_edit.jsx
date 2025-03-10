import React from 'react'
import PropTypes from 'prop-types'
import useChangeHandler from '../hooks/useChangeHandler'

const FormEdit = props => {

  return (
    <>
        <form action="" method="post" className='change-details-form container'>
            {Object.entries(props.jsonData)?.map(([datakey, datavalue], index)=>{
                if (typeof(datavalue) === 'object' && !Array.isArray(datavalue)) {
                    return(<div className="label-container" key={index}>
                        {Object.entries(datavalue)?.map(([datakeyinner, datavalueinner], innerindex)=>{
                            return (
                            <label htmlFor={datakeyinner} className="label-item" key={innerindex}>
                                {datakeyinner}
                                <input type="text" name={datakeyinner} id={datakeyinner} value={datavalueinner} 
                                    onChange={(e)=>{useChangeHandler(props.jsonData[datakey], e, props.setJsonData)}}
                                />
                            </label>)
                        })}
                    </div>)
                }
                else {
                    return (
                    <label htmlFor={datakey} className="label-item" key={index}>
                        {datakey}
                        <input type="text" name={datakey} id={datakey} value={datavalue} 
                            onChange={(e)=>{useChangeHandler(props.jsonData, e, props.setJsonData)}}
                        />
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
