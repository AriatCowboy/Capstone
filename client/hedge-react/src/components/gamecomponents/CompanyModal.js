import React from "react";
import {
  Button,
  Image,
  Modal,
  Header,
  Select,
  Tab,
  Label,
  Grid,
} from "semantic-ui-react";

import AuthContext from "../../AuthContext";
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

function ModalExampleContentImage() {
  // 1. Renders correct company based on back-end result
  // 2. Sets all starting values for company in their appropriate rendering]
  // 3. Buttons have functionality that allow user to "place bet", adds to "portfolio"

  // Validation
  // User cannot purchase more stocks thatn are allowed (max 100)
  // User cannot buy more than they can afford

  // ToDo: add message for total cost of position based on #stocks * price

  const [open, setOpen] = React.useState(false);
  const numberOfStocks = [
    { key: 10, value: 10, text: "10" },
    { key: 20, value: 20, text: "20" },
    { key: 30, value: 30, text: "30" },
    { key: 40, value: 40, text: "40" },
    { key: 50, value: 50, text: "50" },
    { key: 60, value: 60, text: "60" },
    { key: 70, value: 70, text: "70" },
    { key: 80, value: 80, text: "80" },
    { key: 90, value: 90, text: "90" },
    { key: 100, value: 100, text: "100" },
  ];

  const panes = [
    {
      menuItem: "Company Profile",
      render: () => (
        <Tab.Pane>
          <Grid columns={2}>
            <Grid.Row>
              <Grid.Column width={8}>
                <Modal.Content>
                  <Image size="large" src={C5} wrapped />
                </Modal.Content>
              </Grid.Column>
              <Modal.Content>
                <Modal.Description>
                  <Label wrapped size="big">
                    <Header>UXGallery</Header>
                    <p>Dividend per turn: $XX</p>
                    <p>Current Price: $XX</p>
                    <p>Last Year's Price: $XX</p>
                    <p>Available Stocks: #XX</p>
                  </Label>
                  <Header>Put a Position on UXGallery?</Header>
                  <div>
                    <Select
                      placeholder="# of Stocks"
                      options={numberOfStocks}
                    ></Select>
                    <Button color="green">Bet For</Button>
                    <Button color="red">Bet Against</Button>
                  </div>
                </Modal.Description>

                <Modal.Actions>
                  <br />
                  <Button onClick={() => setOpen(false)}>Cancel</Button>
                </Modal.Actions>
              </Modal.Content>
            </Grid.Row>
          </Grid>
        </Tab.Pane>
      ),
    },

    {
      menuItem: "Performance",
      render: () => <Tab.Pane></Tab.Pane>,
    },
  ];

  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={
        <Button inverted size="mini">
          <Image class="ui large image" src={C5}></Image>
        </Button>
      }
    >
      <Modal.Header>
        UXGallery
        <Tab panes={panes} />
      </Modal.Header>
    </Modal>
  );
}

export default ModalExampleContentImage;
