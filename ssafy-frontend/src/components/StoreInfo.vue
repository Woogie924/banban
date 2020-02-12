<template>
  <div>
    <v-card>
      <v-list>
        <v-list-item class="ma-0 pa-0">
          <v-list-item-content class="ma-0 pa-0">
            <v-card-title class="display-1 font-weight-regular justify-center">{{storeName}}</v-card-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="justify-center text-center">
          <v-list-item-content class="ma-0 pa-0">
            <v-rating v-model="starPoint" color="yellow darken-1" large readonly></v-rating>
            <span class="grey--text text--lighten-1 caption">({{starPoint}})</span>
          </v-list-item-content>
        </v-list-item>
        <v-divider vertical class="mx-4"></v-divider>
        <v-spacer />
        <v-list-item class="justify-center text-left">
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-thin">최소주문금액</v-card-text>
          </v-list-item-content>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-medium">{{minimumCost}}원</v-card-text>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="justify-center text-left">
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-thin">결제방법</v-card-text>
          </v-list-item-content>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-medium">바로결제, 만나서결제</v-card-text>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-thin">배달시간</v-card-text>
          </v-list-item-content>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-medium">{{deliveryTime}}분 소요 예상</v-card-text>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-thin">배달팁</v-card-text>
          </v-list-item-content>
          <v-list-item-content class="ma-0 pa-0">
            <v-card-text class="font-weight-medium">{{deliveryTip}}원</v-card-text>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <!-- <v-btn>전화주문</v-btn> -->
          </v-list-item-content>
          <v-list-item-content>
            <!-- <v-btn>찜</v-btn> -->
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "StoreInfo",
  data() {
    return {
      storeName: "앗싸마늘닭 인동점",
      starPoint: "4.8",
      minimumCost: "14900",
      deliveryTime: "45",
      deliveryTip: "1000",
      tips:
        "앗싸는 사랑입니다,,,,수준 높은 마늘닭, 찜닭, 똥집의 맛을 즐겨보세요"
    };
  },
  mounted() {},
  methods: {}
};
</script>

<style>
</style>