import React, { useEffect, useState } from 'react'
import BookThumbnail from './book_thumbnail'
import fetchJSON from '../services/dataFetcher';
// import '../../public/bookthumbnail.json'

const ContentMenu = () => {
    const [json, setJson] = useState({data: []});
    const [needFeed, setNeedFeed] = useState(true);

    async function getData() {
        setJson(await fetchJSON("../../public/bookthumbnail.json"));
        console.log(json);
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
                    img_src = {bkjson.img_src}
                    bkdata = {bkjson.bkdata}
                />
            ))}
        </div>
    </>
  )
}

export default ContentMenu
