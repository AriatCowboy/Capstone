import React, { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";

import AuthContext from "../../AuthContext";
import Errors from "../hiddencomponents/Errors";

export default function Register() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errorList, setErrorList] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (password !== confirmPassword) {
      setErrorList(["`password` and `confirm password` don't match."]);
      return;
    }

    const credentials = {
      username,
      password,
    };

    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credentials),
    };

    fetch("http://localhost:5000/create_account", init)
      .then((response) => {
        if (response.status === 400 || response.status === 201) {
          return response.json();
        }
        return Promise.reject("Something went wrong :)");
      })
      .then((data) => {
        if (data.id) {
          // auto-login the user...

          fetch("http://localhost:5000/authenticate", init)
            .then((response) => {
              if (response.status === 200) {
                return response.json();
              } else if (response.status === 403) {
                // the login failed
                setErrorList(["Login failed."]);
              } else {
                setErrorList(["Unknown error."]);
              }
            })
            .then((data) => {
              if (data) {
                const { jwt_token } = data;
                auth.login(jwt_token);
                history.push("/");
              }
            })
            .catch((error) => console.log(error));
        } else {
          setErrorList(data.messages);
        }
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      <h2>Register</h2>
      <Errors errors={errorList} />
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>
        <div>
          <label>Confirm Password:</label>
          <input
            type="password"
            onChange={(event) => setConfirmPassword(event.target.value)}
          />
        </div>
        <div>
          <button type="submit">Register</button>
          <Link to="/login">I already have an account</Link>
        </div>
      </form>
    </div>
  );
}
