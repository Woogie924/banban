<template>
  <div>
    <v-card elevation="24">
      <v-card-title>주문 현황</v-card-title>
      <v-card-text>
        <v-card v-for="item in list" :key="item.onum">
          <v-card-title>{{item.menuname}}</v-card-title>
          <v-card-text>
            {{item.userid}}고객님
            <v-divider />
            주문 번호 : {{item.onum}}
            <v-divider />
            주소 : {{item.address}}
            <v-divider />
            가격 : {{item.price}}
          </v-card-text>
        </v-card>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import shopkeeper from "../services/shopkeeper";
export default {
  name: "OrderList",
  methods: {
    getOrderList() {
      shopkeeper.getOrderList(
        response => {
          console.log("shopkeeper getOrderList start");
          console.log(response);
          for (let index = 0; index < response.data.length; index++) {
            this.list[index] = response.data[index];
            this.$set(this.list, index, response.data[index]);
            console.log(this.list[index]);
          }
          // console.log("shopkeeper getOrderList " + this.list);
        },
        errorcallback => {
          console.log("shopkeeper error:" + errorcallback);
        }
      );
    }
  },
  mounted() {
    this.getOrderList();
  },
  data() {
    return {
      // list: [
      //   {
      //     index: "1",
      //     title: "김현우",
      //     credit: "19,900",
      //     address: "구미시 진평동"
      //   }
      // ]
      list: []
    };
  }
};
</script>

<style>
</style>