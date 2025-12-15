import React, {useEffect,useState} from 'react'
import { fetchPlans } from '../services/api'
import PlanCard from '../components/PlanCard'

export default function PlanList(){
  const [plans, setPlans] = useState([])
  useEffect(()=>{ fetchPlans().then(setPlans).catch(console.error) },[])
  return (
    <div>
      <h2 className="text-2xl font-semibold mb-4">All Building Plans</h2>
      <div className="grid md:grid-cols-2 gap-4">
        {plans.map(p=> <PlanCard key={p.id} plan={p} />)}
      </div>
    </div>
  )
}
