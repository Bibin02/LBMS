
export function getUserId() {
    return "User101";
}

export function getUserData(userId) {
    return {
        userId: userId,
        userName: "UserName",
        userDescription: "User Description: Lorem ipsum",
        isSeller: true,
        userDetails: {a: 1, b:2, c:3}
      }
}

export function formatUserDataToEdit(userData) {
    return {
        userId: userData.userId,
        userName: userData.userName,
        userDescription: "User Description: Lorem ipsum",
        userDetails: {a: 1, b:2, c:3}
      }
}

export function validateUserLogin(userObject) {
    // API call
    
    return true;
}

export function getIsUserLogin() {
    return true; // temp set for login bypass.
}