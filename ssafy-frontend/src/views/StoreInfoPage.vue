<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container fluid>
      <v-layout class="my-5 justify-center">
        <v-flex xs12 sm3 md3 lg3 class="pb-2">
          <StoreInfo></StoreInfo>
        </v-flex>
      </v-layout>
    </v-container>
    <!-- Store Info Component -->
    <v-container fluid>
      <v-layout wrap class="justify-center">
        <v-flex v-for="(item,i) in list" :key="i" xs12 sm8 md8 lg8 class="ma-0 pa-0">
          <deliveryMenuInfo
            :imageUrl="'http://192.168.100.92:8080/image/'+item.img"
            :name="item.name"
            :cost="item.cost"
            :tip="item.tip"
            :tag="item.tag"
          ></deliveryMenuInfo>
        </v-flex>
      </v-layout>
    </v-container>
    <v-btn
      id="basketBtn"
      fixed
      dark
      fab
      bottom
      right
      color="teal lighten-3"
      @click="moveMyCartListPage()"
    >
      <!-- 장바구니 담은게 있으면 -->
      <v-badge color="red" :content="alertCount">
        <v-icon>{{icons.mdiCart }}</v-icon>
      </v-badge>
      <!-- 장바구니 담은게 없으면,, -->
    </v-btn>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import deliveryMenuInfo from "../components/deliveryMenuInfo";
import UserOrder from "../services/UserOrder";
import { mdiCart } from "@mdi/js";
import UserCartService from "../services/UserCartService";
import StoreInfo from "../components/StoreInfo";

export default {
  name: "StoreInfoPage",
  components: {
    UserNavBar,
    deliveryMenuInfo,
    StoreInfo
  },
  data() {
    return {
      storeId: "asia924",
      list: [],
      icons: {
        mdiCart
      },
      alertCount: 5,
      hover: false
    };
  },

  mounted() {
    this.getMenuList();
    this.getCartList();
    console.log("mounted ");
    console.log(this.list);
  },

  methods: {
    async getMenuList() {
      // console.log("getMyImage start" + this.myId);
      await UserOrder.getStoreMenuList(
        this.storeId,
        response => {
          console.log("[USER ORDER API]deliveryInfo Start:");
          console.log(response);
          this.list = response.data;
          //   for (let index = 0; index < response.data.length; index++) {
          //     // console.log(response.data[index]);
          //     this.list[index] = response.data[index];
          //   }
        },
        errorcallback => {
          console.log("error:" + errorcallback);
        }
      );
    },

    moveMyCartListPage() {
      this.$router.push({
        name: "MyCartPage"
      });
    },
    getCartList() {
      UserCartService.getCartList(response => {
        this.alertCount = response.data.length;
      });
    }
  }
};
</script>

<style>
</style>