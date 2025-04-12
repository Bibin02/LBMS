export function getUserId() {
    return "User101";
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

    let message = "Success", status = false;

    if (userObject.username && userObject.password) {
        // API call
        if (true) { // Authorized Successfully
            status = true
        }
    }
    else{
        if (userObject.username == null) {
            message = "Kindly Enter your User Name";
        }
        else if (userObject.password == null) {
            message = "Kindly Enter your Password";
        }
        else{
            message = "Kindly Enter your User Name and Password";
        }
    }
    
    return {status: status, message: message};
}

export function getIsUserLogin() {
    return true; // temp set for login bypass.
}