import React from "react";
import { Button, Header, Image, Modal, Table } from "semantic-ui-react";

function PortfolioModal() {
  // 1. Shows current holdings, #stocks, bought at price, and current value per position
  // 2. Buttons have functionality that allows user to sell that position, or "bet" more on that position
  // 3. Buttons have functionality that allow user to "place bet", adds to "portfolio"
  // 4. Updates current liquidity and current holdings sections

  // ToDo: add Tab for perfromance of portfolio

  const [open, setOpen] = React.useState(false);

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
          <Header>Current Liquidity: $XXX,XXX</Header>
          <Header>Current Holdings Value: $XXX,XXX</Header>
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
                <Table.Row>
                  <Table.Cell>UXGallery</Table.Cell>
                  <Table.Cell>100</Table.Cell>
                  <Table.Cell>$50</Table.Cell>
                  <Table.Cell>+$500</Table.Cell>
                  <Table.Cell>
                    <Button color="black">Buy</Button>
                    <Button color="green">Sell</Button>
                  </Table.Cell>
                </Table.Row>
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
