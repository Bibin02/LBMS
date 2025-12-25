import ContentMenu from "./content_menu";
import FooterContent from "./footer_content";
import NavigationMenu from "./navigation_menu";

import '../styles/App.css';

export function Home() {

    return (
        <main className="container">
            <NavigationMenu />
            <ContentMenu />
            <FooterContent />
        </main>
    )
}