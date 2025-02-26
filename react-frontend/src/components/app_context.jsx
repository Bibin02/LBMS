import React, { createContext, useState } from "react";
import { getUserId } from "../services/userService";

export const AppContext = createContext();


export const AppContextProvider = ({ children }) => {
    
  const loginUserId = getUserId();
  const [isUserLogin, setIsUserLogin] = useState(false);

  return (
    <AppContext.Provider 
        value={{ isUserLogin, loginUserId, setIsUserLogin }}
      >
      {children}
    </AppContext.Provider>
  );
};