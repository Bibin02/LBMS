import React from 'react'
import { useParams } from 'react-router-dom';
import NavigationMenu from './navigation_menu';

const UserDashboard = () => {
    const {userid} = useParams();
  return (
    <>
      <main className="outer-container container">
        <NavigationMenu/>
        
        <h1 className="text-indigo-600 m-12">User {userid}</h1>
      
      </main>
    </>
  )
}

export default UserDashboard
