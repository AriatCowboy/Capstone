import React, { useState, useContext } from "react";
import {
  Button,
  Modal,
  Header,
  Tab,
  Label,
  Grid,
} from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import CompanyImage from "./CompanyImage";

function CompanyModel({ value = [] }) {
  // , update, id, m }) {
  const [stockAmount, setStockAmount] = useState(0);

  // const [markets, setMarkets] = useState([]);

  // const [currentMarkets, setCurrentMarkets];

  const auth = useContext(AuthContext);

  // const updateList = (arr, changes) => {
  //   for(let k in changes) {
  //     arr[k] = changes[k];
  //   }
  // };


  const placeBetFor = () => {
    value.stockPurchasedYear = stockAmount
    value.longInvestment = true
    // updateList(m[id], value.isBankrupt, value.company, value.gameId, value.lastYearPrice, value.longInvestment, value.marketId, value.price, (value.stockPurchasedTotal + value.stockPurchasedYear), stockAmount, value.yearNumber)
    // console.log(m)
    // return update(m);
    return;
  };

  const placeBetAgainst = () => {
    value.stockPurchasedYear = stockAmount
    value.longInvestment = false
    // updateList(m[id], value.isBankrupt, value.company, value.gameId, value.lastYearPrice, value.longInvestment, value.marketId, value.price, (value.stockPurchasedTotal + value.stockPurchasedYear), stockAmount, value.yearNumber)
    // return update(m);
    return;
  };

  const [open, setOpen] = useState(false);

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
                    <p>Available Stocks: #{100 - value?.stockPurchasedTotal}</p>
                  </Label>
                  {value.yearNumber !== 10 ? (
                    <Header>Put a Position on {value.company?.name}?</Header>
                  ) : null
                  }
                  {value.stockPurchasedTotal === 0 && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        <Button onClick={placeBetFor} color="green">
                          Bet For
                        </Button>
                        <Button onClick={placeBetAgainst} color="red">
                          Bet Against
                        </Button>
                      </div>
                    ) : null
                  }
                  {value.stockPurchasedTotal !== 0 && value.longInvestment == true && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        <Button onClick={placeBetFor} color="green">
                          Bet For
                        </Button>
                      </div>
                    ) : null
                  }
                  {value.stockPurchasedTotal !== 0 && value.longInvestment == false && value.yearNumber !== 10 ?
                    (
                      <div>
                        <input id="edit-stock-amount" name="stockAmount" value={stockAmount} onChange={handleStockAmountChange} type="number"></input>
                        <Button onClick={placeBetAgainst} color="red">
                          Bet Against
                        </Button>
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
