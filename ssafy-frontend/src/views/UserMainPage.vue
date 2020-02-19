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
  }
};
</script>

<style>
</style>