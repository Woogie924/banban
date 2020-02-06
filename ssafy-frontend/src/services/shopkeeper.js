import axios from 'axios'
import store from '@/vuex/store.js'
export default {

    getOrderList(callback, errorCallback) {
        console.log("[shopkeeperAPI] getOrderList start");
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios.get("http://192.168.100.92:8080/orderstatus")
            .then(response => {
                callback(response);
            }).catch(error => {
                errorCallback();
                console.log("[shopKeeperAPI]getOrderList error!!!");
            })
    },
    getProfit(callback, errorCallback) {
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios.get("http://192.168.100.92:8080/shopkeeper/getprofit")
            .then(response => {
                callback(response);
            }).catch(error => {
                errorCallback();
                console.log("[shopKeeperAPI]getProfit error!!!")
            })
    }

}