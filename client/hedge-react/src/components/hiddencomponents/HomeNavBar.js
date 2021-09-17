import React from "react";
import { useContext } from "react";
import { Link } from "react-router-dom";

import AuthContext from "../../AuthContext";
import { Segment, Button } from "semantic-ui-react";

function HomeNavBar() {
  const auth = useContext(AuthContext);

  return (
    <Segment basic inverted vertical>
      <Link to="/game">
        <Button inverted color="green">
          <i class="play icon"></i>
          Play Game!
        </Button>
      </Link>

      <Link to="/info">
        <Button inverted>
          <i class="info circle icon"></i>
          Info
        </Button>
      </Link>

      <Link to="/settings">
        <Button inverted>
          <i class="cog icon"></i>
          Settings
        </Button>
      </Link>

      {auth.user && (
        <div id="logout">
          You are signed in as: {auth.user.username}
          <Button inverted onClick={() => auth.logout()}>
            Logout
          </Button>
        </div>
      )}

      {!auth.user && (
        <>
          <Link to="/login">
            <Button inverted>
              <i class="user icon"></i>
              Login
            </Button>
          </Link>

          <Link to="/register">
            <Button inverted color="yellow">
              <i class="user plus icon"></i>
              Register
            </Button>
          </Link>
        </>
      )}
    </Segment>
  );
}

export default HomeNavBar;
