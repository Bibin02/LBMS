import React, { useEffect, useState } from 'react'
import { loadMeta } from '../config/pageMetaLoader'

import '../styles/signup.css'
import useChangeHandler from '../hooks/useChangeHandler'

const Signup = () => {

    const [user, setUser] = useState({})

    const meta = {
        title: 'E - Library Signup',
        description: 'Signup to Enjoy your Book Store',
        canonical: 'http://example.com/path/to/page',
        meta: {
            charset: 'utf-8',
            name: {
                keywords: 'react,meta,document,html,tags,blablabla'
            }
        }
    }

    useEffect( ()=>{
        loadMeta(meta);
    }, [])

    function clearAction() {
        setUser({});
        document.querySelectorAll("#root input").forEach(input => {
            input.style.borderColor = ""; 
        });
    }

    function validateContentEntry(){
        let flag = true;
        document.querySelectorAll("#root input").forEach((input) => {
            if(input.value.trim() === ""){
                input.style.borderColor = "red"; // Set border to red initially
                flag = false
            }
        });
        return flag;
    }

    function validatePassword() {
        if (user.password.length < 6) {
            alert("Password must be 6 characters")
            return false;
        }
        else if (user.password != user.confirmPassword){
            alert("Password and Confirm Password mismatch")
            return false;
        }
        return true;
    }

    function createAccount() {
        if (!validateContentEntry()) {
            return;
        }
        
        if (validatePassword()){
            alert("Account Created Successfully")
        }
        
    }
  return (
    <>
        <main className="container outer-box">
            <form id='input-tag-root' className="input-container"> {/* method='put' action='/api/v1/users/create' */}
                <label htmlFor="uid">
                    Email ID
                    <input className='input-field' type="text" name="email" id="uid" 
                        required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
                </label>
                <label htmlFor="pass">
                    Password
                    <input className='input-field' type="password" name="password" id="pass" 
                        required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
                </label>
                <label htmlFor="cnf-pass">
                    Confirm your Password
                    <input className='input-field' type="text" name="confirmPassword" id="cnf-pass" 
                        required onChange={(e)=>useChangeHandler(user, e, setUser)}/>
                </label>
                <div className="buttons container">
                    <button id='lgn-btn' className='buttons login-button' type="reset" onClick={clearAction}>Clear</button>
                    <div id='sgnup-btn' className='buttons login-button' onClick={createAccount}>Create Account</div>
                </div>
            </form>
        </main>
    </>
  )
}

export default Signup
