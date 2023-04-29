import React, { useState } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import LoginPage from './LoginPage';
import App from './App';
import Temp from './temp';

const Main = (props) => {
  const temp = props.isLoggedIn;
  const [il , setil] = useState(false);
  const handleDataFromChild = (data)=>{
    console.log(data)
    setil(data);
  }
  console.log("props are " + props)
  return (
    <div>
      <Switch>
        <Route exact path="/">
          <Redirect to="/login" />
        </Route>
        <Route path="/login">
          <LoginPage onData = {handleDataFromChild}/>
        </Route>
        <Route path="/app">
          {console.log(props)}
          {il ? <Temp /> : <Redirect to="/login" />}
          {/* <App/> */}
        </Route>
      </Switch>
    </div>
  );
};

export default Main;
