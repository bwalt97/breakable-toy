import React from 'react'
import HelloWorldService from "../service/HelloWorldService";

class HelloWorldComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            text:''
        }
    }

    componentDidMount() {
        HelloWorldService.getHello().then((response) => {
            this.setState({text: response})
        });
    }

    render() {
        return(
            <div>
                <h1 className={"text-center"}>
                    {this.state.text.data}
                </h1>
            </div>
        )
    }

}

export default HelloWorldComponent