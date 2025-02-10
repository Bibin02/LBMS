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