import React from "react";
import Input from "./Input";

function RegisterPage(){
    return(
        <>
        <div class="d-flex align-items-center justify-content-center">
            <div class="card registration-form-card col- bg-transparent border-0">
                <div class="card-body">
                    <h1 class="form-header card-title mb-3">
                        <i class="fa fa-edit"></i> Register
                    </h1>
                    <form class="reg-form">
                        <Input type={'text'} name={'username'} placeholder={'Username / Nickname'} />
                        <div class="row">
                            <Input type={'text'} name={'first-name'} placeholder={'First Name'} />  
                            <Input type={'text'} name={'last-name'} placeholder={'Last Name'} />
                        </div>
                        <Input type={'email'} name={'email'} placeholder={'Email'} />
                        <div class="row">
                            <Input type={'password'} name={'password'} placeholder={'Password'} />
                            <Input type={'password'} name={'confirm-password'} placeholder={'Confirm Password'} />
                        </div>
                        <div class="form-group col">
                            <button class="btn btn-lg">Register</button>
                        </div>
                    </form>
                    <p class="card-text text-white my-2">Already have an account? <span class="ms-0 text-warning"><a href="login" class="btn bt-sm text-warning">Sign in</a></span></p>
                        <small class="text-warning">
                            <a href="/" class="btn btn-sm text-warning"><i class="fa fa-arrow-alt-circle-left me-1"></i>Back</a> 
                        </small>
                    </div>
                </div>
            </div>
        </>
    )
} export default RegisterPage;