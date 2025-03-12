import React, { useEffect, useState } from 'react'
import BookThumbnail from './book_thumbnail'
import { fetchJSONQuery } from '../services/dataFetcher';
import { debounce } from '../utils/opitmizer';
import useHandleScroll from '../hooks/useHandleScroll';

import '../styles/content_menu.css'
import { useSearchParams } from 'react-router-dom';

const ContentMenu = () => {
    const [feed, setFeed] = useState([]);
    const [needFeed, setNeedFeed] = useState(true);
    const [isLoading, setIsLoading] = useState(true);
    const [pagesLoaded, setPagesLoaded] = useState(0);

    const [searchParams] = useSearchParams();
    
    // adding debounce to the eventListner
    window.addEventListener("scroll", debounce(() => useHandleScroll(setNeedFeed), 500));

    async function getBookThumbnails() {
        setIsLoading(true); 
        const newFeed = await fetchJSONQuery("/bookthumbnail.json", {search: searchParams.get("search")});
        setFeed((feed)=> [...feed, newFeed]);  
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
            <div className="feeds-container">
                {feed.map( (json, page)=>{
                    return (
                    <div className="single-feed-container" key={page}>
                        {json.data.map((bkjson, index)=>(
                            <BookThumbnail
                                key = {index}
                                imageSource = {bkjson.img_src}
                                bkdata = {bkjson.bkdata}
                            />
                        ))}
                    </div>
                    )
                })}
            </div>
            {isLoading && <div className="loading-bar">
                <span className="loading-bar"> ... Loading ... </span>
            </div>
            }
        </div>
        
    </>
  )
}

export default ContentMenu
