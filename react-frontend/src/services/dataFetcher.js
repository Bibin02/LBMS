export default async function fetchJSON(url){
    let response = await fetch(url);
    let responseJSONPromise = await response.json();
    return responseJSONPromise;
}

export async function fetchJSONQuery(url, QueryParams) {
    let response = await fetch(url);
    let responseJSONPromise = await response.json();
    return responseJSONPromise;
}