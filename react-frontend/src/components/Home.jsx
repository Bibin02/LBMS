import ContentMenu from "./content_menu";
import NavigationMenu from "./navigation_menu";

export function Home() {

    return (
        <>
            {<NavigationMenu/>}

            {<ContentMenu/>}
        </>
    )
}