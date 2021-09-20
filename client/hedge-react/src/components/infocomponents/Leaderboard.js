import InfoNavbar from "../hiddencomponents/InfoNavBar";
import { useState, useEffect, useContext } from "react";

import { Table } from "semantic-ui-react";
import AuthContext from "../../AuthContext";

function Leaderboard() {
  const [leaderboards, setLeaderboards] = useState([]);

  const auth = useContext(AuthContext);

  const getList = () => {
    const init = {
      headers: {
        "Access-Control-Allow-Origin": "http://localhost:3000",
        Authorization: `Bearer ${auth.user.token}`,
      },
    };

    return fetch("http://localhost:8080/api/leaderboard", init)
      .then((response) => response.json())
      .then((data) => setLeaderboards(data))
      .catch((error) => console.log("Error", error));
  };

  useEffect(getList, [auth.user]);

  return (
    <div>
      <InfoNavbar />
      <h1 id="subheader">Leaderboard</h1>
      <p id="leaderboard-paragraph">
        Currently showing the top 10 scores from users
      </p>
      <div id="leaderboard">
        <Table celled inverted compact size="large">
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>User</Table.HeaderCell>
              <Table.HeaderCell>Score</Table.HeaderCell>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {leaderboards.map((leaderboard) => (
              <Table.Row key={leaderboard.id}>
                <Table.Cell>{leaderboard.username}</Table.Cell>
                <Table.Cell>{"$" + leaderboard.score}</Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      </div>
    </div>
  );
}

export default Leaderboard;
