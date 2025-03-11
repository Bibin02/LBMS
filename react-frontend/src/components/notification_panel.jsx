import React from 'react'
import PropTypes from 'prop-types'

const NotificationPanel = ({ previewMessage, setPreviewMessage }) => {
  return (
    <>
        {previewMessage != null  && 
            <div className="status-panel container">
                <div className="preview-message"
                style={{backgroundColor: previewMessage[0] === "!" ? "red" : "green"}}>
                    {previewMessage}
                </div>
                <div className="close-message" onClick={()=>setPreviewMessage(null)}>
                    <div className="close-icon button">X</div>
                </div>
            </div>
        }
    </>
  )
}

NotificationPanel.propTypes = {
    setPreviewMessage: PropTypes.func.isRequired
}

export default NotificationPanel
