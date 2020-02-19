<template>
  <div>
    <v-layout my-5>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <v-container>
      <v-layout my-5 row wrap>
        <v-flex pa-2 xs12 sm6 md6 lg6>
          <StorePayment :totalprice="list[0].totalprice"></StorePayment>
        </v-flex>​
        <v-flex pa-2 xs12 sm6 md4 lg4>
          <OrderList :list="list"></OrderList>
        </v-flex>
      </v-layout>
    </v-container>
    <v-layout>
      <bottomNav></bottomNav>
    </v-layout>
    <!-- 알림상자 -->

    <!-- <transition name="slide" mode="out-in" appear>
      <notification :value="value"></notification>
    </transition>-->
  </div>
</template>


<script>
import StoreNavBar from "../components/StoreNavBar";
import store from "@/vuex/store.js";
import StorePayment from "../components/StorePayment";
import bottomNav from "../components/bottomNav";
import OrderList from "../components/OrderList";
import router from "@/router.js";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import shopkeeper from "../services/shopkeeper";
import { mdiFormatQuoteClose, mdiFormatQuoteOpen } from "@mdi/js";
export default {
  mounted() {
    if (this.$store.state.socket === null) {
      this.connect();
    }
    this.getOrderList();
  },
  name: "StoreMainPage",
  components: {
    StoreNavBar,
    StorePayment,
    OrderList,
    bottomNav
  },
  methods: {
    connect() {
      this.socket = new SockJS("http://192.168.100.92:8082/order");
      this.stompClient = Stomp.over(this.socket);
      const that = this;
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
              if (this.$store.state.socket === 1) {
                this.playSound();
              }
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
    },
    playSound() {
      var audio = new Audio(
        "http://chataholic2.homestead.com/files/Door-Doorbell.wav"
      );
      audio.play();
    },
    getOrderList() {
      shopkeeper.getOrderList(
        response => {
          console.log("shopkeeper getOrderList start");
          console.log(response);
          this.list = response.data;
          // for (let index = 0; index < response.data.length; index++) {
          //   this.list[index] = response.data[index];
          //   this.$set(this.list, index, response.data[index]);
          //   console.log(this.list[index]);
          // }
          // console.log("shopkeeper getOrderList " + this.list);
        },
        errorcallback => {
          console.log("shopkeeper error:" + errorcallback);
        }
      );
    }
  },
  data() {
    return {
      list: [],
      value: false,
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen
    };
  }
};
</script>

<style scoped>
.slide-fade-enter-active {
  transition: all 0.3s ease;
}
.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active below version 2.1.8 */ {
  transform: translateX(10px);
  opacity: 0;
}
</style>