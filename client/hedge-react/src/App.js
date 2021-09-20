import { useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import jwt_decode from "jwt-decode";
import "semantic-ui-css/semantic.min.css";
import "./App.css";

import AuthContext from "./AuthContext";

import backDropVideo from "./components/videocomponents/backDropVideo.mp4";
import HomeNavBar from "./components/hiddencomponents/HomeNavBar";
import Game from "./components/homecomponents/Game";
import Info from "./components/homecomponents/Info";
import Settings from "./components/homecomponents/Settings";
import Login from "./components/homecomponents/Login";
import Register from "./components/homecomponents/Register";
import NotFound from "./components/hiddencomponents/NotFound";
import About from "./components/infocomponents/About";
import Contact from "./components/infocomponents/Contact";
import HowToPlay from "./components/infocomponents/HowToPlay";
import Leaderboard from "./components/infocomponents/Leaderboard";

function Home() {
  return <h2 id="header">Home</h2>;
}

function App() {
  const [user, setUser] = useState(null);

  const login = (token) => {
    const decodedTokenPayload = jwt_decode(token);
    const roles = decodedTokenPayload.roles.split(",");

    const user = {
      id: decodedTokenPayload.id,
      username: decodedTokenPayload.sub,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      },
    };

    setUser(user);
  };

  const logout = () => {
    setUser(null);
  };

  const auth = {
    user,
    login,
    logout,
  };

  return (
    <>
      <AuthContext.Provider value={auth}>
        <Router>
          <div className="App">
            <HomeNavBar />

            <Switch>
              <Route exact path="/">
                <Home />
              </Route>

              <Route path="/game">
                {auth.user ? <Game /> : <Redirect to="/login" />}
              </Route>

              <Route exact path="/info">
                <Info />
              </Route>

              <Route path="/info/how_to_play">
                <HowToPlay />
              </Route>

              <Route path="/info/about">
                <About />
              </Route>

              <Route path="/info/contact">
                <Contact />
              </Route>

              <Route path="/info/leaderboard">
                {auth.user ? <Leaderboard /> : <Redirect to="/login" />}
              </Route>

              <Route path="/settings">
                <Settings />
              </Route>

              <Route path="/login">
                <Login />
              </Route>

              <Route path="/register">
                <Register />
              </Route>

              <Route path="*">
                <NotFound />
              </Route>
            </Switch>
          </div>
        </Router>
      </AuthContext.Provider>

      <video id="backdrop" autoPlay loop muted>
        <source src={backDropVideo} type="video/mp4" />
      </video>
    </>
  );
}

export default App;
