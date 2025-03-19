import NavigationMenu from "./navigation_menu";
import FooterContent from "./footer_content";
import { useContext, useState } from "react";
import { AppContext } from "./app_context";
import { validateUserLogin } from "../services/userService";
import useChangeHandler from "../hooks/useChangeHandler";
import { useNavigate } from "react-router-dom";

export function Login() {

    const [user, setUser] = useState({});

    const { setIsUserLogin, setLoginUserId } = useContext(AppContext);

    function loginAction(){
        if (validateUserLogin(user)) {
            setLoginUserId(user.username)
            setIsUserLogin(true);
        }
    }

    const navigate = useNavigate();
    function signupLink() {
        navigate("/signup");
    }
    
    return (
        <>
            <NavigationMenu/>

            <main className="login-outer-container">
                <div className="login-container">
                    <div className="input-container">
                        <label className="login-label" htmlFor="username">
                            Username
                        </label>
                        <input className='input-field-login' type="text" id="username" onChange={(e) => useChangeHandler(user, e, setUser)}/>
                        <label className="login-label" htmlFor="password">
                            Password 
                        </label>
                        <input className='input-field-login' type="password" id="password" onChange={(e) => useChangeHandler(user, e, setUser)}/>
                    </div>

                    <button id='lgn-btn' className='buttons login-button' type="button" onClick={loginAction}>Login</button>

                    <div className="signup-query">Create an account ?</div>
                    
                    <button className="buttons login-button" type="button" onClick={signupLink}>Register</button>

                </div>
            </main>
            
            <FooterContent/>
        </>
    )
}