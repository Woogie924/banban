import axios from 'axios'
export default {

    backendService(success, ereorCallback) {
        axios.get('http://192.168.100.92:8080/notice/allboards')
            .then(function (response) {
                success(response.data);
            })
            .catch(function (error) {
                ereorCallback();
            })
    }

}