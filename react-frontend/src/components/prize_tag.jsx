import '../styles/prize_tag.css'

import React from 'react'
import PropTypes from 'prop-types'
import { calculateDiscount, convertCurrency } from '../utils/utility'

const PrizeTag = ({cost, discount, currency, currencyVal}) => {
  return (
    <>
    {discount ?
        <div className="discount-percent-box prize-tag">
        <em className="discount-prize">
            <span className="currency">{currency}
            {convertCurrency(calculateDiscount(cost, discount), currencyVal)}
            </span>
            <sub className='strike-container'><del>
            <span className="strike">{convertCurrency(cost, currencyVal)}</span>
            </del></sub>
        </em>
        <div className="discount-percent-container">
            <div className="discount-percent">- {discount}%</div>
        </div>
        </div>
        : ( // Else part
        <div className="prize-tag">
        <em className="prize">
            <span className="currency">{currency}</span>
            {convertCurrency(cost, currencyVal)}
        </em>
        </div>
        )
    }
    </>
  )
}

PrizeTag.propTypes = {
    discount: PropTypes.number, 
    currency: PropTypes.string.isRequired, 
    currencyVal: PropTypes.number.isRequired
}

export default PrizeTag
