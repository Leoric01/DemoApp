import React from "react";

function ErrorPage({ error }) {
  return (
    <>
      <div class="d-flex align-items-center justify-content-center">
        <div
          id="errorcard"
          class="card col-4 alert alert-danger border border-danger"
        >
          <h3 class="card-title text-danger">
            {" "}
            <i class="fa fa-window-close"></i> Errors:
          </h3>
          <hr />
          <div class="card-body">
            <p class="card-text errormsg text-danger">
              {error ? error.response.data.error : "Unknown error"}
            </p>
            <hr />
            <a href="login" class="btn btn-sm btn-danger">
              {" "}
              <i class="fa fa-arrow-left me-1"></i>Back
            </a>
          </div>
        </div>
      </div>
    </>
  );
}
export default ErrorPage;
