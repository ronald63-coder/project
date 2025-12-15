import React from 'react'
import { Link } from 'react-router-dom'
import { getCurrentUser, clearAuth } from '../utils/auth'

export default function Navbar({onLogout}){
  const user = getCurrentUser()
  return (
    <header className="bg-white shadow-sm">
      <div className="max-w-6xl mx-auto px-4 py-4 flex justify-between items-center">
        <div className="flex items-center space-x-3">
          <Link to="/" className="text-2xl font-bold text-indigo-600">PermitSys</Link>
          <nav className="hidden md:block">
            <Link to="/dashboard" className="mx-3 text-sm">Dashboard</Link>
            <Link to="/submit" className="mx-3 text-sm">Submit Plan</Link>
            <Link to="/plans" className="mx-3 text-sm">All Plans</Link>
          </nav>
        </div>
        <div>
          {user ? (
            <div className="flex items-center space-x-3">
              <div className="text-sm">{user.name} <span className="text-xs text-gray-500">({user.role})</span></div>
              <button onClick={()=>{ clearAuth(); onLogout && onLogout(); }} className="px-3 py-1 border rounded text-sm">Logout</button>
            </div>
          ) : (
            <Link to="/login" className="px-3 py-1 bg-indigo-600 text-white rounded">Login</Link>
          )}
        </div>
      </div>
    </header>
  )
}
