import React from 'react';
import './App.css';
import HelloWorldComponent from "./components/HelloWorldComponent";
import RandomMovieComponent from "./components/RandomMovieComponent";

function App() {
  return (
    <div className="App">
      <RandomMovieComponent/>
    </div>
  );
}

export default App;
