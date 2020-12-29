import axios from 'axios'

const FETCH_URL = 'http://localhost:8080/api/random'
const LIKE_URL = 'http://localhost:8080/api/like/'

class RandomMovieService {

    getRandomMovie() {
        return axios.get(FETCH_URL);
    }

    likeMovie(movieId) {
        return axios.patch(LIKE_URL + movieId);
    }

}

export default new RandomMovieService()
