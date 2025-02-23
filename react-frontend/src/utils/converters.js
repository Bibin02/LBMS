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

export function getExtraCharges() {
    return {
        charges: [
            {chargeName: "Tax", value: 2},
            {chargeName: "Convenience Fee", value: 0.1}
        ]
    }
}

export function calculateDiscount(originalCost, discountPercentage) {
    return (originalCost - (originalCost * discountPercentage / 100)).toFixed(2);
}