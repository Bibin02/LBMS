import '../styles/seller_dashboard.css';

import React, { useEffect, useState } from 'react'
import PropTypes from 'prop-types'
import { fetchJSONQuery } from '../services/dataFetcher';
import renderTableRows from '../services/renderTableRows';

import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer, PieChart, Pie, Cell } from "recharts";

const COLORS = ["#0088FE", "#FFBB28", "#00C49F"];

const SellerDashboard = ({sellerId}) => {

    // const [salesData, setSalesData] = useState([]);
    // const [stockData, setStockData] = useState([]);
    
    const [sellerData, setSellerData] = useState({});

    useEffect(()=>{
        const getData = async ()=>{
            setSellerData(await fetchJSONQuery("/seller.json", {sellerId: sellerId}))
            // setSalesData(sellerData.salesData);
            // setStockData(sellerData.salesData);
        }
        getData();
    },[])

  return (
    <>
        <div className="seller-dashboard-container container">
            <div className="dashboard-container">
                <h2>Seller Dashboard</h2>
                    {/* Bar Chart for Last 5 Days Sales */}
                    <div className="chart-container">
                    <h3>Last 5 Days Sales</h3>
                    <ResponsiveContainer width="100%" height={300}>
                        <BarChart data={sellerData.salesData}>
                        <XAxis dataKey="day" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Bar dataKey="sales" fill="#8884d8" fillOpacity={0.8} barSize={50} />
                        </BarChart>
                    </ResponsiveContainer>
                    </div>

                    {/* Pie Chart for Stock Distribution */}
                    <div className="chart-container">
                    <h3>Stock Status</h3>
                    <ResponsiveContainer width="100%" height={300}>
                        {sellerData.stockData != null && <PieChart>
                        <Pie data={sellerData.stockData} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={100} label>
                        {sellerData.stockData.map((entry, index) => (
                            <Cell key={entry.name} fill={COLORS[index % COLORS.length]} />
                        ))}
                        </Pie>
                        </PieChart>}
                    </ResponsiveContainer>
                    </div>
            </div>
            <table className='seller-details-table'>
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
