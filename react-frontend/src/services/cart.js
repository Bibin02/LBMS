import { calculateDiscount } from "../utils/utility";

export function addToCart(setCartJson, bookJson, cartBasketCount) {
    // Cart add logic
    if (true) { // If cart added, cartJson state Updates
        
        const cartBookData = {
            bookUid: bookJson.bookUid,
            bookName: bookJson.bookName,
            previewImage: bookJson.imageSource,
            quantity: cartBasketCount,
            cost: calculateDiscount((cartBasketCount * bookJson.cost), bookJson.discount ? bookJson.discount : 0),
            isLend: (!bookJson.bookSellStatus),
            lendDuration: bookJson.bookLendDuration
        };

        let notFoundMatch = true;

        setCartJson(prevCartJson => ({
            ...prevCartJson, // Keep other properties of cartJson
            data: prevCartJson.data.map((book) =>{
                    if(book.bookUid === bookJson.bookUid){ // Replace only the matched index
                        notFoundMatch = false;
                        return cartBookData;
                    }
                    else{
                        return book; 
                    }
                }
            )
          }));
        if (notFoundMatch) {
            setCartJson(prevCartJson => ({
                ...prevCartJson,
                data: [...prevCartJson.data, cartBookData]
            }));
        }

        alert("Book added to Cart Successfully");
    }
    else{
      alert("Error Occured")
    }
}

export function deleteFromCart(setCartJson, bookUid) {

    if (true) { // If removed, cartJson state Updates
        setCartJson(prevCartJson => ({
          ...prevCartJson, // Keep other properties of cartJson
          data: prevCartJson.data.filter((cartBook) => cartBook.bookUid !== bookUid) // Remove item immutably
        }));
        alert("Book removed from Cart Successfully")
    }
    else{

    }
  }

export function placeOrder(cartJson, setCartJson) {
    if (true) { // Order placed Successfully,
        setCartJson({ data: [] });
        alert("Order Placed Successfully.")
    }
}

export function placeSingleOrder(bookJson, cartBookCount) {
    let orderId = null;
    orderId = 101;
    if (orderId !== null) { // Order placed Successfully,
        return orderId;
    }
}