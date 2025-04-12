import React from 'react'
import PropTypes from 'prop-types'
import { getOrderStatusImageSource } from '../services/order'

const OrderProgress = ({orderStatus, orderStatusCode}) => {
  return (
    <>
        <div className="order-progress-container">
            <h3 className='order-progress-heading'>
              <div className='order-status'>{orderStatus}</div>
              <div> Status code : {orderStatusCode}</div>
            </h3>
            {/* A proress bar with neat display is done here */}
            <div className="track-order-container">
              <figure className='track-order-figure'>
                <img src={getOrderStatusImageSource(orderStatusCode)} alt={getOrderStatusImageSource(orderStatusCode)} />
                <figcaption>
                  <div className='order-status'>{orderStatus}</div>
                </figcaption>
              </figure>

            </div>

        </div>
    </>
  )
}

OrderProgress.propTypes = {
    orderStatus: PropTypes.string,
    orderStatusCode: PropTypes.number.isRequired
}

export default OrderProgress
