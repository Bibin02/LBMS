import React, { useEffect, useState } from 'react'
import { getPageMeta, loadMeta } from '../config/pageMetaLoader'
import BookBuyerSignup from './book_buyer_signup'
import BookSellerSignup from './book_seller_signup'
import { useNavigate } from 'react-router-dom'

const Signup = () => {

    const [user, setUser] = useState({})
    const [signupHeading, setSignupHeading] = useState("Book Buyer");

    useEffect( ()=>{
        loadMeta(getPageMeta("/signup"));
    }, [])

    function changeSignupHandler(event) {

        // Apply initial translation
        event.target.style.transition = "transform 0.2s ease";
        event.target.style.transform = "translateY(-20px)";

        // Return to original position after a short delay
        setTimeout(() => {
            event.target.style.transform = "translateY(0)";
        }, 200); // 300ms should match the transition duration

        clearAction() // Fro fresh form fill and user set null.

        if (signupHeading === "Book Buyer") {
            setSignupHeading("Book Seller");
            event.target.style["background-color"] = "lightyellow";
        }
        else{
            setSignupHeading("Book Buyer");
            event.target.style["background-color"] = "lightblue";
        }
    }

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

    function createAccount(event) {
        event.preventDefault();
        if (!validateContentEntry()) {
            return;
        }
        
        if (validatePassword()){
            alert("Account Created Successfully")
        }
    }

    const navigate = useNavigate();
    function loginLink() {
        navigate("/login");
    }


  return (
    <>
        <main className="container outer-box">
            <div className="signup-form-container">
                <form id='input-tag-root' className="signup-form"> {/* method='put' action='/api/v1/users/create' */}
                    
                    {/* Rectangular switch */}
                    <div className="switch" onClick={changeSignupHandler}>
                        {signupHeading}
                    </div>

                    {signupHeading === 'Book Buyer' ?
                        <BookBuyerSignup
                            user={user}
                            setUser={setUser}
                        />
                        :
                        <BookSellerSignup
                            user={user}
                            setUser={setUser}
                        />
                    }

                    <button className='buttons login-button' type="reset" onClick={clearAction}>Clear</button>
                    <button className='buttons login-button' onClick={createAccount}>Create Account</button>
                    <div className="signup-query">Created account already ?</div>
                    
                    <button className="buttons login-button" type="button" onClick={loginLink}>Login</button>
                </form>
            </div>
        </main>
    </>
  )
}

export default Signup
