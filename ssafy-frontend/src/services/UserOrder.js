import axios from 'axios'
import store from '@/vuex/store.js'
export default {

    getStoreMenuList(storeId, callback, ereorCallback) {
        console.log("UserOrder  getStoreMenuList start");
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        // console.log("management  mounted getMyImage gg" + myId);
        axios
            .get("http://192.168.100.92:8080/api/menu/" + storeId)
            .then(response => {
                // console.log(response.data);
                callback(response);

            })
            .catch(error => {
                ereorCallback();
                console.log("UserOrder getStoreMenuList error");
            });

    }
}