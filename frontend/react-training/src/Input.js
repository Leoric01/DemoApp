import React from 'react'

function Input(props) {
const { type, name, placeholder } = props


  return (
    <div class="form-group col">
      <input type={type} name={name} className="form-control form-control-lg" placeholder={placeholder} minLength="3" />
    </div>
  )
}

export default Input;