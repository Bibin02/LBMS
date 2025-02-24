import React, { useEffect, useState } from 'react'
import BookThumbnail from './book_thumbnail'
import fetchJSON from '../services/dataFetcher';
import { debounce } from '../utils/opitmizer';
import useHandleScroll from '../hooks/useHandleScroll';

const ContentMenu = () => {
    const [json, setJson] = useState({data: []});
    const [needFeed, setNeedFeed] = useState(true);

    let isLoading = false;

    // adding debounce to the eventListner
    // window.addEventListener("scroll", debounce(useHandleScroll(setNeedFeed), 500));

    async function getBookThumbnails() {
        if (needFeed) {
            isLoading = true;
            setJson(await fetchJSON("/bookthumbnail.json"));
            isLoading = false;
            setNeedFeed(false);
        }  
    }

    useEffect(function (){
        if (needFeed && !isLoading) {
            getBookThumbnails();
        }
    }, [needFeed]);

  return (
    <>
        <div className="outer-container container">
            {json.data.map((bkjson, index)=>(
                <BookThumbnail
                    key = {index}
                    imageSource = {bkjson.img_src}
                    bkdata = {bkjson.bkdata}
                />
            ))}
        </div>
    </>
  )
}

export default ContentMenu
