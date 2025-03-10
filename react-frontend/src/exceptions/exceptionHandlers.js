export function getStatusMessage(code) {
    switch (code) {
        case 200:
            return "Success"
        case code >= 400 && code < 500:
            return "Bad Request"
        case code >= 500:
            return "Internal Server Error"
    
        default:
            return "Unexpected error"
    }
}