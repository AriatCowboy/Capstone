import { useState } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";
import jwt_decode from "jwt-decode";
import "semantic-ui-css/semantic.min.css";
import "./App.css";

import backDropVideo from "./components/videocomponents/backDropVideo.mp4";
import HomeNavBar from "./components/hiddencomponents/HomeNavBar";
import PlayGame from "./components/homecomponents/PlayGame";
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
  return <h2>Home</h2>;
}

function App() {
  return (
    <>
      <Router>
        <div className="App">
          <HomeNavBar />

          <Switch>
            <Route exact path="/">
              <Home />
            </Route>

            <Route path="/play_game">
              <PlayGame />
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
              <Leaderboard />
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

            <Route path="/not_found">
              <NotFound />
            </Route>
          </Switch>
        </div>
      </Router>

      <video id="backdrop" autoPlay loop muted>
        <source src={backDropVideo} type="video/mp4" />
      </video>
    </>
  );
}

export default App;
