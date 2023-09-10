import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./style.css";

function LoginPage({ setToken, setError }) {
  const navigate = useNavigate();
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();
  function handleLogin(e) {
    e.preventDefault();
    axios
      .post("http://localhost:8080/auth/login", {
        username,
        password,
      })
      .then((e) => {
        setToken(e.data.token);
        navigate("/dashboard");
      })
      .catch((e) => {
        setError(e);
        navigate("/error");
      });
  }
  return (
    <>
      <div className="loginPage">
        <div
          className="d-flex align-items-center justify-content-center"
          id="login-card-form"
        >
          <div className="card login-form-card col-4 bg-transparent border-0">
            <div className="card-body">
              <h1 className="form-header card-title mb-3">
                <i className="fa fa-user-circle"></i> Login
              </h1>
              <form className="login-form" onSubmit={handleLogin}>
                <input
                  class="form-control form-control-lg"
                  type="text"
                  placeholder="Username"
                  onChange={(e) => setUserName(e.target.value)}
                />
                <input
                  class="form-control form-control-lg"
                  type="password"
                  placeholder="Password"
                  onChange={(e) => setPassword(e.target.value)}
                />
                <div className="">
                  <button className="btn btn-lg" type="submit">
                    Login
                  </button>
                </div>
              </form>
              <p className="card-text text-white my-2">
                Don't have an account?{" "}
                <span class="ms-0 text-warning">
                  <a href="register" class="btn bt-sm text-warning">
                    Sign up
                  </a>
                </span>
              </p>
              <small className="text-warning">
                <a href="/" className="btn btn-sm text-warning">
                  <i class="fa fa-arrow-alt-circle-left me-1"></i>Back
                </a>
              </small>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
export default LoginPage;
