import React from "react";
import { Link } from "react-router-dom";
import { Segment } from "semantic-ui-react";

function InfoNavbar() {
  return (
    <Segment inverted vertical>
      <Link id="info-menu" to="/info/how_to_play">
        How To Play
      </Link>

      <Link id="info-menu" to="/info/about">
        About
      </Link>

      <Link id="info-menu" to="/info/contact">
        Contact
      </Link>

      <Link id="info-menu" to="/info/leaderboard">
        Leaderboard
      </Link>
    </Segment>
  );
}

export default InfoNavbar;
