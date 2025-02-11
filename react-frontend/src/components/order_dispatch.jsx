import React from 'react'
import { useSearchParams } from 'react-router-dom'

const OrderDispatch = () => {

    const [searchParams] = useSearchParams();
    
  return (
    <>
        <h1>Order ID : {searchParams.get("oid")}</h1>
    </>
  )
}

export default OrderDispatch
