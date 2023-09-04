import React from "react";

function LoginPage(){
    return(
        <>
        <div class="d-flex align-items-center justify-content-center">
    <div class="card login-form-card col-4 bg-transparent border-0">
        <div class="card-body">
            <h1 class="form-header card-title mb-3">
                <i class="fa fa-user-circle"></i> Login
            </h1>
            <form class="login-form">
                <div class="form-group col">
                    <input type="username" name="username" class="form-control form-control-lg" placeholder="Username" />
                </div>
                <div class="form-group col">
                    <input type="password" name="password" class="form-control form-control-lg" placeholder="Password" minlength="6" />
                </div>
               
                <div class="form-group col">
                    <button class="btn btn-lg">Login</button>
                </div>
            </form>
            <p class="card-text text-white my-2">Don't have an account? <span class="ms-0 text-warning"><a href="register" class="btn bt-sm text-warning">Sign up</a></span></p>
            <small class="text-warning">
                <a href="/" class="btn btn-sm text-warning"><i class="fa fa-arrow-alt-circle-left me-1"></i>Back</a> 
            </small>
        </div>
    </div>
</div>
        </>
    )
} export default LoginPage;