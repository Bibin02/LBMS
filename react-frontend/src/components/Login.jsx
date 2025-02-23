import InputField from "./input_field";
import "../styles/login.css"
import NavigationMenu from "./navigation_menu";

export function Login() {
    return (
        <>
            {<NavigationMenu/>}

            {<InputField/>}
        </>
    )
}