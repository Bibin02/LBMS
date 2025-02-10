import React, { useEffect, useState } from 'react'
import Navigate from './navigate'
import fetchJSON from '../services/dataFetcher';
import OrderItem from './order_item';

const Orders = () => {

    const [ordersJson, setOrdersJson] = useState({data: []});

    async function getData() {
      setOrdersJson(await fetchJSON("/orders.json"));
    }

    useEffect( ()=>{
      getData();
    }, [])


  return (
    <>
        <Navigate/>
        <div className="outer-container body-container">
            <div className="order-list-container">
                {ordersJson.data.map((order, id)=>(
                    <OrderItem
                        key = {id}
                        orderId = {order.orderId}
                        orderStatus = {order.orderStatus}
                        items = {order.items}
                    />
                ))}
            </div>
        </div>
    </>
  )
}

export default Orders
