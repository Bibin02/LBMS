import React, { createContext, useEffect, useState } from "react";
import { getIsUserLogin, getUserId } from "../services/userService";
import fetchJSON from "../services/dataFetcher";

export const AppContext = createContext();


export const AppContextProvider = ({ children }) => {
    
  const [ loginUserId, setLoginUserId ] = useState("");
  const [ isUserLogin, setIsUserLogin ] = useState(false);
  const [ cartJson, setCartJson ] = useState({data: []})
  const [ cartBookCount, setCartBookCount ] = useState(isUserLogin ? 2 : 0);
  const [ totalCartCost, setTotalCartCost ] = useState(0);

  useEffect( () => {
    const getData = async ()=>{
      setCartJson(await fetchJSON("/cart.json"));
    }
    getData();
    setLoginUserId(getUserId());
    setIsUserLogin(getIsUserLogin());
  }, [])

  useEffect(() => {
    if (cartJson?.data) {
      const bookCount = cartJson.data.length;
      const totalCost = cartJson.data.reduce((sum, item) => sum + item.cost, 0);
  
      setCartBookCount(bookCount);
      setTotalCartCost(totalCost);
    }
    
  }, [cartJson]);

  return (
    <AppContext.Provider 
        value={
          { 
            isUserLogin, setIsUserLogin,
            loginUserId, setLoginUserId,
            cartJson, setCartJson,
            cartBookCount, setCartBookCount,
            totalCartCost, setTotalCartCost
          }
        }
      >
      {children}
    </AppContext.Provider>
  );
};