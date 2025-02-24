import React, { useState } from 'react'
import { Link } from 'react-router-dom';

const InputField = () => {

  const [user, setUser] = useState({
    uid: "",
    pass: ""
  });

  function changeHandler(e) {
    const name = e.target.name;
    const value = e.target.value;

    setUser({...user, [name]: value});
  }

  function loginAction(){
    alert("login ...")
  }

  return (
    <>
        <section className="inner-container container">
            <div className="input-container">
                <input className='input-field-login' type="text" name="username" id="uid" onChange={changeHandler}/>
                <input className='input-field-login' type="password" name="password" id="pwd" onChange={changeHandler}/>
            </div>

            <button id='lgn-btn' className='login-button' type="button" onClick={loginAction}>Login</button>

            <div className="signup-query">Create an account ?</div>

            <Link to={'/signup'}><button id='sgnup-btn' className='login-button' type="button">Register</button></Link>
        </section>
    </>
  )
}

export default InputField
