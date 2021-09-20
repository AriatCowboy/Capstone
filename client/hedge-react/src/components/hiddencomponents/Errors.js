import React from "react";
import { Message } from "semantic-ui-react";

function Errors({ errors = [] }) {
  if (errors.length === 0) {
    return null;
  }

  return (
    <Message color="red" compact floating>
      <Message.Header>Errors...</Message.Header>
      {errors.map((error) => (
        <Message.List key={error}>{error}</Message.List>
      ))}
    </Message>
  );
}

export default Errors;
