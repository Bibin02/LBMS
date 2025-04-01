export function getOrderStatusImageSource(orderStatusCode) {
    const path = "/images/"
    switch (orderStatusCode) {
        case 100: // Payment Pending
            return path+"pay_pending.jpg";
        case 200:
            return path+"dispatch.jpg";
        case 300:
            return path+"delivered.jpg";
        default:
            return path+"error_status.jpg";
    }
}