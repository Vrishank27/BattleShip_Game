import React from 'react';
import Cell from './Cell';

const GameBoard = ({ board, onCellClick }) => {
  const renderRow = (row, rowIndex) => {
    return (
      <div key={rowIndex}>
        {row.map((value, colIndex) => (
          <Cell key={`${rowIndex}-${colIndex}`} value={value} onClick={() => onCellClick(rowIndex, colIndex)} />
        ))}
      </div>
    );
  };

  return (
    <div>
      {board.map(renderRow)}
    </div>
  );
};

export default GameBoard;
