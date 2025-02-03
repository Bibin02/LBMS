import { Link } from "react-router-dom";

export function Home() {
    return (
        <>
            <li>
                <Link to={"/login"}>Login</Link>
            </li>
        </>
    )
}