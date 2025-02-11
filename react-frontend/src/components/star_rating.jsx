import React from 'react'

const StarRating = (props) => {

    let rating = Number(props.rating)
    let isHalf = ((props.rating * 10) % 10) == 0 ? false : true;
    let stars = []

    for(let i=1; i<=5; i++){
        if (i <= rating) {
            stars.push("*");
        }
        else if (isHalf && (i-rating) < 1.0) {
            stars.push("/");
        }
        else{
            stars.push(" ")
        }
    }

    function starTagGetter(star) {
        switch (star) {
            case "*":
                return "star-complete.svg"
            case "/":
                return "star-half.svg"
            default:
                return "star-hollow.svg"
        }
    }
    
    
  return (
    <>  
        <div className="stars-outer-box">
            {stars.map( (star, index)=>{
                return(
                    <div className="star-box" key={index} style={{display: 'inline'}}>
                        <img src={`/${starTagGetter(star)}`} alt="star" height={"20px"} width={"20px"}/> 
                    </div>
                )
            })}
        </div>
    </>
  )
}

export default StarRating
