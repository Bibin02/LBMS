
export function getUserId() {
    return "User101";
}

export function getUserData(userId) {
    return {
        userId: userId,
        userName: "UserName",
        userDescription: "User Description: Lorem ipsum",
        userDetails: {a: 1, b:2, c:3}
      }
}