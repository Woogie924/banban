<template>
  <div>
    <v-layout my-5>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <v-container>
      <v-layout my-5 row wrap>
        <v-flex pa-2 xs12 sm6 md6 lg6>
          <StorePayment></StorePayment>
        </v-flex>​
        <v-flex pa-2 xs12 sm6 md4 lg4>
          <OrderList></OrderList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>


<script>
import StoreNavBar from "../components/StoreNavBar";
import store from "@/vuex/store.js";
import StorePayment from "../components/StorePayment";
import OrderList from "../components/OrderList";
import router from "@/router.js";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  mounted() {
    if (this.$store.state.socket === null) {
      this.connect();
    }
  },
  name: "StoreMainPage",
  components: {
    StoreNavBar,
    StorePayment,
    OrderList
  },
  methods: {
    connect() {
      this.socket = new SockJS("http://192.168.100.92:8082/order");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          console.log("연결요");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe(
            `/topic/push/${this.$store.state.userName}`,
            tick => {
              this.$store.commit("ORDER_PLUS");
              this.$store.commit("SOCKET_CONNECTED");
              // console.log(JSON.parse(tick.body));
              // this.received_messages.push(JSON.parse(tick.body));
            }
          );
        },
        error => {
          console.log("에러요");
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      console.log("disconnected");
      this.stompClient.disconnect();
      this.connected = false;
      this.status = "disconnected";
      this.received_messages = [];
    }
  },
  data() {
    return {};
  }
};
</script>

<style>
</style>