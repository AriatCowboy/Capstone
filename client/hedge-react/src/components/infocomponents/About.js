import React from "react";
import { Container } from "semantic-ui-react";
import InfoNavbar from "../hiddencomponents/InfoNavBar";

function About() {
  return (
    <>
      <InfoNavbar />
      <h1 id="subheader">About</h1>
      <h3 id="subheader2">Disclaimer</h3>
      <Container>
      <p id="disclaimer">
        ALL TRADING (including and not limited to Bitcoin, Altcoin, Futures,
        stocks and options trading) involves substantial risk of loss and is not
        suitable for every investor. The valuation of trading products may
        fluctuate, and, as a result, you may lose more than your original
        investment. The highly leveraged nature of trading means that
        small market movements can have a great impact on your trading account
        and this can work against you. If the market moves against you, you may
        sustain a total loss greater than the amount you deposited into your
        account. You are responsible for all the risks and financial resources
        you use. You should not engage in trading unless you fully understand
        the nature of the transactions you are entering into and the extent of
        your exposure to loss. Trade only with risk capital, which means that
        you trade with money that, if lost, will not adversely impact your
        lifestyle and your ability to meet your financial obligations. Past
        performance is not necessarily indicative of future results. If you do
        not fully understand these risks you must seek independent advice from
        your financial advisor. 
        <br />
        <br />
        No matter how good you get at playing HEDGE,
        extreme caution should be taken when and if moving onto a real-world
        brokerage platform. The creators of HEDGE accept no responsibility for
        future losses or gains in the real world market, nor do they cite the
        information presented in their product(s) to be applicable in real world
        trading instruments or strategies.
        </p>
      </Container>
    </>
  );
}

export default About;
