import axios from 'axios'
import store from '@/vuex/store.js'
export default {

    backendService(success, ereorCallback) {
        axios.defaults.headers.common[
            'Authorization'
        ] = `Bearer ${store.state.token}`
        axios.get('http://192.168.100.92:8080/notice/allboards')
            .then(function (response) {
                success(response.data);
            })
            .catch(function (error) {
                ereorCallback();
            })
    },
    getImage(myId, callback, ereorCallback) {
        console.log("management  getMyImage start");
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        console.log("management  mounted getMyImage gg" + myId);
        axios
            .get("http://192.168.100.92:8080/shopkeeper/menu/" + myId)
            .then(response => {
                console.log(response.data);
                callback(response);
                // console.log(response);
                // // console.log(response.data[0].img);
                // this.childId = response.data[0].id;
                // this.childName = response.data[0].name;
                // this.childCost = response.data[0].cost;
                // this.childTip = response.data[0].tip;
                // this.childImg = "192.168.100.92:8080/" + response.data[0].img;
                // console.log("이미지 경로:" + this.childImg);
            })
            .catch(error => {
                ereorCallback();
                console.log("management  mounted getMyImage error");
            });

    },
    getUserInfo(callback, errorCallback) {
        axios.defaults.headers.common[
            "Authorization"
        ] = `Bearer ${store.state.token}`;
        console.log("test.js getUserInfo start");
        axios
            .get("http://192.168.100.92:8080/shopkeeper/store")
            .then(response => {
                // console.log("response : " + response);
                // console.log("response (2) : " + response.data);

                callback(response);
                // this.userInfo = this.$store.state.user.id;
            })
            .catch(error => {
                errorCallback();
            });
    }
}