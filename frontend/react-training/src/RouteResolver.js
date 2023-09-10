import { BrowserRouter, Route, Routes } from "react-router-dom";
import Counter from "./Counter";
import Joke from "./Joke";
import LandingPage from "./LandingPage";
import ErrorPage from "./ErrorPage";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import { useState } from "react";

import useToken from "./useToken";

function RouteResolver() {
  const { token, setToken } = useToken();
  const [error, setError] = useState();
  if (
    !token &&
    sessionStorage.getItem("token") &&
    sessionStorage.getItem("token") !== "null"
  ) {
    setToken(sessionStorage.getItem("token"));
  }

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/counter" element={<Counter />} />
          <Route path="/joke" element={<Joke />} />
          <Route path="/" element={<LandingPage />} />
          <Route path="/error" element={<ErrorPage error={error} />} />
          <Route
            path="/login"
            element={<LoginPage setToken={setToken} setError={setError} />}
          />
          <Route path="/register" element={<RegisterPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}
export default RouteResolver;
