import React, { useState, useEffect } from 'react';

function Joke() {
    const [jokeData, setJokeData] = useState(null);

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
        <div>
            {jokeData ? (
                <>
                    <p>{jokeData.setup}</p>
                    <p>{jokeData.punchline}</p>
                </>
            ) : (
                <p>API unreachable</p>
            )}
        </div>
    );
}

export default Joke;
