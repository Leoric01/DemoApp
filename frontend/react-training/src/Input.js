import React from 'react'

function Input(props) {
const { type, name, placeholder } = props


  return (
    <div class="form-group col">
    <input type={type} name={name} class="form-control form-control-lg" placeholder={placeholder} minlength="6" />
</div>
  )
}

export default Input;