<template>
  <div>
    <v-card>
      <v-list class="justify-center text-center">
        <v-list-item class="my-0 py-0 justify-center text-center">
          <v-list-item-content class="justify-center text-center my-0 py-0" two-line>
            <v-list-item-title
              class="display-1 font-weight-regular justify-center my-0"
            >{{storeName}}</v-list-item-title>
            <v-list-item-subtitle>{{menuName}}</v-list-item-subtitle>

            <!-- 주문확정 대기중 -->
            <div>
              <v-list-item-subtitle>주문 완료(확정X)</v-list-item-subtitle>
            </div>
            <!-- 주문 완료  -->
            <div>
              <v-list-item-subtitle>주문 완료</v-list-item-subtitle>
            </div>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <span class="grey--text text--darken-1 text-center">{{totalPeople}}명</span>
            <span class="grey--text text--darken-1 text-center caption">{{currentCash}}원</span>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="justify-center">
          <v-list-item-content class="justify-center">
            <v-list-item-subtitle>결제 인원</v-list-item-subtitle>
            <v-card elevation="0">
              <v-list-item-subtitle v-for="(item) in peopleList" :key="item.idx">
                <div v-if="item.type">{{item.userid}}</div>
              </v-list-item-subtitle>
            </v-card>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-divider />
      <v-btn @click="plusPeople()">사람 증가</v-btn>
    </v-card>
  </div>
</template>
<script>
export default {
  name: "WaitPayment",
  data() {
    return {
      storeName: "앗싸마늘닭 인동점",
      menuName: "양념치킨",
      starPoint: "4.8",
      totalCash: 30000,
      personalCash: 5000,
      currentCash: 0,
      totalPeople: 5,
      joinPeople: 0,
      progressPercent: 0,
      tips:
        "앗싸는 사랑입니다,,,,수준 높은 마늘닭, 찜닭, 똥집의 맛을 즐겨보세요",
      list: [],
      peopleList: [
        { idx: 1, userid: "asia924", type: false },
        { idx: 2, userid: "hsw924", type: true },
        { idx: 3, userid: "zizizi", type: false },
        { idx: 4, userid: "kas4343", type: false },
        { idx: 5, userid: "zzzz123", type: true }
      ]
    };
  },
  mounted() {
    this.personalCash = this.totalCash / this.totalPeople;
  },
  methods: {
    plusPeople() {
      if (this.joinPeople < this.totalPeople) {
        this.joinPeople++;
        this.currentCash = this.joinPeople * this.personalCash;
        this.progressPercent = (this.joinPeople / this.totalPeople) * 100;
      }
    }
  }
};
</script>
<style>
</style>