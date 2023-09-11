import { useState } from "react";
import axios from "axios";

export default function useToken() {
  const getToken = () => {
    const userToken = sessionStorage.getItem("token");
    return userToken;
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (userToken) => {
    sessionStorage.setItem("token", userToken);
    setToken(userToken);
    axios.interceptors.request.use(function (config) {
      const token = `Bearer ${userToken}`;
      config.headers.Authorization = token;

      return config;
    });
  };

  return {
    setToken: saveToken,
    token,
  };
}
