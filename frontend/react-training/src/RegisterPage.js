import React from "react";
import Input from "./Input";
//import './styles/style.css';


function RegisterPage(){
    return(
        <>
        <div className="d-flex align-items-center justify-content-center">
            <div className="card registration-form-card col- bg-transparent border-0">
                <div className="card-body">
                    <h1 className="form-header card-title mb-3">
                        <i className="fa fa-edit"></i> Register
                    </h1>
                    <form className="reg-form">
                        <Input type={'text'} name={'username'} placeholder={'Username / Nickname'} />
                        <div className="row">
                            <Input type={'text'} name={'first-name'} placeholder={'First Name'} />  
                            <Input type={'text'} name={'last-name'} placeholder={'Last Name'} />
                        </div>
                        <Input type={'email'} name={'email'} placeholder={'Email'} />
                        <div className="row">
                            <Input type={'password'} name={'password'} placeholder={'Password'} />
                            <Input type={'password'} name={'confirm-password'} placeholder={'Confirm Password'} />
                        </div>
                        <div className="form-group col">
                            <button className="btn btn-lg">Register</button>
                        </div>
                    </form>
                    <p className="card-text text-white my-2">Already have an account? <span className="ms-0 text-warning"><a href="login" className="btn bt-sm text-warning">Sign in</a></span></p>
                        <small className="text-warning">
                            <a href="/" className="btn btn-sm text-warning"><i className="fa fa-arrow-alt-circle-left me-1"></i>Back</a> 
                        </small>
                    </div>
                </div>
            </div>
        </>
    )
} export default RegisterPage;