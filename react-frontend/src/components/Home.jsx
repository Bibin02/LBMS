import ContentMenu from "./content_menu";
import FooterContent from "./footer_content";
import NavigationMenu from "./navigation_menu";

export function Home() {

    return (
        <>
            <main className="container">
                <NavigationMenu/>

                <ContentMenu/>

                <FooterContent/>
            </main>
        </>
    )
}