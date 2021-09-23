import React from "react";
import { Form, Container, Button } from "semantic-ui-react";

import InfoNavbar from "../hiddencomponents/InfoNavBar";

function Contact() {
  function sendMail() {}

  return (
    <>
      <InfoNavbar />
      <Container>
        <h1 id="subheader">Contact Us</h1>
        <br />
        <Form method="post">
          <Form.Field>
            <div>
              <label>Your name</label>
              <br />
              <input type="text" id="Name" required />
            </div>
          </Form.Field>
          <br />
          <Form.Field>
            <div>
              <label>Your email address</label>
              <br />
              <input type="email" id="Email" required />
            </div>
          </Form.Field>
          <br />
          <Form.Field>
            <div>
              <label>Your message</label>
              <textarea
                id="Message"
                rows="6"
                maxlength="3000"
                required
              ></textarea>
            </div>
          </Form.Field>
          <div>
            <Button color="black" type="submit" onclick={sendMail()}>
              Send
            </Button>
          </div>
        </Form>
      </Container>
    </>
  );
}

export default Contact;
