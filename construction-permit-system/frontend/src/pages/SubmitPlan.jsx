import React, {useState} from 'react'
import { getCurrentUser } from '../utils/auth'
import { uploadBuildingPlan } from '../services/api'
import FileInput from '../components/FileInput'

export default function SubmitPlan(){
  const user = getCurrentUser()
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  const [file, setFile] = useState(null)
  const [loading, setLoading] = useState(false)

  const handleSubmit = async (e)=>{
    e.preventDefault()
    if(!user){ alert('Please login or select a user'); return }
    const fd = new FormData()
    fd.append('title', title)
    fd.append('description', description)
    fd.append('applicantId', user.id)
    if(file) fd.append('file', file)
    setLoading(true)
    try{
      await uploadBuildingPlan(fd)
      alert('Submitted successfully')
      window.location.href = '/dashboard'
    }catch(e){
      alert('Submit failed: ' + (e.response?.data || e.message))
    }finally{ setLoading(false) }
  }

  return (
    <div className="max-w-2xl mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-xl font-semibold mb-4">Submit Building Plan</h2>
      <form onSubmit={handleSubmit} className="space-y-3">
        <div>
          <label className="block text-sm">Title</label>
          <input className="w-full border p-2 rounded" value={title} onChange={e=>setTitle(e.target.value)} />
        </div>
        <div>
          <label className="block text-sm">Description</label>
          <textarea className="w-full border p-2 rounded" value={description} onChange={e=>setDescription(e.target.value)} />
        </div>
        <div>
          <label className="block text-sm">Upload drawing (PDF, image)</label>
          <FileInput onChange={setFile} />
        </div>
        <div className="text-right">
          <button disabled={loading} className="px-4 py-2 bg-indigo-600 text-white rounded">{loading? 'Submitting...' : 'Submit'}</button>
        </div>
      </form>
    </div>
  )
}
