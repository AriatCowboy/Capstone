import React, { useState, useContext, useEffect } from "react";
import { Button, Grid, Label, Header } from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import PortfolioModal from "../gamecomponents/PortfolioModal";
import QuitGame from "../gamecomponents/QuitGameModal";
import CompanyModal from "../gamecomponents/CompanyModal";

function Game() {
  const [game, setGame] = useState(null);
  const [markets, setMarkets] = useState([]);
  const auth = useContext(AuthContext);

  const getCurrentMarkets = (game) => {
    const currentMarkets = [];
    for (let i = 0; i < game.markets.length; i++) {
      if (
        !game.markets[i].isBankrupt &&
        game.markets[i].yearNumber === game.year
      ) {
        currentMarkets.push(game.markets[i]);
      }
    }
    return currentMarkets;
  };

  const getGame = () => {
    const init = {
      headers: {
        "Access-Control-Allow-Origin": "http://localhost:3000",
        Authorization: `Bearer ${auth.user.token}`,
      },
    };

    return fetch("http://localhost:8080/api/game", init)
      .then((response) => response.json())
      .then((data) => {
        setGame(data)
        const currentMarkets = getCurrentMarkets(data)
        setMarkets(currentMarkets)

      })
      .catch((error) => console.log("Error", error));
  };

  useEffect(getGame, [auth.user]);

  // Goes to next year - Add Market
  const handleNextYear = () => {
    let gameHold = game
    gameHold.markets = markets
    setGame(gameHold)

    const init = {
      method: "POST",
      headers: {
        "Access-Control-Allow-Origin": "http://localhost:3000",
        Authorization: `Bearer ${auth.user.token}`,
        "Content-Type": "application/json"
      },
      body: JSON.stringify(game)
    };

    fetch("http://localhost:8080/api/game", init)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        }
        return Promise.reject("Something else went wrong, sorry :)");
      })
      .then((data) => {
        setGame(data)
        const currentMarkets = getCurrentMarkets(data)
        setMarkets(currentMarkets)
      })
      .catch((error) => console.log("Error:", error));
  };

  // const update = (market) => {
  //   let currentMarkets = markets
  //   if (currentMarkets !== market){
  //     setMarkets(currentMarkets)
  //   }
  // };
  // useEffect(update, [auth.user]);

  // useEffect(update, [auth.user, markets]);

  //  1. Calls new game (GETMAPPING from GAME Controller)
  //  2. Sets random 10 companies to "company slots" in the grid using their id
  //  3. Portfolio starts with stating cash for the user
  //  4. Next year button adds (POSTMAPPING) a new market, updating the pricing of each company, increments Year++
  //  5. Score updates on each year (liquidity + holdings)
  //  6. Once game finishes, logs score into leaderboard table

  // Validation
  // User is valid
  // Checks the correct user is playing the game
  if (markets.length === 0) {
    return null;
  };

  return (
    <>
      <div>
        <h1 id="header">The Market</h1>
      </div>
      <br />
      <Grid textAlign="center">
        <Grid.Row>
          <Grid.Column width={3}>
            <CompanyModal value={markets[0]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[1]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[2]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[3]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[4]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={3}>
            <CompanyModal value={markets[5]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[6]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[7]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[8]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal value={markets[9]} /> 
            {/* update={update} id={0} m={markets}/> */}
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <br />
      <br />
      <br />
      <Grid columns={3}>
        <Grid.Column textAlign="center" width={8}>
          <Grid.Row>
            {game.year !== 10 ? (
              <>
                <PortfolioModal game={game} score={game.score} current={markets} />
                <Button onClick={handleNextYear} size="massive" color="yellow">
                  Next Year
                </Button>
                <QuitGame />
              </>
            ) : null}

          </Grid.Row>
        </Grid.Column>
        <Grid.Column textAlign="right" width={4}>
          <Grid.Row>
            <Label size="massive" width={9}>
              {/* increment by 1 when progressToNextYear is clicked*/}
              <Header>Year {game.year} / 10</Header>
            </Label>
          </Grid.Row>
        </Grid.Column>

        <Grid.Column textAlign="left" width={4}>
          <Grid.Row>
            <Label size="massive" width={9}>
              {/* set equal to liquidity + currentholdings*/}
              <Header>Score {game.score}</Header>
            </Label>
          </Grid.Row>
        </Grid.Column>
      </Grid>
    </>
  );
}

export default Game;
