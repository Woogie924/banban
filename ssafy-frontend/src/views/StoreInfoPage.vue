<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container fluid class="mb-0 pb-0">
      <v-layout class="mt-5 justify-center">
        <v-flex xs12 sm6 md6 lg3>
          <StoreInfo :storeId="this.$route.params.StoreId"></StoreInfo>
        </v-flex>
      </v-layout>
      <v-layout class="justify-center">
        <v-btn
          v-if="MenuVisible"
          outlined
          large
          text
          @click="MenuBtnClick()"
          class="font-weight-black"
        >메뉴</v-btn>
        <v-btn
          v-else
          outlined
          large
          text
          @click="MenuBtnClick()"
          class="font-weight-bold grey--text"
        >메뉴</v-btn>
        <v-btn
          v-if="ReviewVisible"
          outlined
          large
          text
          @click="ReviewBtnClick()"
          class="font-weight-black"
        >리뷰</v-btn>
        <v-btn
          v-else
          outlined
          large
          text
          @click="ReviewBtnClick()"
          class="font-weight-bold grey--text"
        >리뷰</v-btn>
      </v-layout>
    </v-container>

    <!-- Store Info Component -->
    <v-container fluid>
      <v-layout v-show="MenuVisible" wrap class="justify-center">
        <!-- <deliveryMenuInfo :list="list"></deliveryMenuInfo> -->

        <v-list>
          <v-list-item v-for="(item,index) in list" :key="index" class="mx-0 px-0 my-2">
            <v-flex xs12 sm12 md12 lg12>
              <deliveryMenuInfo
                :imageUrl="'http://192.168.100.92:8080/image/'+item.img"
                :name="item.name"
                :cost="item.cost"
                :tip="item.tip"
                :tag="item.tag"
              ></deliveryMenuInfo>
            </v-flex>
          </v-list-item>
        </v-list>
      </v-layout>

      <!-- 리뷰 카드(일반 사용자가 가게 리뷰) -->
      <v-layout v-show="ReviewVisible" wrap class="justify-center">
        <!-- 리뷰 -->
        <v-flex xs12 sm7 md7 lg4>
          <Review :storeId="this.$route.params.StoreId"></Review>
        </v-flex>
      </v-layout>
    </v-container>

    <!-- 장바구니 바로 이동 버튼 -->
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
import Review from "../components/Review";

export default {
  name: "StoreInfoPage",
  components: {
    UserNavBar,
    deliveryMenuInfo,
    StoreInfo,
    Review
  },
  data() {
    return {
      list: [],
      icons: {
        mdiCart
      },
      alertCount: 5,
      hover: false,
      MenuVisible: true,
      ReviewVisible: false
    };
  },

  mounted() {
    console.log(this.$route.params.StoreId);
    this.getMenuList();
    this.getCartList();
    console.log("mounted ");
    console.log(this.list);
  },

  methods: {
    MenuBtnClick() {
      this.MenuVisible = true;
      this.ReviewVisible = false;
    },
    ReviewBtnClick() {
      this.MenuVisible = false;
      this.ReviewVisible = true;
    },
    async getMenuList() {
      // console.log("getMyImage start" + this.myId);
      await UserOrder.getStoreMenuList(
        this.$route.params.StoreId,
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