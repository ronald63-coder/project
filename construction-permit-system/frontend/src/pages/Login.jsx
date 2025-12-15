import React from 'react'
import LoginForm from '../components/LoginForm'
import UsersSelector from '../components/UsersSelector'

export default function Login(){
  return (
    <div className="max-w-4xl mx-auto grid md:grid-cols-2 gap-6">
      <LoginForm />
      <div className="bg-white p-6 rounded shadow">
        <h3 className="text-lg font-semibold mb-3">Or choose a test user</h3>
        <UsersSelector />
      </div>
    </div>
  )
}
