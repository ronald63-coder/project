import React from 'react'
import { useForm } from 'react-hook-form'
import { login } from '../services/api'
//'src/main/java/com/construction/construction_permit_system/service/DataInitializer'
//'../services/DataInitializer'

import { saveAuth } from '../utils/auth'
import { useNavigate } from 'react-router-dom'

export default function LoginForm(){
  const { register, handleSubmit } = useForm()
  const navigate = useNavigate()

  const onSubmit = async (data) => {
    try{
      const res = await login(data)
      if(res.token){
        saveAuth(res.token)
        navigate('/dashboard')
      } else {
        alert('Login response did not include token.')
      }
    }catch(e){
      alert('Login failed: ' + (e.response?.data || e.message))
    }
  }

  return (
    <div className="max-w-md mx-auto bg-white p-6 rounded shadow">
      <h2 className="text-xl font-semibold mb-4">Sign in</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-3">
        <div>
          <label className="block text-sm">Username</label>
          <input {...register('username')} className="w-full border p-2 rounded" />
        </div>
        <div>
          <label className="block text-sm">Password</label>
          <input type="password" {...register('password')} className="w-full border p-2 rounded" />
        </div>
        <div className="text-right">
          <button type="submit" className="px-4 py-2 bg-indigo-600 text-white rounded">Login</button>
        </div>
      </form>
    </div>
  )
}
