import React from 'react';

const Cell = ({ value, onClick }) => {
  const handleClick = (event) => {
    event.preventDefault();
    onClick();
  };

  return (
    <div
      className={`cell ${value === 0 ? 'empty' : 'occupied'}`}
      onClick={handleClick}
      onContextMenu={handleClick}
    />
  );
};

export default Cell;
