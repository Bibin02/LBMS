export function convertCurrency(cost, localCurrency) {
    return Number((cost * localCurrency).toFixed(2));
}

export function calculateDiscount(originalCost, discountPercentage) {
    return Number((originalCost - (originalCost * discountPercentage / 100)).toFixed(2));
}

export function calculateLendDuration(milliseconds) {
    let currDate = new Date();
    let lendExpiryDate = new Date(Date.now() + Number(milliseconds));
    return ("From " + (currDate).toDateString() + " To " + (lendExpiryDate).toDateString());
}

export function convertMilliSecToDateTime(milliseconds) {
    return new Date(milliseconds).toString();
}

export function getLendRemainingDuration(orderDate, lendDuration) {
    let remaining = orderDate + lendDuration - (new Date().getMilliseconds());
    remaining = remaining > 0 ? remaining : 0
    return new Date(remaining).toDateString()  
}

export function getDisplayName(name) {
    let dictionary = 
    {
        UserHome: "Home",
        ChangeUserPassword: "Change Password",
        ChangeUserDetails: "Update Profile",
        SellerAddBook: "Add new Books",
        SellerViewBook: "View your Books",
        userId: "User Unique Id",
        userName: "Name",
        userDescription: "About",
        userAddress: "Delivery Address",
        isSeller: "Does the user is a Seller ?",
        bookName: "Book Name",
        imageSource: "Upload Book Cover / E-book Preview",
        authorName: "Author Name",
        bookSellStatus: "Book is under Sale or Lend ?",
        bookLendDuration: "Book Lend Duration (in months)",
        publicationName: "Book Published by",
        keywords: "Keywords",
        cost: "Book Cost",
        discount: "Discount Percentage",
        stock: "Book in Stock",
        author: "Author",
        rating: "Rating",
        genre: "Genre",
    };

    return dictionary[name] ? dictionary[name] : name;
}