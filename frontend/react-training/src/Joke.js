import React, { useState, useEffect } from "react";
import axios from "axios";

import useToken from "./useToken";

function Joke() {
  const [jokeData, setJokeData] = useState({});
  const { token } = useToken();

  useEffect(() => {
    axios("http://localhost:8080/joke/api")
      .then((data) => {
        setJokeData(data.data);
      })
      .catch((error) => {
        console.error("Error fetching joke:", error);
      });
  }, []);

  return (
    <>
      {jokeData ? (
        <>
          <p>{jokeData.setup}</p>
          <p>{jokeData.punchline}</p>
        </>
      ) : (
        <p>API unreachable</p>
      )}
    </>
  );
}

export default Joke;
