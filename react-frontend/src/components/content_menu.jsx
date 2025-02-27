import React, { useEffect, useState } from 'react'
import BookThumbnail from './book_thumbnail'
import fetchJSON from '../services/dataFetcher';
import { debounce } from '../utils/opitmizer';
import useHandleScroll from '../hooks/useHandleScroll';

import '../styles/content_menu.css'

const ContentMenu = () => {
    const [feed, setFeed] = useState([]);
    const [needFeed, setNeedFeed] = useState(true);
    const [isLoading, setIsLoading] = useState(true);
    const [pagesLoaded, setPagesLoaded] = useState(0);
    
    // adding debounce to the eventListner
    window.addEventListener("scroll", debounce(() => useHandleScroll(setNeedFeed), 500));

    async function getBookThumbnails() {
        setIsLoading(true);
        // setJson(...json, await fetchJSON("/bookthumbnail.json"));  
        const newFeed = await fetchJSON("/bookthumbnail.json");
        setFeed((feed)=> [...feed, newFeed]);  
        console.log("Data Loading");       
        setIsLoading(false);
        setNeedFeed(false);
    }

    useEffect(function (){
        if (needFeed && pagesLoaded < 10) {
            getBookThumbnails();
            setPagesLoaded(pagesLoaded+1);
        }
    }, [needFeed]);

  return (
    <>
        <div className="outer-container container">
            {feed.map( (json, page)=>{
                return (
                <div className="inner-container container" key={page}>
                    {json.data.map((bkjson, index)=>(
                        <BookThumbnail
                            key = {index}
                            imageSource = {bkjson.img_src}
                            bkdata = {bkjson.bkdata}
                        />
                    ))}
                    {isLoading ? <div className="loading-bar">Loading ... </div> : null}
                </div>)
            })}
        </div>
        
    </>
  )
}

export default ContentMenu
