<template>
  <div>
    <!-- 점주전용 NavBar -->
    <v-layout my-5 absolute>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <!-- StorePayment.vue + OrderList.vue -->

    <v-container>
      <v-layout mt-5 class="justify-center text-center">
        <div class="underlined justify-center text-center display-1">내 메뉴</div>
      </v-layout>
      <v-layout>
        <v-flex pa-2>
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

<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  position: relative;
  z-index: 1;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
}
.underlined::before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(to top, #b4e7f8 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>