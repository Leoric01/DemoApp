import {useState} from "react";

function Counter(){
    const [counter, setCounter] = useState(0);

    function increase(){
        setCounter(counter => counter + 1)
    }

    function increaseByTen(){
        setCounter(counter => counter + 10)
    }

    function decrease(){
        if (counter > 0){
            setCounter(counter => counter -1)
        }
    }



    return(
        <>
        <button onClick={increase}>Add to shopping cart</button>
        <button onClick={increaseByTen}>Add 10 items</button>
        <span>{counter}</span>
        <button onClick={decrease}>Remove from shopping cart</button>
        </>
    )
}
export default Counter;