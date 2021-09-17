import React, { useState, useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import { Input, Button, Header } from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import Errors from "../hiddencomponents/Errors";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  const handleSubmit = async (event) => {
    event.preventDefault();

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

    fetch("http://localhost:5000/authenticate", init)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else if (response.status === 403) {
          // the login failed
          setErrors(["Login failed."]);
        } else {
          setErrors(["Unknown error."]);
        }
      })
      .then((data) => {
        if (data) {
          const { jwt_token } = data;
          auth.login(jwt_token);
          history.push("/");
        }
      })
      .catch((error) => console.log(error)); // send the user to a generic "error" page
  };

  return (
    <div class="ui form">
      <Header as="h1">Login</Header>
      <Errors errors={errors} />
      <form onSubmit={handleSubmit}>
        <div className="App">
          <label>Username: </label>
          <input
            type="text"
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
        <br />
        <div>
          <label>Password: </label>
          <input
            type="password"
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>
        <div>
          <button type="submit">Login</button>
          <br />
          <Link to="/register" color="yellow">
            I don't have an account
          </Link>
        </div>
      </form>
    </div>
  );
}
