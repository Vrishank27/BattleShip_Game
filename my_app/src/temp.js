import React, { useState } from 'react';
import './App.css';
import App from './App';

function Temp(){
    const [buttonPressed , setButtonPressed] = useState(false);

    const onClickHandler = () => {
        setButtonPressed(true);
    }

        return(
            <div>
            <div className='divCenter'>
            {!buttonPressed && <button onClick={onClickHandler} className='button-64'>Start Playing</button>}
            </div>
            <div>{buttonPressed && <App/>}</div>
            </div>
        )
    
}

export default Temp;