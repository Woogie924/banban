<template>
  <div>
    <!-- 점주전용 NavBar -->
    <v-layout my-5 absolute>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <!-- StorePayment.vue + OrderList.vue -->

    <v-container>
      <v-layout my-5>
        <v-flex pa-2 xs12 sm12 md12 lg12>
          <MyMenuList></MyMenuList>
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
import MyMenuList from "../components/MyMenuList";
import bottomNav from "../components/bottomNav";
import store from "@/vuex/store.js";
import router from "@/router.js";

export default {
  name: "StoreMenuPage",
  components: {
    StoreNavBar,
    MyMenuList,
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

<style>
</style>