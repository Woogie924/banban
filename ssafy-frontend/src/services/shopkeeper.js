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
    },
    getMyStoreInfo(callback, errorCallback) {
        console.log("[shopkeeper API] getMyStoreInfo")
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios.get("http://192.168.100.92:8080/shopkeeper/storeinfo")
            .then(response => {
                callback(response);
            }).catch(error => {
                errorCallback(error);
                console.log("[shopKeeperAPI]getMyStoreInfo error!!!")
            })
    },
    setMyStoreInfo(storeInfoVO, callback, errorCallback) {
        console.log("[shopkeeper API] setMyStoreInfo");
        console.log(storeInfoVO)
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios({
                method: "post",
                url: "http://192.168.100.92:8080/shopkeeper/storeinfo",
                data: {
                    "snum": storeInfoVO.snum,
                    "storeid": storeInfoVO.storeid,
                    "minprice": storeInfoVO.minprice,
                    "deliverytip": storeInfoVO.deliverytip,
                    "deliverytime": storeInfoVO.deliverytime,
                    "storeintro": storeInfoVO.storeintro
                }
            }).then(async response => {
                await callback(response);
            })
            .catch(error => {
                errorCallback(error);
                console.log("shopkeeper setComment error");
            });
    },
    setComment(item, item2, callback, errorCallback) {
        console.log("[shopkeeper API] setComment start");
        console.log(item);
        console.log(item2);
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios({
                method: "post",
                url: "http://192.168.100.92:8080/shopkeeper/storestarpoint",
                data: {
                    "body": item2,
                    "regDate": item.regDate,
                    "starnum": item.starnum,
                    "storeid": item.storeid,
                    "storenum": 1
                }
            }).then(response => {
                callback(response);
            })
            .catch(error => {
                errorCallback(error);
                console.log("shopkeeper setComment error");
            });
    }

}