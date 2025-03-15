import '../styles/star_rating.css';

import React, { useRef, useState } from 'react'
import { getRatingStars, starTagGetter } from '../utils/ratings';
import useExpandTextArea from '../hooks/useExpandTextArea';

const GetBookReview = ({bookUid}) => {

  const [comment, setComment] = useState("");
  const [stars, setStars] = useState(getRatingStars(Number(0)));
  const [openComments, setOpenComments] = useState(false);

  const referenceObject = useRef();

  function submitRating() {
    if(true){
      setComment("");
      alert("Feedback submited");
    }
  }
  return (
    <>
      <h3 className="get-rating-title" style={{textAlign: 'center', padding: "1em"}}>
        Rate our book
      </h3>

      <div className="dynamic-stars-container">
        <div className="stars-outer-box">
          {stars.map( (star, index)=>{
              return(
                  <div className="star-box" key={index} style={{display: 'inline'}}
                    onClick={
                      ()=>{
                          setStars(getRatingStars(index+1));
                          setOpenComments(true);
                        }
                      }>
                      <img src={`${starTagGetter(star)}`} alt="star" height={"20px"} width={"20px"}/> 
                  </div>
              )
          })}
        </div>
      </div>

      {openComments && <>
        <textarea
          ref={referenceObject} 
          placeholder='comments' 
          className='comments-textarea'
          onInput={(e)=>setComment(e.target.value)}
          onKeyDown={(e)=>useExpandTextArea(referenceObject, e)}>

        </textarea>

        <div className="buttons-container">
          <button type="button" className="buttons" onClick={submitRating}>Submit</button>
          <button type="button" className="buttons" 
            onClick={
              ()=>{
                setOpenComments(false);
                setComment("");
                setStars(getRatingStars(0));
              }
            }>
            Cancel
          </button>
        </div>
      </>}
    </>
  )
}

export default GetBookReview
