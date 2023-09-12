import { useState } from "react";
import axios from "axios";



export default function useToken() {
  const getToken = () => {
    const userToken = sessionStorage.getItem("token");
    return userToken;
  };

  const [token, setToken] = useState(getToken());

  const saveToken = (userToken) => {
    if (userToken !== undefined) {
      sessionStorage.setItem("token", userToken);
    }
    else {
      sessionStorage.removeItem("token");
    }
    setToken(userToken);

  };

  return {
    setToken: saveToken,
    token,
  };
}
