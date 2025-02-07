import React from 'react'
import { useParams } from 'react-router-dom';

const UserDashboard = () => {
    const {userid} = useParams();
  return (
    <>
        <h1 className="text-indigo-600 m-12">User {userid}</h1>
    </>
  )
}

export default UserDashboard
