import React from "react";
import { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";

import AuthContext from "../../AuthContext";
import { Segment, Grid, Button } from "semantic-ui-react";

function HomeNavBar() {
  const auth = useContext(AuthContext);

  return (
    <Segment basic inverted vertical>
      <Grid columns={3}>
        <Grid.Column textAlign="left" width={4}>
          <Link to="/">
            <Button inverted>
              <i class="home icon"></i>
              Home
            </Button>
          </Link>
        </Grid.Column>

        <Grid.Column textAlign="center" width={8}>
          {auth.user && (
            <Link to="/game">
              <Button color="green">
                <i class="play icon"></i>
                Play Game!
              </Button>
            </Link>
          )}

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
        </Grid.Column>

        <Grid.Column textAlign="right" width={4}>
          {auth.user && (
            <>
              <Button inverted onClick={() => auth.logout()}>
                Logout: {auth.user.username}
              </Button>
            </>
          )}
        </Grid.Column>
      </Grid>
    </Segment>
  );
}

export default HomeNavBar;
