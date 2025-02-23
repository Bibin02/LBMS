export function loadMeta(metaData) {
     
    document.title = metaData.title;

    document.querySelector('meta[name="description"]')?.setAttribute('content', metaData.description);
    document.querySelector('link[rel="canonical"]')?.setAttribute('href', metaData.canonical);

    // Object.entries(metaData.meta.name).forEach(([key, value]) => {
    //     let metaTag = document.querySelector(`meta[name="${key}"]`);
    //     if (metaTag) {
    //         metaTag.setAttribute('content', value);
    //     } else {
    //         metaTag = document.createElement('meta');
    //         metaTag.setAttribute('name', key);
    //         metaTag.setAttribute('content', value);
    //         document.head.appendChild(metaTag);
    //     }
    // });

    // Object.entries(metaData.meta["http-equiv"]).forEach(([key, value]) => {
    //     let metaTag = document.querySelector(`meta[http-equiv="${key}"]`);
    //     if (metaTag) {
    //         metaTag.setAttribute('content', value);
    //     } else {
    //         metaTag = document.createElement('meta');
    //         metaTag.setAttribute('http-equiv', key);
    //         metaTag.setAttribute('content', value);
    //         document.head.appendChild(metaTag);
    //     }
    // });

    // Object.entries(metaData.meta.property).forEach(([key, value]) => {
    //     let metaTag = document.querySelector(`meta[property="${key}"]`);
    //     if (metaTag) {
    //         metaTag.setAttribute('content', value);
    //     } else {
    //         metaTag = document.createElement('meta');
    //         metaTag.setAttribute('property', key);
    //         metaTag.setAttribute('content', value);
    //         document.head.appendChild(metaTag);
    //     }
    // });
}
