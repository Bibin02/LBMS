export function convertCurrency(cost, localCurrency) {
    return (cost * localCurrency).toFixed(2);
}

export function calculateDiscount(originalCost, discountPercentage) {
    return (originalCost - (originalCost * discountPercentage / 100)).toFixed(2);
}

export function calculateLendDuration(milliseconds) {
    let currDate = new Date();
    let lendExpiryDate = new Date(Date.now() + Number(milliseconds));
    return ("From " + (currDate).toDateString() + " To " + (lendExpiryDate).toDateString());
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
        isSeller: "Does the user is a Seller ?",
        bookName: "Book name",
        imageSource: "Upload Image",
        authorName: "Author Name",
        bookSellStatus: "Book is under Sale or Lend ?",
        bookLendDuration: "Book Lend Duration",
        publicationName: "Book Published by",
        keywords: "Keywords",
        cost: "Book Cost",
        discount: "Discount Percentage",
        stock: "Book in Stock",
    };

    return dictionary[name] ? dictionary[name] : name;
}