import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './styles/style2.css';
import useToken from "./useToken";

function Dashboard() {
  const { token, setToken } = useToken();
  const [transactType, setTransactType] = useState("");

  const handleTransactTypeChange = (e) => {
    setTransactType(e.target.value);
  };

  const renderTransactionCard = () => {
    switch (transactType) {
      case "payment":
        return (
          <div className="card payment-card">
            {/* Payment card content */}
          </div>
        );
      case "transfer":
        return (
          <div className="card transfer-card">
            {/* Transfer card content */}
          </div>
        );
      case "deposit":
        return (
          <div className="card deposit-card">
            {/* Deposit card content */}
          </div>
        );
      case "withdraw":
        return (
          <div className="card withdraw-card">
            {/* Withdraw card content */}
          </div>
        );
      default:
        return null;
    }
  };
  console.log(token)
  return (
    <div>
      <header className="main-page-header mb-3 bg-primary">
        <div class="container d-flex align-items-center">
          <div class="company-name">
            My-Super Bank
          </div>
          <nav class="navigation">
            <li><a href="">Dashboard</a></li>
            <li><a href="">Payment History</a></li>
            <li><a href="">Transaction History</a></li>
          </nav>
          <div class="display-name ms-auto text-white">
            <i class="fa fa-circle text-success me-2"></i>Welcome: <span>{token}</span>
          </div>
          <a href="/" class="btn btn-sm text-white" onClick={() => setToken(undefined)}>
            <i class="fa fa-sign-out ms-2 me-1"></i> Sign out
          </a>
        </div>
      </header>

      <div className="container d-flex">
        <div class="offcanvas offcanvas-start" tabindex="-1" id="offCanvasExample" aria-labeledby="offCanvasExampleLabel">
          <div class="offcanvas-header">
            <h5 class="offcanvas-title text-white" id="offCanvasExampleLabel">Transact</h5>
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
          </div>
          <div class="offcanvas-body">
            <small class="card-text text-white">
              Choose an option below to perform a transaction
            </small>
            <select name="transact-type" class="form-control my-3" id="transact-type">
              <option value="">-- Select transact type--</option>
              <option value="payment">Payment</option>
              <option value="transfer">Transfer</option>
              <option value="deposit">Deposit</option>
              <option value="withdraw">Withdraw</option>
            </select>
            <div class="card payment-card">
              <div class="card-body">
                <div class="form-group mb-2">
                  <label for="">Account Holder / Beneficiary</label>
                  <input type="text" name="beneficiary" class="form-control" placeholder="Account Holder / Beneficiary Account" />
                </div>
                <div class="form-group mb-2">
                  <label for="">Beneficiary Account Number</label>
                  <input type="text" name="account_number" class="form-control" placeholder="Beneficiary Account No" />
                </div>
                <div class="form-group">
                  <label for="">Select account</label>
                  <select name="account_id" class="form-control my-1" id="">
                    <option value="">Select account</option>
                  </select>
                </div>
                <div class="form-group mb-2">
                  <label for="">Reference</label>
                  <input type="text" name="reference" class="form-control" placeholder="Reference" />
                </div>
                <div class="form-group mb-2">
                  <label for="">Payment Amount</label>
                  <input type="text" name="payment_amount" class="form-control" placeholder="Payment Amount" />
                </div>
                <div class="form-group mb2">
                  <button id="" class="btn btn-md transact-btn">Pay</button>
                </div>
              </div>
            </div>
            <div class="card transfer-card">
              <div class="card-body">
                <div class="form-group">
                  <label for="">Select account from</label>
                  <select name="account_id" class="form-control my-1" id="">
                    <option value="">Select account from</option>
                  </select>
                </div>
                <div class="form-group">
                  <label for="">Select account to</label>
                  <select name="account_id" class="form-control my-1" id="">
                    <option value="">Select account to</option>
                  </select>
                </div>
                <div class="form-group mb-2">
                  <label for="">Transfer Amount</label>
                  <input type="text" name="transfer_amount" class="form-control" placeholder="Transfer Amount" />
                </div>
                <div class="form-group mb2">
                  <button id="" class="btn btn-md transact-btn">Transfer</button>
                </div>
              </div>
            </div>
            <div class="card deposit-card">
              <div class="card-body">
                <form action="" class="deposit-form">
                  <div class="form-group mb-2">
                    <label for="">Select account</label>
                    <select name="account_id" class="form-control my-1" id="">
                      <option value="">Select account</option>
                    </select>
                  </div>
                  <div class="form-group mb-2">
                    <label for="">Deposit Amount</label>
                    <input type="text" name="deposit_amount" class="form-control" placeholder="Deposit Amount" />
                  </div>
                  <div class="form-group mb2">
                    <button id="" class="btn btn-md transact-btn">Deposit</button>
                  </div>
                </form>
              </div>
            </div>
            <div class="card withdraw-card">
              <div class="card-body">
                <form action="" class="withdraw-form">
                  <div class="form-group mb-2">
                    <label for="">Select account</label>
                    <select name="account_id" class="form-control my-1" id="">
                      <option value="">Select account</option>
                    </select>
                  </div>
                  <div class="form-group mb-2">
                    <label for="">Withdrawal Amount</label>
                    <input type="text" name="withdrawal_amount" class="form-control" placeholder="Withdrawal Amount" />
                  </div>
                  <div class="form-group mb2">
                    <button id="" class="btn btn-md transact-btn">Withdraw</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container d-flex">
        <button id="add-account-btn" class="btn btn-lg shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offCanvasRight" aria-controls="offCanvasRight">
          <i class="fa fa-credit-card me-1"></i>Add New Account
        </button>
        <button id="transact-btn" class="btn btn-lg ms-auto shadow" data-bs-toggle="offcanvas" data-bs-target="#offCanvasExample" aria-controls="offCanvasExample">
          <i class="fa fa-wallet me-1"></i>Transact
        </button>
      </div>

      <div className="container mt-5">
        <h1>Accordion Example</h1>
        <div class="accordion" id="accordionExample">
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingOne">
              <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                Section 1
              </button>
            </h2>
            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                Content for section 1 goes her e.
              </div>
            </div>
          </div>
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                Section 2
              </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                Content for section 2 goes here.
              </div>
            </div>
          </div>
          <div class="accordion-item">
            <h2 class="accordion-header" id="headingThree">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                Section 3
              </button>
            </h2>
            <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
              <div class="accordion-body">
                Content for section 3 goes here.
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="container">
        <div class="card no-accounts-card">
          <div class="card-body">
            <h1 class="card-title">
              <i class="fa fa-ban text-danger"></i> No Registered Accounts
            </h1>
            <hr />
            <div class="card-text">
              You currently do not have any regeistered accounts. <br /> Please click below to register / add new account.
            </div>
            <br />
            <button class="btn btn-lg btn-primary shadow" type="button" data-bs-toggle="offcanvas" data-bs-target="#offCanvasRight" aria-controls="offCanvasRight">
              <i class="fa fa-credit-card me-1"></i>Add New Account
            </button>
          </div>
        </div>
      </div>

      {/* Render the transaction card based on the selected transact type */}
      {renderTransactionCard()}
    </div >
  );
}

export default Dashboard;
