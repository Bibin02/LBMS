export function addToCart(cartJson, bookJson, quantity) {
    // Cart add logic
    if (true) { // If cart added, cartJson state Updates
        
        let foundIndex = -1;
        let notFoundMatch = cartJson.data.every((cartBook, index) => {
            if (cartBook.bookUid == bookJson.bookUid){
                foundIndex = index
                return false;
            }
            return true;
        });

        const cartJsonData = {
            bookUid: bookJson.bookUid,
            bookName: bookJson.bookName,
            previewImage: bookJson.imageSource,
            quantity: quantity,
            cost: bookJson.cost,
            isLend: (!bookJson.bookSellStatus),
            lendDuration: bookJson.bookLendDuration
        };

        if (notFoundMatch) {
            cartJson.data = [...cartJson.data, cartJsonData];
        }
        else{
            cartJson.data[foundIndex] = cartJsonData;
        }

        alert("Book added to Cart Successfully");
    }
    else{

    }
}


export function deleteFromCart(cartJson, bookUid) {
    // Cart remove logic
    let foundIndex = -1;
    cartJson.data.every((cartBook, index) => {
        if (cartBook.bookUid == bookUid){
            foundIndex = index
            return false;
        }
        return true;
    });

    if (true) { // If removed, cartJson state Updates
        delete cartJson.data[foundIndex];
        alert("Book removed from Cart Successfully")
    }
    else{

    }
}