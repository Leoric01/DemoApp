import {useState} from "react";

function Counter(){
    const [counter, setCounter] = useState(0);

    function increase(){
        
    }

    function decrease(){

    }



    return(
        <>
        <button>Buy one</button>
        <span>{counter}</span>
        <button>Eat one</button>
        </>
    )
}
export default Counter;