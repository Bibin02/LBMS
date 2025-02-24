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
            return "star-complete.svg"
        case "/":
            return "star-half.svg"
        default:
            return "star-hollow.svg"
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