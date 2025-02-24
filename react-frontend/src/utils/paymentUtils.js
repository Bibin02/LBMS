export function getLocalCurrency() {

    // INR
    return {currency: "₹",
            currencyVal: 85};
    
}

export function getExtraCharges() {
    return {
        charges: [
            {chargeName: "Tax", value: 2},
            {chargeName: "Convenience Fee", value: 0.1}
        ]
    }
}

