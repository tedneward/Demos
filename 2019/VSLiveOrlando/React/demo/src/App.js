import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Joke, JokeUI} from './Joke';

function Hello(props) {
  return <div>Hello from {props.message}</div>
}

class Counter extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return null;
  }
}



class App extends React.Component {
  render() {
    let jokes = [new Joke()];
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <Hello message="from Ted!" language="en-us" />
          <JokeUI joke={jokes[0]} />
          <p>Hello React!</p>
          <Counter />
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
    }
}

export default App;


/*

function Item(props) {
  return <li>{props.message}</li>;
}

function TodoList() {
  const todos = ['finish doc', 'submit pr', 'nag dan to review'];
  return (
    <ul>
      { todos.length === 0 && There are no todos! You're done! Miller Time! }
      {todos.map((message) => <Item key={message} message={message} />)}
    </ul>
  );
}


 */