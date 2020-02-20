<template>
  <div>
    <v-layout my-5>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <v-container>
      <v-layout my-5 row wrap>
        <v-flex pa-2 xs12 sm6 md6 lg6>
          <StorePayment :totalprice="totalprice"></StorePayment>
        </v-flex>​
        <v-flex pa-2 xs12 sm6 md4 lg4>
          <OrderList :list="list" @child-userid="parents"></OrderList>
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
  data() {
    return {
      list: [],
      value: false,
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen,
      received_messages: [],
      orderNumber: 0,
      totalprice: 0
    };
  },
  methods: {
    parents(childUserId) {
      console.log("ggg");
      console.log(childUserId);
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        let msg = {
          message: this.send_message,
          sender: this.$store.state.userName,
          receiver: childUserId,
          data: null
        };
        console.log(JSON.stringify(msg));
        this.stompClient.send("/push", JSON.stringify(msg), {});
      }
    },
    async connect() {
      this.socket = new SockJS("http://54.180.163.74:8082/order");
      this.stompClient = Stomp.over(this.socket);
      const that = this;
      await this.stompClient.connect(
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
              console.log("겟오더리스트");
              that.getOrderList();
              if (this.$store.state.socket === 1) {
                this.playSound();
              }
              console.log(JSON.parse(tick.body));
              this.received_messages.push(JSON.parse(tick.body));
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
      // await this.getOrderList();
      // console.log("제발");
      // console.log(this.list);
    },
    async getOrderList() {
      await shopkeeper.getOrderList(
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
          if (!isNaN(this.list[0].totalprice)) {
            this.totalprice = this.list[0].totalprice;
          }
        },
        errorcallback => {
          console.log("shopkeeper error:" + errorcallback);
        }
      );
    }
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