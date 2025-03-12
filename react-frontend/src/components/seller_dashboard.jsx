import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import { fetchJSONQuery } from '../services/dataFetcher';
import renderTableRows from '../services/renderTableRows';

const SellerDashboard = ({sellerId}) => {

    const [sellerData, setSellerData] = useState({});

    useEffect(()=>{
        const getData = async ()=>{
            setSellerData(await fetchJSONQuery("/seller.json", {sellerId: sellerId}))
        }
        getData();
    },[])

  return (
    <>
        <div className="seller-dashboard-container container">
            <table>
                <tbody>
                    {renderTableRows(sellerData)}
                </tbody>
            </table>
        </div>
    </>
  )
}

SellerDashboard.propTypes = {
    sellerId: PropTypes.string.isRequired
}

export default SellerDashboard
