import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
//import './styles/style.css';

function LandingPage() {
    return (
        <div className="d-flex align-items-center">
            <div id="sample-text-card" className="card col-6 bg-transparent border-0">
                <div className="card-body">
                    <h1 className="mb-3">My-Super Bank</h1>
                    <h5 className="card-title">Easy Banking solutions</h5>
                    <p className="card-text">
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Consectetur sequi quia reprehenderit cum consequatur accusantium 
                        sapiente adipisci minus ipsa voluptate debitis quas commodi amet aperiam distinctio consequuntur perspiciatis dolores 
                        velit, incidunt eos? Eos necessitatibus repudiandae consequuntur velit id? Eius vitae, voluptatum voluptas animi 
                        cupiditate alias nisi voluptate facere iusto laboriosam.
                    </p>
                    <div className="button-wrapper d-flex align-items-center">
                        <a id="register-button" className="btn btn-md register" role="button" href="register">Register</a>
                        <a id="login-button" className="btn btn-md login" role="button" href="login">Login</a>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LandingPage;
