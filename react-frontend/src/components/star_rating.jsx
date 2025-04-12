import '../styles/star_rating.css';

import React from 'react'
import { getRatingStars, starTagGetter } from '../utils/ratings';

const StarRating = (props) => {
    
    let stars = getRatingStars(Number(props.rating));
    
  return (
    <>  
        <div className="stars-outer-box">
            {stars.map( (star, index)=>{
                return(
                    <div className="star-box" key={index} style={{display: 'inline'}}>
                        <img src={`${starTagGetter(star)}`} alt="star" height={"20px"} width={"20px"}/> 
                    </div>
                )
            })}
        </div>
    </>
  )
}

export default StarRating
