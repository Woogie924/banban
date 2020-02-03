<template>
  <div>
    <v-card>
      <v-card-title class="display-1 font-weight-black">정산 예정 금액</v-card-title>

      <v-card-text>
        <h1>{{credit}} 원</h1>
      </v-card-text>
      <v-card-subtitle>
        현재 기준으로 등록하신 2개 가게의 합계 금액이며,
        정산 지급일 등 정산 관련 상세 정보는 정산 관리에서 확인하실 수 있습니다.
      </v-card-subtitle>
    </v-card>

    <v-card class="text-center" color="transparent">
      <v-card-title class="display-1">주간 매출</v-card-title>

      <v-card-text>
        <v-sheet color="rgba(0,0,0, .12)">
          <v-sparkline
            :value="value"
            color="rgba(255,255,255,.7)"
            height="100"
            padding="24"
            stroke-linecap="round"
            smooth
          >
            <template v-slot:label="item">{{item.value}}</template>
          </v-sparkline>
        </v-sheet>
      </v-card-text>

      <v-divider></v-divider>
      <v-card-actions class="justify-center">
        <v-btn block text>상세 보기</v-btn>
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "StorePayment",
  data() {
    return {
      store: [],
      credit: 0,
      value: [423, 446, 675, 510, 590, 800, 760]
    };
  },

  method: {
    init() {
      alert("init들어옴");
      axios({
        method: "get",
        url: "http://192.168.100.92:8080/shopkeeper/store/111"
      }).then(() => {
        console.log(res.data);
        this.store = res.data;
        console.log(this.store);
        alert(this.store);
      });
    }
  },
  mounted() {
    this.init();
    alert("zzz");
    window.console.log("ㅅㅂ");
  }
};
</script>

<style>
</style>