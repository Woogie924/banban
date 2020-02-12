import axios from 'axios'
import store from '@/vuex/store.js'
export default {
    setCartVO(cartVO, callback, errorCallback) {
        console.log("userCartService setCartVO start");
        console.log(cartVO);
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios({
                method: "post",
                url: `http://192.168.100.92:8080/api/cart/`,
                data: {
                    unum: cartVO.unum,
                    userid: cartVO.userid,
                    menuname: cartVO.menuName,
                    storeid: cartVO.storeid,
                    price: cartVO.price,
                    quantity: cartVO.quantity
                }
            }).then(response => {
                callback(response);
            })
            .catch(error => {
                errorCallback(error);
                console.log("UserCartService setCartVO error");
            });
    },
    getCartList(callback, errorCallback) {
        console.log("userCartService getCartList start");
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        axios
            .get("http://192.168.100.92:8080/api/cart")
            .then(response => {
                callback(response);
            })
            .catch(error => {
                errorCallback(error);
                console.log("UserCartService getCartList error");
            })
    }


}