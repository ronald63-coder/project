import React from 'react'
import { Navigate } from 'react-router-dom'
import { getCurrentUser } from '../utils/auth'

export default function ProtectedRoute({children, role}){
  const user = getCurrentUser()
  if(!user) return <Navigate to="/login" replace />
  if(role && user.role !== role) return <div className="p-6">Access denied for role {user.role}</div>
  return children
}
