import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
const LoginPage = (props) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const history = useHistory();
  const handleLogin = (e) => {
    // TODO: Perform login logic here
    // If login is successful, set isLoggedIn to true
	const sendData = async() =>{
    const data = {
		username: username,
		password: password
	  };
    console.log(data);
	const response = await axios.post('http://localhost:8080/test/test4', data)
  console.log(response.data);
  if(response.data.id){
    alert("Logging in")
    setIsLoggedIn(true);
    console.log("this is logged in " + isLoggedIn)
    console.log("im here")
    console.log("Is logged in is " + isLoggedIn)
    props.onData(true);
    history.push('/app');
  }
  else{
    console.log(response)
    alert("error");
  }
	
    // Redirect to App component


  };
  
  sendData();



}

const handleRegister = async () => {
  // TODO: Perform register logic here
const data = {
  username: username,
  password: password
  };
  console.log(data);
const response  = await axios.post('http://localhost:8080/test/test3', data);
console.log("response is " + response.data);
window.location.reload();
};

if (isLoggedIn) {
  return null;
}

  return (
    <div>
      <h2>Login Page</h2>
      <form>
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="button" onClick={handleLogin}>
          Login
        </button>
        <button type="button" onClick={handleRegister}>
          Register
        </button>
      </form>
    </div>
  );

};
export default LoginPage;
