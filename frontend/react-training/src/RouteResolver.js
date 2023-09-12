import { BrowserRouter, Route, Routes } from "react-router-dom";
import Counter from "./Counter";
import Joke from "./Joke";
import LandingPage from "./LandingPage";
import ErrorPage from "./ErrorPage";
import LoginPage from "./LoginPage/LoginPage";
import RegisterPage from "./RegisterPage";
import { useState } from "react";
import Dashboard from "./Dashboard";
import useToken from "./useToken";
import axios from "axios";

function RouteResolver() {
  const { token, setToken } = useToken();
  const [error, setError] = useState();
  if (
    token
  ) {
    axios.interceptors.request.use(function (config) {
      const tkn = `Bearer ${token}`;
      config.headers.Authorization = tkn;

      return config;
    });
  }

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/counter" element={<Counter />} />
          <Route path="/joke" element={<Joke />} />
          <Route path="/" element={<LandingPage />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/error" element={<ErrorPage error={error} />} />
          <Route
            path="/login"
            element={<LoginPage setToken={setToken} setError={setError} />}
          />
          <Route path="/register" element={<RegisterPage setError={setError} />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
export default RouteResolver;