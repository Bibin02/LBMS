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