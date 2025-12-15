import React, {useState} from 'react'
import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar'
import Dashboard from './pages/Dashboard'
import SubmitPlan from './pages/SubmitPlan'
import PlanList from './pages/PlanList'
import ReviewPage from './pages/ReviewPage'
import Login from './pages/Login'
import { getCurrentUser, clearAuth } from './utils/auth'

export default function App(){
  const [user, setUser] = useState(getCurrentUser())
  const onLogout = ()=>{ clearAuth(); setUser(null) }

  return (
    <div className="min-h-screen">
      <Navbar onLogout={onLogout} />
      <main className="max-w-6xl mx-auto p-4">
        <Routes>
          <Route path="/" element={<div className="p-6 bg-white rounded shadow">Welcome to PermitSys. Use the Login button or select a fallback user.</div>} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/submit" element={<SubmitPlan />} />
          <Route path="/plans" element={<PlanList />} />
          <Route path="/plans/:id/review" element={<ReviewPage />} />
        </Routes>
      </main>
    </div>
  )
}
