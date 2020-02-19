<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-layout my-5>
      <v-container fluid>
        <v-layout wrap>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <Ranking></Ranking>
          </v-flex>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <Menu></Menu>
          </v-flex>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <OrderPossible></OrderPossible>
          </v-flex>
        </v-layout>
      </v-container>
    </v-layout>
    <v-layout>
      <transition>
        <Notification :value="true"></Notification>
      </transition>
    </v-layout>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import Ranking from "../components/Ranking";
import Menu from "../components/Menu";
import OrderPossible from "../components/OrderPossible";
import store from "@/vuex/store.js";
import router from "@/router.js";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import Notification from "../components/Notification";

export default {
  mounted() {
    if (this.$store.state.userType !== 1) {
      alert("권한이 없습니다. 로그인해주세요");
      this.$router.push("/Mlogin");
    }
    if (this.$store.state.socket === null) {
      this.connect();
    }
  },
  name: "UserMainPage",
  components: {
    Ranking,
    Menu,
    UserNavBar,
    OrderPossible
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
    }
  }
};
</script>

<style>
</style>