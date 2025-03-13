import React from 'react'
import PropTypes from 'prop-types'

const OrderProgress = ({orderStatus, orderStatusCode}) => {
  return (
    <>
        <div className="order-progress-container">
            {/* A proress bar with neat display is done here */}
            {orderStatus} and its code : {orderStatusCode}
        </div>
    </>
  )
}

OrderProgress.propTypes = {
    orderStatus: PropTypes.string,
    orderStatusCode: PropTypes.number.isRequired
}

export default OrderProgress
