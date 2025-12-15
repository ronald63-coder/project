import React, {useEffect, useState} from 'react'
import { fetchUsers } from '../services/api'
import { saveAuth } from '../utils/auth'

export default function UsersSelector(){
  const [users, setUsers] = useState([])
  useEffect(()=>{ fetchUsers().then(setUsers).catch(()=>{}) },[])
  return (
    <select onChange={e=>{
      const u = users.find(x=>String(x.id)===String(e.target.value))
      if(u){
        // simulate token by storing user object as currentUser
        localStorage.setItem('currentUser', JSON.stringify(u))
        window.location.href = '/dashboard'
      }
    }} className="border p-1 rounded">
      <option value="">-- Select user (fallback) --</option>
      {users.map(u=> <option key={u.id} value={u.id}>{u.name} ({u.role})</option>)}
    </select>
  )
}
