import React from 'react'
export default function FileInput({onChange}){
  return (
    <div className="mt-2">
      <input type="file" onChange={e=> onChange(e.target.files[0])} />
    </div>
  )
}
