import '../styles/orders.css'

import React, { useContext, useEffect, useState } from 'react'
import fetchJSON from '../services/dataFetcher';
import OrderItem from './order_item';
import NavigationMenu from './navigation_menu';
import { AppContext } from './app_context';
import { Link } from 'react-router-dom';

const Orders = () => {

    const [ordersJson, setOrdersJson] = useState({data: []});
    const { isUserLogin } = useContext(AppContext);

    useEffect( ()=>{
      const getData = async () => {
        setOrdersJson(await fetchJSON("/orders.json"));
      }
      isUserLogin ? getData() : null;
    }, [])


  return (
    <>
      <NavigationMenu/>
      <main className="outer-container container">
        <div className="inner-container container">
            {isUserLogin ? 
            <div className="orders-list-container">
                {ordersJson.data.map((order, id)=>(
                    <OrderItem
                        key = {id}
                        orderId = {order.orderId}
                        orderStatus = {order.orderStatus}
                        items = {order.items}
                    />
                ))}
            </div>
            : // Else display Login option
            <div className="login-request-panel container">
              <div className="message-division">
                <span className="message">Login to see your orders</span>
              </div>
              <div className="login-button">
                <Link to={"/login"} className='buttons login-button'>
                  Login
                </Link>
              </div>
            </div>
            }
        </div>
      </main>
    </>
  )
}

export default Orders
