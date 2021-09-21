import React, { useState, useContext } from "react";
import {
  Button,
  Modal,
  Header,
  Input,
  Tab,
  Label,
  Grid,
} from "semantic-ui-react";

import AuthContext from "../../AuthContext";
import CompanyImage from "./CompanyImage";
import LineChart from "./LineChart";

function CompanyModel({ value = [] }) {
  //Get companies in array
  console.log(value);

  const auth = useContext(AuthContext);

  const [stockAmount, setStockAmount] = useState(0);

  const handleStockAmountChange = (event) => {
    setStockAmount(event.target.valueAsNumber);
  };

  const placeBetFor = () => {
    value.stockPurchasedYear = stockAmount;
    return stockAmount;
  };

  const placeBetAgainst = () => {
    value.stockPurchasedYear = stockAmount;

    return stockAmount;
  };

  const [open, setOpen] = useState(false);

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
                    <p>Last Year's Price: ${value?.price}</p>
                    <p>Available Stocks: #{100 - value?.stockPurchasedTotal}</p>
                  </Label>
                  <Header>Put a Position on {value.company?.name}?</Header>
                  <div>
                    <Input
                      id="edit-stock-amount"
                      name="stockAmount"
                      value={stockAmount}
                      onChange={handleStockAmountChange}
                      type="number"
                    ></Input>

                    <Button onClick={placeBetFor} color="green">
                      Bet For
                    </Button>
                    <Button onClick={placeBetAgainst} color="red">
                      Bet Against
                    </Button>
                  </div>
                </Modal.Description>

                <Modal.Actions>
                  <br />
                  <Button onClick={() => setOpen(false)}>Return</Button>
                </Modal.Actions>
              </Modal.Content>
            </Grid.Row>
          </Grid>
        </Tab.Pane>
      ),
    },

    {
      menuItem: "Performance",
      render: () => (
        <Tab.Pane>
          <LineChart />
          <Modal.Actions>
            <br />
            <Button onClick={() => setOpen(false)}>Return</Button>
          </Modal.Actions>
        </Tab.Pane>
      ),
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
