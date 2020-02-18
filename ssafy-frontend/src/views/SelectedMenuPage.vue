<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>

    <v-layout class="my-5 justify-center">
      <v-flex xs12 md3 lg3>
        <SelectMenu
          :a="menuInfo"
          :storeId="storeId"
          :imageUrl="imageUrl"
          :name="name"
          :cost="cost"
          :tip="tip"
          :tag="tag"
        ></SelectMenu>
      </v-flex>
    </v-layout>
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
      <!-- 장바구니 담은게 있으면,, -->
      <v-badge color="red" :content="alertCount">
        <v-icon>{{icons.mdiCart }}</v-icon>
      </v-badge>
      <!-- 장바구니 담은게 없으면,,, -->
    </v-btn>
  </div>
</template>

<script>
import SelectMenu from "../components/SelectMenu";
import UserNavBar from "../components/UserNavBar";
import router from "@/router.js";
import store from "@/vuex/store.js";
import { mdiCart } from "@mdi/js";
import UserCartService from "../services/UserCartService";
export default {
  name: "SelectedMenu",
  components: {
    SelectMenu,
    UserNavBar
  },
  data() {
    return {
      menuInfo: [],
      d: this.$route.params,
      storeId: this.$route.params.storeId,
      imageUrl: this.$route.params.imageUrl,
      name: this.$route.params.name,
      cost: this.$route.params.cost,
      tip: this.$route.params.tip,
      tag: this.$route.params.tag,
      icons: {
        mdiCart
      },
      alertCount: 0
    };
  },
  props: {},
  created() {
    console.log("SelectedMenuPage Created");
    this.getCartList();
  },
  mounted() {
    console.log("SelectedMenuPage mounted");
    this.storeId = this.$route.params.storeId;
    this.imageUrl = this.$route.params.imageUrl;
    this.name = this.$route.params.name;
    this.cost = this.$route.params.cost;
    this.tip = this.$route.params.tip;
    this.tag = this.$route.params.tag;
  },
  methods: {
    moveMyCartListPage() {
      this.$router.push({
        name: "MyCartPage"
      });
    },
    getCartList() {
      UserCartService.getCartList(
        response => {
          this.alertCount = response.data.length;
        },
        errorCallback => {
          console.log("error");
        }
      );
    }
  }
};
</script>

<style>
#basketBtn {
  bottom: 0;
  margin: 0 0 16px 16px;
}
</style>