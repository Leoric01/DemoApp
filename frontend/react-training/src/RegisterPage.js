import React from "react";
import Input from "./Input";

function RegisterPage(){
    return(
        <>
<div class="d-flex align-items-center justify-content-center">
    <div class="card registration-form-card col-6 bg-transparent border-0">
        <div class="card-body">
            <h1 class="form-header card-title mb-3">
                <i class="fa fa-edit"></i> Register
            </h1>
            <form class="reg-form">
                <div class="form-group col">
                    <input type="text" name="username" class="form-control form-control-lg" placeholder="Username / Nickname" />
                </div>
                <div class="row">
                    <div class="form-group col">
                        <input type="text" name="first_name" class="form-control form-control-lg" placeholder="First Name"/>
                    </div>
                    <div class="form-group col">
                        <input type="text" name="last_name" class="form-control form-control-lg" placeholder="Last Name"/>
                    </div>
                </div>
                <div class="form-group col">
                    <input type="email" name="email" class="form-control form-control-lg" placeholder="Email" />
                </div>
                <div class="row">
                    <Input type={'password'} name={'password'} placeholder={'Password'} />
                    <div class="form-group col">
                        <input type="password" name="password" class="form-control form-control-lg" placeholder="Password" minlength="6" />
                    </div>
                    <div class="form-group col">
                        <input type="password" name="confirm_password" class="form-control form-control-lg" placeholder="Confirm Password" minlength="6" />
                    </div>
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