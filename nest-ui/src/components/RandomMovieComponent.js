import React from 'react'
import RandomMovieService from "../service/RandomMovieService";

class RandomMovieComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            movie: null
        }
    }

    componentDidMount() {
        RandomMovieService.getRandomMovie().then((response) => {
            this.setState({movie: response})
        });
    }

    render() {
        return(
            <div>
                <h1 className={"text-center"}>
                    {this.state.movie.data}
                </h1>
            </div>
        )
    }

}

export default RandomMovieComponent
