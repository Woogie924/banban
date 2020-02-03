import axios from 'axios'
import store from '@/vuex/store.js'
export default {

    backendService(success, ereorCallback) {
        axios.defaults.headers.common[
            'Authorization'
        ] = `Bearer ${store.state.user}`
        axios.get('http://192.168.100.92:8080/notice/allboards')
            .then(function (response) {
                success(response.data);
            })
            .catch(function (error) {
                ereorCallback();
            })
    }

}