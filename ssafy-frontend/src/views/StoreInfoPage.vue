<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <!-- Store Info Component -->
    <v-container class="my-5">
      <v-layout wrap>
        <v-flex v-for="(item,i) in list" :key="i">
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
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import deliveryMenuInfo from "../components/deliveryMenuInfo";
import UserOrder from "../services/UserOrder";
export default {
  name: "StoreInfoPage",
  components: {
    UserNavBar,
    deliveryMenuInfo
  },
  data() {
    return { storeId: "asia924", list: [] };
  },
  created() {
    // this.$nextTick(() => {
    //   this.getMenuList();
    // });
    // this.getMenuList();
    // console.log("Infopage created");
  },
  mounted() {
    this.getMenuList();
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
    }
  }
};
</script>

<style>
</style>