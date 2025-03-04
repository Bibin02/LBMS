import React, { useEffect, useState } from 'react'
import fetchJSON from '../services/dataFetcher';
import OrderItem from './order_item';
import NavigationMenu from './navigation_menu';

import '../styles/orders.css'

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
      <main className="outer-container container">
        <NavigationMenu/>
        <div className="inner-container container">
            <div className="order-list container">
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
      </main>
    </>
  )
}

export default Orders
