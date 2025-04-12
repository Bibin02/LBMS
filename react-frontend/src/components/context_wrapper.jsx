import React from 'react'
import { AppContextProvider } from './app_context'
import { Outlet } from 'react-router-dom'

const ContextWrapper = () => {
  return (
    <AppContextProvider>
        <Outlet />
    </AppContextProvider>
  )
}

export default ContextWrapper
