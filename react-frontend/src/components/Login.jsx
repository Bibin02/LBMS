import InputField from "./input_field";
import "../styles/login.css"
import NavigationMenu from "./navigation_menu";
import FooterContent from "./footer_content";

export function Login() {
    return (
        <>
            <main className="outer-container container">
                {<NavigationMenu/>}
                {<InputField/>}
                {<FooterContent/>}
            </main>
        </>
    )
}