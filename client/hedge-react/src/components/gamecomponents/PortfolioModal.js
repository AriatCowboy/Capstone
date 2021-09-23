import React, { useContext, useState, useEffect } from "react";
import { Button, Header, Image, Modal, Table } from "semantic-ui-react";

import AuthContext from "../../AuthContext";

function PortfolioModal({ score, current }) {
  const [open, setOpen] = useState(false);
  const [graphdata, setGraphData] = useState([]);


  const auth = useContext(AuthContext);
  const sellAllStocks = () => {
    return;
  };
  const getStockValue = () => {
    let stockValue = 0
    for (let i = 0; i < current.length; i++) {
      stockValue = stockValue + (current[i].price * current[i].stockPurchasedTotal)
    }
    return stockValue;
  }

   const getMarkets = () => {
     const init = {
       headers: {
         "Access-Control-Allow-Origin": "http://localhost:3000",
         Authorization: `Bearer ${auth.user.token}`,
       },
     };

    fetch(`http://localhost:8080/api/graphdata`, init)
       .then((response) => response.json())
       .then((data) => setGraphData(data))
       .catch((error) => console.log("Error", error));
   };

   useEffect(getMarkets, [auth.user, score, current]);


  return (
    <Modal
      onClose={() => setOpen(false)}
      onOpen={() => setOpen(true)}
      open={open}
      trigger={
        <Button size="massive" color="green">
          Portfolio
        </Button>
      }
    >
      <Modal.Header>Username's Portfolio</Modal.Header>
      <Modal.Content image>
        <Image
          size="medium"
          src="https://react.semantic-ui.com/images/avatar/large/rachel.png"
          wrapped
        />
        <Modal.Description>
          <Header>Current Liquidity: $ {score}</Header>
          <Header>Current Holdings Value: $ {getStockValue()}</Header>
          <p>Your Current Positions:</p>
          <div>
            <Table>
              <Table.Header>
                <Table.Row>
                  <Table.HeaderCell>Company</Table.HeaderCell>
                  <Table.HeaderCell># Stocks</Table.HeaderCell>
                  <Table.HeaderCell>Bought At</Table.HeaderCell>
                  <Table.HeaderCell>Current Value</Table.HeaderCell>
                  <Table.HeaderCell>Action</Table.HeaderCell>
                </Table.Row>
              </Table.Header>
              <Table.Body>
                {graphdata.map((gd) => (
                  <Table.Row key={gd.graphDataId}>
                    <Table.Cell>{gd.companyId}</Table.Cell>
                    <Table.Cell>{gd.amountPurchased}</Table.Cell>
                    <Table.Cell>{gd.purchasedPrice}</Table.Cell>
                    <Table.Cell>{gd.currentPrice}</Table.Cell>
                    <Table.Cell>
                      <Button onClick={sellAllStocks} color="green">
                        Sell
                      </Button>
                    </Table.Cell>
                  </Table.Row>
                ))}
              </Table.Body>
            </Table>
          </div>
        </Modal.Description>
      </Modal.Content>
      <Modal.Actions>
        <Button
          content="Return to Market"
          labelPosition="right"
          icon="checkmark"
          onClick={() => setOpen(false)}
        />
      </Modal.Actions>
    </Modal>
  );
}

export default PortfolioModal;
