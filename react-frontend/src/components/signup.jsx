import React, { useEffect, useState } from 'react'
import { loadMeta } from '../config/pageMetaLoader'

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

    function changeHandler(e) {
        const name = e.target.name;
        const value = e.target.name == "checked" ? e.target.checked : e.target.value;
    
        setUser({...user, [name]: value});
    }

    function clearAction() {
        setUser({});
        document.querySelectorAll("#root input").forEach(input => {
            input.value = ""
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
        console.log(user);
        
        if (validatePassword()){
            alert("Account Created Successfully")
        }
        
    }
  return (
    <>
        <div className="container outer-box">
            <div id='input-tag-root' className="input-container">
                <input className='input-field' placeholder='Email' type="text" name="email" id="uid" onChange={changeHandler}/>
                <input className='input-field' placeholder='Password' type="password" name="password" id="pass" onChange={changeHandler}/>
                <input className='input-field' placeholder='Confirm Password' type="text" name="confirmPassword" id="cnf-pass" onChange={changeHandler}/>
            </div>

            <button id='lgn-btn' className='buttons login-button' type="button" onClick={clearAction}>Clear</button>

            <button id='sgnup-btn' className='buttons login-button' type="button" onClick={createAccount}>Create Account</button>
        </div>
    </>
  )
}

export default Signup
