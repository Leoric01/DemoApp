import React from "react";
import './styles/style1.css';

function ErrorPage({ error }) {
  return (
    <div className="errorPage">
      <div className="d-flex align-items-center justify-content-center">
        <div
          id="errorcard"
          className="card col-4 alert alert-danger border border-danger"
        >
          <h3 className="card-title text-danger">
            {" "}
            <i className="fa fa-window-close"></i> Errors:
          </h3>
          <hr />
          <div className="card-body">
            <p className="card-text errormsg text-danger">
              {error ? error.response.data.error : "Unknown error"}
            </p>
            <hr />
            <a href="login" className="btn btn-sm btn-danger">
              {" "}
              <i className="fa fa-arrow-left me-1"></i>Back
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}
export default ErrorPage;
