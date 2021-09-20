import React, { useState, useContext } from "react";
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
import CompanyImage from "./CompanyImage";

function CompanyModel({ value = [] }) {
  console.log(value);
  // 1. Renders correct company based on back-end result
  // const [markets, setMarkets] = useState([]);

  // const [currentMarkets, setCurrentMarkets];
  // 2. Sets all starting values for company in their appropriate rendering]

  const auth = useContext(AuthContext);

  //  const getMarkets = () => {
  //    const init = {
  //      headers: {
  //        "Access-Control-Allow-Origin": "http://localhost:3000",
  //        Authorization: `Bearer ${auth.user.token}`,
  //      },
  //    };

  //    return fetch("http://localhost:8080/api/market", init)
  //      .then((response) => response.json())
  //      .then((data) => setMarkets(data))
  //      .catch((error) => console.log("Error", error));
  //  };

  //  const currentMarkets = (getMarkets) => {
  //    for (let i = 0; i < getMarkets.size(); i++) {
  //      if (
  //        !getMarkets[i].isBankrupt &&
  //        getMarkets[i].yearNumber === Game.lastYear
  //      ) {
  //        currentMarket.push(getMarkets[i]);
  //      }
  //    }
  //    return currentMarket;
  //  };
  // 3. Buttons have functionality that allow user to "place bet", adds to "portfolio"

  // Validation
  // User cannot purchase more stocks thatn are allowed (max 100)
  // User cannot buy more than they can afford

  // ToDo: add message for total cost of position based on #stocks * price

  const [open, setOpen] = useState(false);
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
                  <CompanyImage
                    fomrat="modal"
                    companyId={value.company?.companyId}
                  />
                </Modal.Content>
              </Grid.Column>
              <Modal.Content>
                <Modal.Description>
                  <Label wrapped size="big">
                    {value.map((market) => (
                      <>
                        <Header key={market.marketId}>{market.marketId}</Header>
                        {/* <p>Dividend per turn: ${market.company.dividend}</p>
                        <p>Current Price: ${market.price}</p>
                  
                        <p>Last Year's Price: </p>
                        <p>Available Stocks: #{100 - market.stockPurchasedTotal}</p> */}
                      </>
                    ))}
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
          <CompanyImage companyId={value.company?.companyId} />
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

export default CompanyModel;
