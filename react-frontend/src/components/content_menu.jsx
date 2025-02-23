import React, { useEffect, useState } from 'react'
import BookThumbnail from './book_thumbnail'
import fetchJSON from '../services/dataFetcher';

const ContentMenu = () => {
    const [json, setJson] = useState({data: []});
    const [needFeed, setNeedFeed] = useState(true);

    async function getData() {
        if (needFeed) {
            setJson(await fetchJSON("/bookthumbnail.json"));
            // console.log(json);
            setNeedFeed(false);
        }  
    }

    useEffect(function (){
        getData();
    }, [])

  return (
    <>
        <div className="outer-container">
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
