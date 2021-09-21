import React from "react";
import { Link } from "react-router-dom";
import { Button, Modal } from "semantic-ui-react";

function Reducer(state, action) {
  switch (action.type) {
    case "close":
      return { open: false };
    case "open":
      return { open: true, size: action.size };
    default:
      throw new Error("Unsupported action...");
  }
}

const QuitGame = () => {
  const [state, dispatch] = React.useReducer(Reducer, {
    open: false,
    size: undefined,
  });
  const { open, size } = state;

  return (
    <>
      <Button
        size="massive"
        color="red"
        onClick={() => dispatch({ type: "open", size: "mini" })}
      >
        Quit Game
      </Button>

      <Modal
        size={size}
        open={open}
        onClose={() => dispatch({ type: "close" })}
      >
        <Modal.Header>Quit Current Game</Modal.Header>
        <Modal.Content>
          <p>
            Are you sure you want to quit the game? (Your progress will be saved
            until you log-out)
          </p>
        </Modal.Content>
        <Modal.Actions>
          <Button negative onClick={() => dispatch({ type: "close" })}>
            No
          </Button>
          <Link to="/">
            <Button positive onClick={() => dispatch({ type: "close" })}>
              Yes
            </Button>
          </Link>
        </Modal.Actions>
      </Modal>
    </>
  );
};

export default QuitGame;
