import axios from 'axios'

const API_URL = 'http://localhost:8080/api/sayHello'

class HelloWorldService {

    getHello() {
        return axios.get(API_URL);
    }

}

export default new HelloWorldService()