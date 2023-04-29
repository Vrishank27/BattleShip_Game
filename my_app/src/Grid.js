import React, { useState } from 'react';
import './Grid.css';
import axios from 'axios';

axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080/test/test2';

axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080/test/result';

axios.defaults.headers.common['Access-Control-Allow-Origin'] = 'http://localhost:8080/test/resack';


function Grid(props) {
  const [clickedCellsTrue, setClickedCellsTrue] = useState([]);
  const [clickedCellsFalse, setClickedCellsFalse] = useState([]);
  const grid = props.grid;
  const setGrid = props.setGrid;
  const backendUpdated = props.backendUpdated;



    const updateCellColor = (rowIndex, colIndex, hasShip) => {
        const newGrid = [...grid]; // make a copy of the grid

        let color ;
        if(hasShip == true){
            color = "orange"
        }
        else{
            color = "black"
        }
        const ship = {
            color,
          };
         // set the color based on whether or not the cell has a ship
        newGrid[colIndex][rowIndex] = ship;
        setGrid(newGrid); // update the state of the component with the new grid
      };

  const handleClick = (x, y) => {
    if(!backendUpdated){
      alert("Place all your ships and submit 1st ")
      return;
    }
    axios.post('http://localhost:8080/test/test2', { x, y })
      .then(response => {
        console.log(typeof(response.data))
        const data = response.data;
        let truthVal ;
        for (const key in data) {
          if (data.hasOwnProperty(key)) {
            console.log(" x: " + key[0] + " y: " + key[1]);
            truthVal = response.data[key];
          }
          updateCellColor(key[0] , key[1] , grid[key[1]][key[0]] == null);
        }
        if (truthVal === true) {
          const newClickedCellsTrue = [...clickedCellsTrue, { x, y }];
          setClickedCellsTrue(newClickedCellsTrue);
        } else {
          const newClickedCellsFalse = [...clickedCellsFalse, { x, y }];
          setClickedCellsFalse(newClickedCellsFalse);
        }
      })
      .catch(error => {
        console.log(error);
      });
      const sendCoordinatesToBackend = async () => {
        const url = 'http://localhost:8080/test/result';
      
        try {
          const response = await axios.get(url);
          console.log(response.data);
          if(response.data == 1){
            let x = prompt("You have won");
            await axios.post('http://localhost:8080/test/resack');
            window.location.reload();
          }
          else if(response.data == 0){
            let x = prompt("You have lost to an AI ");
            await axios.post('http://localhost:8080/test/resack');
            window.location.reload();
          }
          else if(response.data == 3){
            alert("You have just lost a ship")
          }
          else if(response.data == 4){
            alert("You just destroyed an opponent's ship")
          }
        } catch (error) {
          console.error(error);
        }
      }

      sendCoordinatesToBackend();
  };


  return (
    <div className="grid-container">
      <table className="grid-table">
        <tbody>
          {Array.from({ length: 10 }).map((_, rowIndex) => (
            <tr key={rowIndex}>
              {Array.from({ length: 10 }).map((_, colIndex) => {
                const isClickedTrue = clickedCellsTrue.some(
                  (cell) => cell.x === colIndex && cell.y === rowIndex
                );
                const isClickedFalse = clickedCellsFalse.some(
                  (cell) => cell.x === colIndex && cell.y === rowIndex
                );
                let cellClassName = 'grid-cell';
                if (isClickedTrue) {
                  cellClassName += ' clicked-true';
                } else if (isClickedFalse) {
                  cellClassName += ' clicked-false';
                }
                return (
                  <td
                    key={colIndex}
                    onClick={() => handleClick(colIndex, rowIndex)}
                    className={cellClassName}
                  ></td>
                );
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Grid;


