import "../styles/login.css"
import NavigationMenu from "./navigation_menu";
import FooterContent from "./footer_content";
import { useContext, useState } from "react";
import { AppContext } from "./app_context";
import { validateUserLogin } from "../services/userService";
import useChangeHandler from "../hooks/useChangeHandler";
import { Link } from "react-router-dom";

export function Login() {

    const [user, setUser] = useState({});

    const { setIsUserLogin } = useContext(AppContext);

    function loginAction(){
        if (validateUserLogin(user)) {
            setIsUserLogin(true);
        }
    }
    
    return (
        <>
            <main className="outer-container container">
                <NavigationMenu/>

                <div className="inner-container container">
                    <div className="input-container">
                        <label htmlFor="username">
                            User name <input className='input-field-login' type="text" name="username" id="uid" onChange={(e) => useChangeHandler(user, e, setUser)}/>
                        </label>
                        <label htmlFor="password">
                            Password <input className='input-field-login' type="password" name="password" id="pwd" onChange={(e) => useChangeHandler(user, e, setUser)}/>
                        </label>
                    </div>

                    <button id='lgn-btn' className='login-button' type="button" onClick={loginAction}>Login</button>

                    <div className="signup-query">Create an account ?</div>

                    <Link to={'/signup'}><button id='sgnup-btn' className='login-button' type="button">Register</button></Link>
                </div>

                <FooterContent/>
            </main>
        </>
    )
}