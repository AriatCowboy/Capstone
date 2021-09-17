import { useState, useEffect, useContext } from "react";
import { Link, useHistory } from "react-router-dom";
import { Button, Divider, Segment, Grid, Image } from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import Errors from "../hiddencomponents/Errors";
import UXGallery from "../picturecomponents/UXGallery.png";

function Game() {
  return (
    <>
      <div>
        <h1 id="header">The Market</h1>
      </div>
      <br />
      <div id="company" class="ui five column grid container">
        <div class="row">
          <div class="column">
            <Image class="ui large image" src={UXGallery} />
          </div>
          <div class="column">
            <Image class="ui large image" src={UXGallery} />
          </div>
          <div class="column">
            <Image class="ui large image" src={UXGallery} />
          </div>
          <div class="column">
            <Image class="ui large image" src={UXGallery} />
          </div>
          <div class="column">
            <Image class="ui large image" src={UXGallery} />
          </div>
        </div>

        <div class="column">
          <Image class="ui large image" src={UXGallery} />
        </div>
        <div class="column">
          <Image class="ui large image" src={UXGallery} />
        </div>
        <div class="column">
          <Image class="ui large image" src={UXGallery} />
        </div>
        <div class="column">
          <Image class="ui large image" src={UXGallery} />
        </div>
        <div class="column">
          <Image class="ui large image" src={UXGallery} />
        </div>
      </div>
      <br />
      <Segment inverted>
        <Button size="massive" inverted color="green">
          Portfolio
        </Button>
        <Link to="/">
          <Button size="massive" inverted color="red">
            Quit Game
          </Button>
        </Link>
      </Segment>
    </>
  );
}

export default Game;
