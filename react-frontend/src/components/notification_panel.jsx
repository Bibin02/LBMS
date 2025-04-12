import '../styles/notification_panel.css';

import React from 'react'
import PropTypes from 'prop-types'

const NotificationPanel = ({ previewMessage, setPreviewMessage }) => {
  return (
    <>
        {previewMessage != null  && 
            <div className="notification-panel-container">
                <div className="preview-message"
                style={{backgroundColor: previewMessage[0] === "!" ? "red" : "green"}}>
                    <div className="notification-message">
                        {previewMessage}
                    </div>
                    <div className="close-notification" onClick={()=>setPreviewMessage(null)}>
                        X
                    </div>
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
