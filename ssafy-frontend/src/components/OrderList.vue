<template>
  <div>
    <v-card elevation="0" style="border:1px solid grey">
      <v-card-title class="justify-center">
        <div class="underlined">주문 현황</div>
      </v-card-title>
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
          // for (let index = 0; index < response.data.length; index++) {
          //   this.list[index] = response.data[index];
          //   this.$set(this.list, index, response.data[index]);
          //   console.log(this.list[index]);
          // }
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
  background-image: linear-gradient(to top, #23c984 10%, rgba(0, 0, 0, 0) 30%);
  position: absolute;
  left: 3px;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>