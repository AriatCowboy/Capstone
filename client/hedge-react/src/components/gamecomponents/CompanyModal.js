import React, { useState, useContext, useEffect } from "react";
import {
  Button,
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
  const [stockAmount, setStockAmount] = useState(0);

  // const [markets, setMarkets] = useState([]);

  // const [currentMarkets, setCurrentMarkets];

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

  const placeBetFor = () => {
    value.stockPurchasedYear = stockAmount
    value.longInvestment = true
    setOpen(false)
    return;
  };

  const placeBetAgainst = () => {
    value.stockPurchasedYear = stockAmount
    value.longInvestment = false
    setOpen(false)
    return;
  };

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

  const handleStockAmountChange = (event) => {
    setStockAmount(event.target.valueAsNumber);
  };


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
                    format="modal"
                    companyId={value.company?.companyId}
                  />
                </Modal.Content>
              </Grid.Column>
              <Modal.Content>
                <Modal.Description>
                  <Label wrapped size="big">
                    <Header key={value.company?.companyId}>
                      {value.company?.name}
                    </Header>
                    <p>Dividend per turn: ${value.company?.dividend}</p>
                    <p>Current Price: ${value?.price}</p>
                    {/* Update this to present correct last year price */}
                    <p>Last Year's Price: ${value?.lastYearPrice}</p>
                    <p>Available Stocks: #{100 - value?.stockPurchasedTotal - stockAmount}</p>
                  </Label>
                  {value.yearNumber !== 10 ? (
                    <Header>Put a Position on {value.company?.name}?</Header>
                  ) : null
                  }
                  {value.stockPurchasedTotal === 0 && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        {stockAmount >= 0 ?
                          (
                            <>
                              <Button onClick={placeBetFor} color="green">
                                Bet For
                              </Button>
                              <Button onClick={placeBetAgainst} color="red">
                                Bet Against
                              </Button>
                            </>
                          ) :
                          <>
                            <Button onClick={placeBetFor} color="green">
                              Sell For
                            </Button>
                            <Button onClick={placeBetAgainst} color="red">
                              Sell Against
                            </Button>
                          </>
                        }

                      </div>
                    ) : null
                  }
                  {value.stockPurchasedTotal !== 0 && value.longInvestment == true && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        {stockAmount >= 0 ?
                          (
                            <Button onClick={placeBetFor} color="green">
                              Bet For
                            </Button>
                          ) :
                          <Button onClick={placeBetFor} color="green">
                            Sell For
                          </Button>
                        }
                      </div>
                    ) : null
                  }
                  {value.stockPurchasedTotal !== 0 && value.longInvestment == false && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        {stockAmount >= 0 ?
                          (
                            <Button onClick={placeBetAgainst} color="red">
                              Bet Against
                            </Button>
                          ) :
                          <Button onClick={placeBetAgainst} color="red">
                            Sell Against
                          </Button>
                        }

                      </div>
                    ) : null
                  }
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
        {value.company?.name}
        <br />
        <br />
        <Tab panes={panes} />
      </Modal.Header>
    </Modal>
  );
}

export default CompanyModel;
