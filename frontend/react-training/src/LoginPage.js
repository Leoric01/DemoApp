import React from "react";
import Input from "./Input";

function LoginPage(){
    return(
        <>
            <div className="d-flex align-items-center justify-content-center" id="login-card-form">
                <div className="card login-form-card col-4 bg-transparent border-0">
                    <div className="card-body">
                        <h1 className="form-header card-title mb-3">
                            <i className="fa fa-user-circle"></i> Login
                        </h1>
                        <form className="login-form">
                            <Input type={'text'} name={'username'} placeholder={'Username'} />
                            <Input type={'password'} name={'password'} placeholder={'Password'} />
                            <div className="form-group col">
                                <button className="btn btn-lg">Login</button>
                            </div>
                        </form>
                        <p className="card-text text-white my-2">Don't have an account? <span class="ms-0 text-warning"><a href="register" class="btn bt-sm text-warning">Sign up</a></span></p>
                        <small className="text-warning">
                            <a href="/" className="btn btn-sm text-warning"><i class="fa fa-arrow-alt-circle-left me-1"></i>Back</a> 
                        </small>
                    </div>
                </div>
            </div>
        </>
    )
} export default LoginPage;