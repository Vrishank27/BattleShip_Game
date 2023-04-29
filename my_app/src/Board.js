import React from "react";
import "./styles.css";

const SHIP_COLORS = {
  carrier: "purple",
  battleship: "blue",
  cruiser: "green",
  submarine: "yellow",
  destroyer: "red"
};

const Board = ({ board, handleClick }) => {
  const renderBoard = board.map((row, rowIndex) => (
    <div className="row" key={rowIndex}>
      {row.map((col, colIndex) => (
        <div
          className={`cell ${col}`}
          key={colIndex}
          onClick={() => handleClick(rowIndex, colIndex)}
        ></div>
      ))}
    </div>
  ));

  const renderShips = () => {
    const ships = Object.keys(SHIP_COLORS);
    return ships.map(ship => (
      <div className="ship-container" key={ship}>
        <div className="ship" style={{ backgroundColor: SHIP_COLORS[ship] }}></div>
        <div className="ship-label">{ship}</div>
      </div>
    ));
  };

  return (
    <div className="board-container">
      <div className="board">{renderBoard}</div>
      <div className="ships">{renderShips()}</div>
    </div>
  );
};

export default Board;
