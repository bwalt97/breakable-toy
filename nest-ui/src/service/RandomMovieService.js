import axios from 'axios'

const API_URL = 'http://localhost:8080/api/random'

class RandomMovieService {

    getRandomMovie() {
        return axios.get(API_URL);
    }

}

export default new RandomMovieService()