<template>
  <div>
    <!-- 점주전용 NavBar -->
    <v-layout my-5 absolute>
      <StoreNavBar></StoreNavBar>
    </v-layout>​
    <!-- StorePayment.vue + OrderList.vue -->

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
    <v-layout>
      <bottomNav></bottomNav>
    </v-layout>
  </div>
</template>


<script>
import StoreNavBar from "../components/StoreNavBar";
import StorePayment from "../components/StorePayment";
import OrderList from "../components/OrderList";
import StoreMenuList from "../components/StoreMenuList";
import MenuManagement from "../components/MenuManagement";
import bottomNav from "../components/bottomNav";
import store from "@/vuex/store.js";
import router from "@/router.js";

export default {
  name: "StoreMainPage",
  components: {
    StoreNavBar,
    StorePayment,
    OrderList,
    StoreMenuList,
    MenuManagement,
    bottomNav
  },
  mounted() {
    if (this.$store.state.userType !== 2) {
      alert("권한이 없습니다. 로그인해주세요");
      this.$router.push("/StoreLogin");
    }
  },
  methods: {},
  data() {
    return {};
  }
};
</script>
​
<style>
</style>