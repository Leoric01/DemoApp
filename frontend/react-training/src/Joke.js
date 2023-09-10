import React, { useState, useEffect } from 'react';

function Joke() {
    const [jokeData, setJokeData] = useState({});
  
    useEffect(() => {
        fetch('http://localhost:8080/joke/api')
            .then((res) => res.json())
            .then((data) => {
                setJokeData(data);
            })
            .catch((error) => {
                console.error('Error fetching joke:', error);
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
