import React, { useState } from 'react';
import './App.css';
import axios from 'axios';
import Grid from './Grid';
import cors from 'cors';


const GRID_SIZE = 10;

const ships = [
  { length: 2, name: 'Destroyer' , color : "red"},
  { length: 3, name: 'Submarine'  , color : "blue"},
  { length: 3, name: 'Cruiser'  , color : "green"},
  { length: 4, name: 'Battleship'  , color : "yellow"},
  { length: 5, name: 'Carrier'  , color : "pink"},
];

function App() {
  const [grid, setGrid] = useState(Array(GRID_SIZE).fill().map(() => Array(GRID_SIZE).fill(null)));
  const [selectedShip, setSelectedShip] = useState(null);
  const [placedShips, setPlacedShips] = useState([]);
  const [disabledShips, setDisabledShips] = useState([]);
  const [backendUpdated, setBackendUpdated] = useState(false);

  const placeShip = (row, col, direction, newShip) => {
  
    let name = 'name';
    let id = 0;
    if(newShip.name == "Cruiser"){
      id = 0;
    }
    else if(newShip.name == "Carrier"){
      id = 1;
    }
    else if(newShip.name == "Destroyer"){
      id = 2;
    }
    else if(newShip.name == "Battleship"){
      id = 3;
    }
    else if(newShip.name == "Submarine"){
      id = 4;
    }
    const length = newShip.length;
    const color = newShip.color;
    const ship = {
      row,
      col,
      direction,
      color,
      id
    };

    for (let i = 0; i < length; i++) {
      let r = row;
      let c = col;
      if (direction == '0') {
        r += i;
      } else {
        c += i;
      }
      console.log(r , c);
      if (r >= GRID_SIZE || c >= GRID_SIZE || grid[r][c] !== null || r<0 || c<0) {
        alert("Cannot place");
        console.log(r + " and " + c)
        return false;
      }
    }

    for (let i = 0; i < length; i++) {
      let r = row;
      let c = col;
      if (direction == '0') {
        r += i;
      } else {
        c += i;
      }
      grid[r][c] = ship;
    }

    
    setGrid([...grid]);
    setPlacedShips([...placedShips, ship]);
    return true;
  };

  const sendCoordinatesToBackend = async () => {
    if(disabledShips.length != 5){
      alert("You need to finish placing all your ships 1st");
      return;
    }
    setBackendUpdated(true);
    const url = 'http://localhost:8080/test/test1';
    console.log(placedShips)
    const coordinates = placedShips.map(ship => {
      return {
        row: ship.row,
        col: ship.col,
        length: ship.length,
        direction: ship.direction,
        color: ship.color,
        id: ship.id
      };
    });
    
    try {
      console.log(coordinates)
      const response = await axios.post(url, coordinates);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  

  const handleClick = (row, col, e) => {
    if (selectedShip === null) {
      return;
    }

    e.preventDefault();
    const dir = (prompt("enter 0 for horizontal and 1 for vertical "));
    if(selectedShip !== null) 
    {
      if(placeShip(row, col, dir, selectedShip)){
        setSelectedShip(null);
      }
      else{
         const ind = disabledShips.indexOf(selectedShip.name);
         if(ind>-1){
          disabledShips.splice(ind ,1)
         }
         setSelectedShip(null);
      }
    }
    
  };

  const handleShipClick = (shipToCheck) => {
    
    if(selectedShip != null){
      alert("Finish placing the other ship 1st")
      return;
    }
    console.log(shipToCheck);
    console.log(disabledShips);
    console.log(disabledShips.includes(shipToCheck.name))
    if(!disabledShips.includes(shipToCheck.name)){
      setDisabledShips([...disabledShips, shipToCheck.name]);
      setSelectedShip(shipToCheck);
      alert("OKAY!")
    }
    else {
      alert("SHIP DISABLED!");
      setSelectedShip(null);
    }

  };
  


  

  const renderGrid = () => {
    return (
      <div className="grid-container">
        {grid.map((row, rowIndex) => (
          <div className="row" key={`row-${rowIndex}`}>
            {row.map((cell, colIndex) => (
              <div
                key={`${rowIndex}-${colIndex}`}
                className="cell"
                style={{ backgroundColor: cell ? cell.color : '#fff' }}
                onClick={(e) => handleClick(rowIndex, colIndex, e)}
              />
            ))}
          </div>
        ))}
      </div>
    );
  };
  

  const renderShips = () => {
    const ships = [
      { length: 2, name: 'Destroyer' , color : "red"},
      { length: 3, name: 'Submarine'  , color : "blue"},
      { length: 3, name: 'Cruiser'  , color : "green"},
      { length: 4, name: 'Battleship'  , color : "yellow"},
      { length: 5, name: 'Carrier'  , color : "pink"},
    ];

    return (
      <div className="ships">
        <h2>Ships</h2>
        {ships.map((ship) => {
          const disabled = disabledShips.some(disabledShip => disabledShip.name === ship.name);
          return (
            <div
            key={ship.name}
            className={`ship ${selectedShip === ship ? 'selected' : ''} ${disabled ? 'disabled' : ''}`}
            onClick={() => handleShipClick(ship)}
            style={{ backgroundColor: ship.color }}
          >
            {ship.name} ({ship.length})
            
          </div>
          
        );
      })}
      <button onClick={sendCoordinatesToBackend} className='button-62'>Start Playing</button>
    </div>
  );
};

return (
<div className="App">
{renderGrid()}
{renderShips()}
<Grid 
  grid={grid}
  setGrid={setGrid}
  backendUpdated = {backendUpdated}
  />


</div>
);
}

export default App;