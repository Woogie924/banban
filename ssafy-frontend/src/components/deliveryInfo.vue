<template>
  <div fluid>
    <div v-for="item in list" :key="item.id" fluid>
      <deliveryMenuInfo
        :imageUrl="'http://192.168.100.92:8080/image/'+item.img"
        :name="item.name"
        :cost="item.cost"
        :tip="item.tip"
        :tag="item.tag"
        class="ma-3"
      ></deliveryMenuInfo>
    </div>
  </div>
</template>

<script>
import deliveryMenuInfo from "../components/deliveryMenuInfo";
import UserOrder from "../services/UserOrder";
export default {
  name: "deliveryInfo",
  components: {
    deliveryMenuInfo
  },
  data() {
    return {
      storeId: "asia924",
      list: []
    };
  },
  mounted() {
    this.getMenuList();
  },
  methods: {
    getMenuList() {
      // console.log("getMyImage start" + this.myId);
      UserOrder.getImage(
        this.storeId,
        response => {
          console.log("deliveryInfo Start:");
          console.log(response);
          for (let index = 0; index < response.data.length; index++) {
            // console.log(response.data[index]);
            this.list[index] = response.data[index];
          }
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