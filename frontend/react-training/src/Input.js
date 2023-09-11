import React from 'react'

function Input(props) {
  const { type, name, placeholder, onChange } = props


  return (
    <div className="form-group col">
      <input type={type} name={name} onChange={onChange} className="form-control form-control-lg" placeholder={placeholder} minLength="3" />
    </div>
  )
}

export default Input;