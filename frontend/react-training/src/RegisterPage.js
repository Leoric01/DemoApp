import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Input from "./Input";
import './styles/style1.css';

function RegisterPage({ setError }) {
    const navigate = useNavigate();
    const [username, setUserName] = useState("");
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmpassword, setConfirmPassword] = useState("");

    function handleRegister(e) {
        e.preventDefault();
        const formData = {
            username,
            firstname,
            lastname,
            email,
            password,
            matchingPassword:confirmpassword,
        };
        axios
            .post("http://localhost:8080/auth/register", formData)
            .then((e) => {
                navigate("/login");
                console.log(username);
            })
            .catch((e) => {
                setError(e);
                console.error("Registration failed:", e);
                navigate("/error");
            });
    }
    

    return (
        <div className="registerPage">
            <div className="d-flex align-items-center justify-content-center">
                <div className="card registration-form-card col- bg-transparent border-0">
                    <div className="card-body">
                        <h1 className="form-header card-title mb-3">
                            <i className="fa fa-edit"></i> Register
                        </h1>
                        <form className="reg-form" onSubmit={handleRegister}>
                            <Input
                                type='text'
                                name='username'
                                placeholder={'Username / Nickname'}
                                onChange={(e) => setUserName(e.target.value)}
                            />
                            <div className="row">
                                <Input
                                    type='text'
                                    name='firstname'
                                    placeholder={'First Name'}
                                    onChange={(e) => setFirstName(e.target.value)}
                                />
                                <Input
                                    type={'text'}
                                    name={'lastname'}
                                    placeholder={'Last Name'}
                                    onChange={(e) => setLastName(e.target.value)}
                                />
                            </div>
                            <Input
                                type={'email'}
                                name={'email'}
                                placeholder={'Email'}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                            <div className="row">
                                <Input
                                    type={'password'}
                                    name={'password'}
                                    placeholder={'Password'}
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                                <Input
                                    type={'password'}
                                    name={'confirmpassword'}
                                    placeholder={'Confirm Password'}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                />
                            </div>
                            <div className="form-group col">
                                <button className="btn btn-lg" type="submit">Register</button>
                            </div>
                        </form>
                        <p className="card-text text-white my-2">Already have an account? <span className="ms-0 text-warning"><a href="/login" className="btn bt-sm text-warning">Sign in</a></span></p>
                        <small className="text-warning">
                            <a href="/" className="btn btn-sm text-warning"><i className="fa fa-arrow-alt-circle-left me-1"></i>Back</a>
                        </small>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default RegisterPage;