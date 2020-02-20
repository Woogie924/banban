<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container fluid>
      <v-container class="justify-center">
        <v-layout wrap>
          <v-flex pa-2 xs12 sm2 lg2 md2>
            <Ranking></Ranking>
          </v-flex>
          <v-flex pa-2 xs12 sm8 lg8 md8>
            <Menu></Menu>
          </v-flex>
          <v-flex pa-2 xs12 sm2 lg2 md2>
            <OrderPossible></OrderPossible>
          </v-flex>
        </v-layout>
      </v-container>
    </v-container>
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
    this.connect();
  },
  name: "UserMainPage",
  components: {
    Ranking,
    Menu,
    UserNavBar,
    OrderPossible,
    Notification
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
          this.$store.commit("SOCKET_CONNECTED");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe(
            `/topic/push/${this.$store.state.userName}`,
            tick => {
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
    }
  }
};
</script>

<style>
</style>