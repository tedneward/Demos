import React from 'react';

export class Joke {
  constructor(setup = "Setup", punchline = "Punchline", lols = 0, groans = 0) {
    this.setup = setup;
    this.punchline = punchline;
    this.groans = groans;
    this.lols = lols;
  }
}

export class JokeUI extends React.Component {
  render() {
    return <div>{this.props.joke.punchline}</div>
  }
}