import React, {useEffect, useState} from 'react'
import { fetchPlans, fetchPlansByApplicant } from '../services/api'
import { getCurrentUser } from '../utils/auth'
import PlanCard from '../components/PlanCard'
import { Link } from 'react-router-dom'

export default function Dashboard(){
  const [plans, setPlans] = useState([])
  const user = getCurrentUser()

  useEffect(()=>{
    if(!user) return
    if(user.role === 'APPLICANT') {
      fetchPlansByApplicant(user.id).then(setPlans).catch(console.error)
    } else {
      fetchPlans().then(setPlans).catch(console.error)
    }
  },[user])

  if(!user) return <div>Please login or select a fallback user.</div>

  return (
    <div>
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-semibold">Dashboard</h2>
        <div>
          <Link to="/submit" className="px-3 py-2 bg-indigo-600 text-white rounded">Submit New Plan</Link>
        </div>
      </div>

      <div className="grid md:grid-cols-2 gap-4">
        {plans.map(p=> <PlanCard key={p.id} plan={p} onReview={()=> window.location.href = `/plans/${p.id}/review`} />)}
      </div>
    </div>
  )
}
