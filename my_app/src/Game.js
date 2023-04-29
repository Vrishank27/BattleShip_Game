import React, { useState } from 'react';
import GameBoard from './Gameboard';

const Game = () => {
  const [board, setBoard] = useState(Array(7).fill().map(() => Array(7).fill(0)));

  const handleClick = (row, col) => {
    const newBoard = [...board];
    newBoard[row][col] = 1;
    setBoard(newBoard);
  };

  return (
    <>
      <h1>Battleship</h1>
      <GameBoard board={board} onClick={handleClick} />
    </>
  );
};

export default Game;
