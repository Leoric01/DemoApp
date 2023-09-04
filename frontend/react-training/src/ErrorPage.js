import React from "react";

function ErrorPage(){
    return(
        <>
        <div class="d-flex align-items-center justify-content-center">
            <div id="errorcard" class="card col-4 alert alert-danger border border-danger">
            <h3 class="card-title text-danger"> <i class="fa fa-window-close"></i> Errors:</h3>
            <hr/>
                <div class="card-body">
                <p class="card-text errormsg text-danger">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Deserunt perferendis recusandae ut assumenda magnam impedit fuga 
                blanditiis, voluptates dignissimos laboriosam est nostrum, laudantium voluptate officia vel quia voluptas similique. Cupiditate.
                </p>
                <hr/>
                <a href="login" class="btn btn-sm btn-danger"> <i class="fa fa-arrow-left me-1"></i>Back</a>
                </div>
            </div>
        </div>
        </>
    )
} export default ErrorPage;