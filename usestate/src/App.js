import React, { useState } from "react";

function App() {

  const [user, setUser] = useState({
    name: "Poojitha",
    age: 20,
    city: "Hyderabad"
  });

  const increaseAge = () => {
    setUser({
      ...user,
      age: user.age + 1
    });
  };

  return (
    <div style={{textAlign:"center", marginTop:"50px"}}>
      <h1>User Profile</h1>

      <h2>Name: {user.name}</h2>
      <h2>Age: {user.age}</h2>
      <h2>City: {user.city}</h2>

      <button onClick={increaseAge}>
        Increase Age
      </button>
    </div>
  );
}

export default App;