import React from 'react'
import { Link } from "react-router-dom";

const Navigate = () => {
  return (
    <>
        <div className="navbar">
            <div className="navbar-links">
                <ul>
                    <li><Link to={"/login"}>Login</Link></li>
                    <li><Link to={"/"}>Home</Link></li>
                </ul>
            </div>
        </div>
    </>
  )
}

export default Navigate
