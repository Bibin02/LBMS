import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Login } from './components/login';
import { Home } from './components/Home';
import Cart from './components/cart';
import Book from './components/book';
import UserDashboard from './components/user_dashboard';
import Orders from './components/orders';
import OrderDispatch from './components/order_dispatch';
import Signup from './components/signup';

import "./styles/App.css";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route index element={<Home/>}/>
          <Route path='/login' element={<Login/>}/>
          <Route path='/cart' element={<Cart/>}/>
          <Route path='/orders' element={<Orders/>}/>
          <Route path='/order' element={<OrderDispatch/>}/>
          <Route path='/signup' element={<Signup/>}/>
          <Route path='/users/:userid' element={<UserDashboard/>}/>
          <Route path='/books/:bookid' element={<Book/>}/>
        </Routes>
      </BrowserRouter>
    </>
  )
}

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
