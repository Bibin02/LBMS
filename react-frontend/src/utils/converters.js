export function getLocalCurrency() {

    // INR
    return {currency: "₹",
            currencyVal: 85};
    
}

export function convertTo(cost, localCurrency) {
    return (cost * localCurrency).toFixed(2);
}

export function calculateLendDuration(milliseconds) {
    let currDate = new Date();
    let lendExpiryDate = new Date(Date.now() + Number(milliseconds));
    return ("From " + (currDate).toDateString() + " To " + (lendExpiryDate).toDateString());
}