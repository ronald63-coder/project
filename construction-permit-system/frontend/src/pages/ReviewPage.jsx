import React, {useEffect,useState} from 'react'
import { useParams } from 'react-router-dom'
import { fetchPlans, fetchReviewsByPlan, createReview, updatePlanStatus } from '../services/api'
import { getCurrentUser } from '../utils/auth'

export default function ReviewPage(){
  const { id } = useParams()
  const [plan, setPlan] = useState(null)
  const [reviews, setReviews] = useState([])
  const [comment, setComment] = useState('')
  const user = getCurrentUser()

  useEffect(()=>{
    fetchPlans().then(plans=> setPlan(plans.find(p=>String(p.id)===String(id))))
    fetchReviewsByPlan(id).then(setReviews)
  },[id])

  const submitReview = async ()=>{
    if(!user){ alert('Login required'); return }
    try{
      await createReview(id, user.id, { comments: comment })
      setComment('')
      const r = await fetchReviewsByPlan(id)
      setReviews(r)
    }catch(e){ alert('Error: '+(e.response?.data || e.message)) }
  }

  const changeStatus = async (status)=>{
    try{
      await updatePlanStatus(id, status)
      alert('Status updated')
      const plans = await fetchPlans()
      setPlan(plans.find(p=>String(p.id)===String(id)))
    }catch(e){ alert('Error:'+ (e.response?.data || e.message)) }
  }

  if(!plan) return <div>Loading...</div>

  return (
    <div className="max-w-3xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-xl font-semibold">Plan: {plan.title}</h2>
      <p className="text-sm text-gray-600">{plan.description}</p>
      <div className="mt-4">
        <h3 className="font-semibold">Reviews</h3>
        <ul className="list-disc pl-5 mt-2">
          {reviews.map(r=> <li key={r.id}>{r.comments} <span className="text-xs text-gray-400">by {r.reviewer?.name}</span></li>)}
        </ul>
      </div>

      <div className="mt-4">
        <textarea className="w-full border p-2 rounded" value={comment} onChange={e=>setComment(e.target.value)} placeholder="Write review..." />
        <div className="mt-2 space-x-2">
          <button onClick={submitReview} className="px-3 py-1 bg-indigo-600 text-white rounded">Submit Review</button>
          <button onClick={()=>changeStatus('UNDER_REVIEW')} className="px-3 py-1 border rounded">Mark Under Review</button>
          <button onClick={()=>changeStatus('APPROVED')} className="px-3 py-1 border rounded">Approve</button>
          <button onClick={()=>changeStatus('REJECTED')} className="px-3 py-1 border rounded">Reject</button>
        </div>
      </div>
    </div>
  )
}
