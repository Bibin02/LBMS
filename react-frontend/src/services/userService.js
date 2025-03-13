
export function getUserId() {
    return "User101";
}

export function getUserData(userId) {
    return {
        userId: userId,
        userName: "UserName",
        userDescription: "Lorem ipsum dolo set mi",
        isSeller: true,
        userAddress: "Address 1",
      }
}

export function formatUserDataToEdit(userData) {
    return  {
        userId: userData.userId,
        userName: userData.userName,
        userDescription: userData.userDescription,
        userAddress: userData.userAddress
      }
}

export function validateUserLogin(userObject) {
    // API call
    
    return true;
}

export function getIsUserLogin() {
    return true; // temp set for login bypass.
}