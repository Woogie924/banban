import axios from 'axios'
import store from '@/vuex/store.js'
export default {

    getStoreMenuList(storeId, callback, errorCallback) {
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
                errorCallback();
                console.log("UserOrder getStoreMenuList error");
            });

    },
    getStoreInfo(storeId, callback, errorCallback) {
        console.log("UserOrder getStoreInfo start")
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios
            .get("http://192.168.100.92:8080/api/storeinfo/" + storeId)
            .then(response => {
                // console.log(response.data);
                callback(response);

            })
            .catch(error => {
                errorCallback();
                console.log("UserOrder getStoreMenuList error");
            });
    },
    setReview(reviewVO, callback, errorCallback) {
        console.log("userOrder setReview start");
        console.log(reviewVO);
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios({
                method: "post",
                url: "http://192.168.100.92:8080/api/starpoint/",
                data: {
                    "starnum": reviewVO.rating,
                    "storeid": reviewVO.storeid,
                    "userid": reviewVO.userid,
                    "starpoint": reviewVO.starpoint,
                    "body": reviewVO.body + "",
                    "menu": reviewVO.menu,
                    "regDate": reviewVO.rating
                }
            }).then(response => {
                callback(response);
            })
            .catch(error => {
                errorCallback(error);
                console.log("UserOrder setReview error");
            });
    }
}