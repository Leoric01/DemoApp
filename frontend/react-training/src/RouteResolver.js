import { BrowserRouter, Route, Routes } from "react-router-dom";
import Counter from "./Counter";
import Joke from "./Joke";
import LandingPage from "./LandingPage";
import ErrorPage from "./ErrorPage";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";

function RouteResolver(){
    return(
        <>
        <BrowserRouter>
        <Routes>
            <Route path="/counter" element={<Counter />} />
            <Route path="/joke" element={<Joke />} />
            <Route path="/" element={<LandingPage />} />
            <Route path="/error" element={<ErrorPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
        </Routes>
        </BrowserRouter>
        </>
    )
}export default RouteResolver;