<template>
  <div>
    <v-card elevation="0" style="border:1px solid grey">
      <v-card-title class="text-center justify-center">
        <div class="underlined">정산 예정 금액</div>
      </v-card-title>

      <v-card-text class="text-center justify-center mb-1">
        <h1 class="counter">{{totalprice}} 원</h1>
      </v-card-text>
      <v-card-subtitle class="grey-text caption text-center">
        현재 기준으로 등록하신 2개 가게의 합계 금액이며,
        <br />정산 지급일 등 정산 관련 상세 정보는 정산 관리에서 확인하실 수 있습니다.
      </v-card-subtitle>
    </v-card>

    <br />
    <v-card class="text-center" elevation="0" style="border:1px solid grey">
      <v-card-title class="text-center justify-center">
        <div class="underlined">주간 매출</div>
      </v-card-title>

      <v-card-text>
        <!-- color="rgba(255,255,255,.7)" -->

        <v-sheet color="grey lighten-4">
          <v-sparkline
            :value="value"
            :gradient="gradient"
            :smooth="radius || false"
            :padding="padding"
            :line-width="width"
            :stroke-linecap="lineCap"
            :gradient-direction="gradientDirection"
            :fill="fill"
            :type="type"
            :auto-line-width="autoLineWidth"
            auto-draw
            height="100vh"
          >
            <template v-slot:label="item">{{item.value}}</template>
          </v-sparkline>
        </v-sheet>
      </v-card-text>
    </v-card>
    <br />
  </div>
</template>

<script>
import axios from "axios";
import store from "@/vuex/store.js";
import shopkeeper from "../services/shopkeeper";
const gradients = [
  ["#222"],
  ["#42b3f4"],
  ["red", "orange", "yellow"],
  ["purple", "violet"],
  ["#00c6ff", "#F0F", "#FF0"],
  ["#f72047", "#ffd200", "#1feaea"]
];
export default {
  name: "StorePayment",
  props: {
    totalprice: { type: String }
  },
  data() {
    return {
      store: [],
      value: [423, 446, 675, 510, 590, 800, 760],
      list: [],

      width: 2,
      radius: 20,
      padding: 8,
      lineCap: "round",
      gradient: gradients[5],
      gradientDirection: "top",
      gradients,
      fill: false,
      type: "trend",
      autoLineWidth: true,
      duration: 8000,
      idx: 0
    };
  },
  created() {
    this.getProfit();

    this.timer = setTimeout(
      function() {
        this.getProfit();
      }.bind(this),
      30000
    );
  },
  mounted() {},
  methods: {
    test() {
      this.value[this.idx] = 100;
      this.idx++;
      console.log(this.value);
    },
    getProfit() {
      shopkeeper.getProfit(
        response => {
          console.log("shopkeeper getProfit start");
          console.log(response.data);

          for (let index = 0; index < response.data.length; index++) {
            this.list[index] = response.data[index].price;
            // console.log("a" + response.data[index].price);
            // console.log("b" + this.list[index]);
            this.$set(this.list, index, response.data[index].price);
            this.$set(this.value, index, this.list[index]);

            // this.value[index] = Number(this.list[index]);

            // console.log(this.list[index]);
          }
          console.log("shopkeeper getProfit " + this.list);
        },
        errorcallback => {
          console.log("shopkeeper error:" + errorcallback);
        }
      );
    }
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
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>