import React from 'react'
export default function PlanCard({plan, onReview, onChangeStatus}){
  return (
    <div className="bg-white rounded shadow p-4">
      <h3 className="text-lg font-semibold">{plan.title}</h3>
      <p className="text-sm text-gray-600">{plan.description}</p>
      <div className="mt-2 text-sm">Status: <strong>{plan.status}</strong></div>
      <div className="mt-3 flex space-x-2">
        <button onClick={()=>onReview && onReview(plan)} className="px-3 py-1 border rounded text-sm">Review</button>
        <button onClick={()=>onChangeStatus && onChangeStatus(plan, 'UNDER_REVIEW')} className="px-3 py-1 border rounded text-sm">Under review</button>
      </div>
    </div>
  )
}
