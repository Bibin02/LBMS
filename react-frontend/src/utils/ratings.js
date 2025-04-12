import StarComplete from '../assets/images/star-complete.svg'
import StarHalf from '../assets/images/star-half.svg'
import StarHollow from '../assets/images/star-hollow.svg'

export function getRatingStars(rating) {

    let isHalf = ((rating * 10) % 10) == 0 ? false : true;
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

    return stars;
}
    

export function starTagGetter(star) {
    switch (star) {
        case "*":
            return StarComplete;
        case "/":
            return StarHalf;
        default:
            return StarHollow;
    }
}

export function getRatingLabel(rating){
    rating = Number(rating);

    if (rating >= 4) {
        return "Best";
    } 
    if (rating > 2 && rating < 4) {
        return "Moderate"
    } else {
        return "Critical"
    }
}