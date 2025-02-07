import { useState } from "react";
import ContentMenu from "./content_menu";
import Navigate from "./navigate";
import PreviewPanel from "./preview_panel";

export function Home() {

    const [isPreview, setIsPreview] = useState(false);

    return (
        <>
            {<Navigate/>}

            {<ContentMenu/>}

            {isPreview == true ? <PreviewPanel/> : null}
        </>
    )
}