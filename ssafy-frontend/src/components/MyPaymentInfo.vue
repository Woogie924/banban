<template>
  <div class="text-center">
    <v-dialog v-model="dialog" :width="myWidth" :fullscreen="$vuetify.breakpoint.xsOnly">
      <template v-slot:activator="{ on }">
        <v-btn color="red lighten-2" dark v-on="on">CLick me</v-btn>
      </template>
      <v-card class="justify-center align-center">
        <v-toolbar card floating fixed dense color="white" :width="myWidth" elevation="0">
          <v-btn icon @click="dialog = false">
            <v-icon>mdi-reply</v-icon>
          </v-btn>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-container class="justify-center">
          <v-list>
            <!-- 배달정보 -->
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="text-shadow font-weight-bold my-2">
                  <div class="underlined">배달 정보</div>
                </v-list-item-title>
                <v-list-item-subtitle class="font-weight-black text--grey">내 주소</v-list-item-subtitle>
                <div class="card">구미시 인의동 560-24 썬하우스A 305호 010-5008-9962</div>
              </v-list-item-content>
            </v-list-item>
            <v-spacer />
            <!-- 요청사항 -->
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="text-shadow font-weight-bold my-2">
                  <div class="underlined">요청 사항</div>
                </v-list-item-title>
                <v-list-item-subtitle class="font-weight-black text--grey">가게 사장님께</v-list-item-subtitle>
                <div class="card">예) 견과류 뺴주세요. 덜 맵게 해주세요</div>
              </v-list-item-content>
            </v-list-item>
            <!-- 결제금액 -->
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="text-shadow font-weight-bold my-2">
                  <div class="underlined">결제 금액</div>
                </v-list-item-title>
                <v-list-item-subtitle class="font-weight-bold">32,700원</v-list-item-subtitle>
                <div class="card">
                  <v-btn block text>결제방식</v-btn>
                </div>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>
                <v-expansion-panels focusable elevation="0">
                  <v-expansion-panel v-for="(item) in panel_items" :key="item.idx">
                    <v-expansion-panel-header>{{item.title}}</v-expansion-panel-header>
                    <v-expansion-panel-content>{{item.words}}</v-expansion-panel-content>
                  </v-expansion-panel>
                </v-expansion-panels>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-container>
        <div class="text-center font-weight-black text--grey">위 내용을 확인하였으며 결제에 동의합니다</div>
        <v-toolbar card fixed color="white" elevation="0">
          <v-btn block>32,700원 결제하기</v-btn>
        </v-toolbar>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
export default {
  name: "MyPaymentInfo",
  props: {},
  data() {
    return {
      dialog: false,
      myWidth: "100vw",
      panel_items: [
        {
          idx: 1,
          title: "배달상품 주의사항",
          words:
            "1. 고객님의 주문에 따라 개별적으로 생산 또는 " +
            "조리된 배달음식은 업주에게 회복할 수 없는 손해가 발생하므로 " +
            "단순 변심에 의한 청약철회는 할 수 없습니다."
        },
        {
          idx: 2,
          title: "개인정보 제 3자 제공",
          words:
            "1. 고객님의 주문에 따라 개별적으로 생산 또는 " +
            "조리된 배달음식은 업주에게 회복할 수 없는 손해가 발생하므로 " +
            "단순 변심에 의한 청약철회는 할 수 없습니다."
        }
      ]
    };
  },
  mounted() {
    this.myWidth = this.dialogResizing();
    console.log(this.myWidth);
  },
  methods: {
    dialogResizing() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "100vw";
        case "lg":
          return "30vw";
        case "md":
          return "80vw";
        default:
          return "50vw";
      }
    }
  }
};
</script>
<style scoped>
.v-dialog > .v-card > .v-toolbar {
  position: sticky;
  top: 0;
  z-index: 999;
}
.card {
  height: 100%;
  width: 12em;
  border-radius: 1%;
  background-color: #e0e0e0;
  box-shadow: 0.08em 0.03em 0.4em #ababab;
  padding-top: 1em;
  padding-bottom: 1em;
}
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
  background-image: linear-gradient(to top, #b4e7f8 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>