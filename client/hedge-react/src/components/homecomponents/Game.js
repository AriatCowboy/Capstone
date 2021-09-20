import React from "react";
import { Button, Grid, Label, Icon, Header } from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import PortfolioModal from "../gamecomponents/PortfolioModal";
import QuitGame from "../gamecomponents/QuitGameModal";
import Errors from "../hiddencomponents/Errors";
import CompanyModal from "../gamecomponents/CompanyModal";

import C1 from "../picturecomponents/Acedemiic.png";
import C2 from "../picturecomponents/HealthHarbor.png";
import C3 from "../picturecomponents/LeisureMachine.png";
import C4 from "../picturecomponents/SocialCells.png";
import C5 from "../picturecomponents/UXGallery.png";
import C6 from "../picturecomponents/BrightMiner.png";
import C7 from "../picturecomponents/Digitalic.png";
import C8 from "../picturecomponents/Earthzy.png";
import C9 from "../picturecomponents/EcoAlley.png";
import C10 from "../picturecomponents/Modaxy.png";
import C11 from "../picturecomponents/Tourux.png";
import C12 from "../picturecomponents/CargoAcer.png";
import C13 from "../picturecomponents/PowerLy.png";
import C14 from "../picturecomponents/RivalIndustry.png";
import C15 from "../picturecomponents/SupplyPhase.png";
import C16 from "../picturecomponents/UltraFoundry.png";
import C17 from "../picturecomponents/Agribuilder.png";
import C18 from "../picturecomponents/CoalStove.png";
import C19 from "../picturecomponents/PetroYield.png";
import C20 from "../picturecomponents/ProsperityFuel.png";
import C21 from "../picturecomponents/TimberFly.png";
import C22 from "../picturecomponents/Cammunition.png";
import C23 from "../picturecomponents/Grandlytics.png";
import C24 from "../picturecomponents/Pharmanetic.png";
import C25 from "../picturecomponents/Protectice.png";
import C26 from "../picturecomponents/SimplySentient.png";

function Game() {
  //  1. Calls new game (GETMAPPING from GAME Controller)
  //  2. Sets random 10 companies to "company slots" in the grid using their id
  //  3. Portfolio starts with stating cash for the user
  //  4. Next year button adds (POSTMAPPING) a new market, updating the pricing of each company, increments Year++
  //  5. Score updates on each year (liquidity + holdings)
  //  6. Once game finishes, logs score into leaderboard table

  // Validation
  // User is valid
  // Checks the correct user is playing the game

  return (
    <>
      <div>
        <h1 id="header">The Market</h1>
      </div>
      <br />
      <Grid textAlign="center">
        <Grid.Row>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
        </Grid.Row>

        <Grid.Row>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
          <Grid.Column width={3}>
            <CompanyModal />
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <br />
      <br />
      <br />

      <Grid columns={3}>
        <Grid.Column textAlign="center" width={8}>
          <Grid.Row>
            <PortfolioModal />
            <Button size="massive" color="yellow">
              Next Year
            </Button>
            <QuitGame />
          </Grid.Row>
        </Grid.Column>

        <Grid.Column textAlign="right" width={4}>
          <Grid.Row>
            <Label size="massive" width={9}>
              <Header>Year 1 / 10</Header>
            </Label>
          </Grid.Row>
        </Grid.Column>

        <Grid.Column textAlign="left" width={4}>
          <Grid.Row>
            <Label size="massive" width={9}>
              <Header>Score $XXX,XXX</Header>
            </Label>
          </Grid.Row>
        </Grid.Column>
      </Grid>
    </>
  );
}

export default Game;
