<template>
  <div>
    <v-card>
      <v-card-title class="display-1 font-weight-black">정산 예정 금액</v-card-title>

      <v-card-text>
        <h1>{{store.point}} 원</h1>
      </v-card-text>
      <v-card-subtitle>
        현재 기준으로 등록하신 2개 가게의 합계 금액이며,
        정산 지급일 등 정산 관련 상세 정보는 정산 관리에서 확인하실 수 있습니다.
      </v-card-subtitle>
    </v-card>

    <v-card class="text-center" color="transparent">
      <v-card-title class="display-1">주간 매출</v-card-title>

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
      <v-btn @click="test">테스트</v-btn>
      <v-divider></v-divider>
      <v-card-actions class="justify-center">
        <v-btn block text>상세 보기</v-btn>
      </v-card-actions>
    </v-card>
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
  mounted() {
    this.getProfit();

    this.timer = setTimeout(
      function() {
        this.getProfit();
      }.bind(this),
      30000
    );
  },
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
          // console.log(response.data);

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

<style>
</style>