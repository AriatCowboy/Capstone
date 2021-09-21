import React from "react";
import { Line } from "react-chartjs-2";

const data = {
  labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"],
  datasets: [
    {
      label: "Price",
      data: [12, 19, 3, 5, 2, 3, 6, 10, 14, 7],
      fill: false,
      backgroundColor: "rgb(255, 99, 132)",
      borderColor: "rgba(0, 0, 0, 0.9)",
    },
  ],
};

const options = {
  scales: {
    yAxes: [
      {
        ticks: {
          beginAtZero: true,
        },
      },
    ],
  },
};

const LineChart = () => (
  <>
    <div className="header">
      <h1 className="title">Yearly Performance</h1>
    </div>
    <Line data={data} options={options} />
  </>
);

export default LineChart;
