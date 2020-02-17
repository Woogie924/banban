<template>
  <div>
    <v-card>
      <v-list class="pt-5">
        <v-list-item class="my-0 py-0 justify-center text-center">
          <v-list-item-content class="justify-center text-center my-0 py-0" two-line>
            <v-list-item-title
              class="display-1 font-weight-regular justify-center my-0"
            >{{list.store.name}}</v-list-item-title>
            <v-list-item-subtitle>
              <v-rating
                v-model="list.point"
                color="yellow darken-1"
                large
                dense
                readonly
                class="my-0"
              ></v-rating>
              <span class="grey--text text--lighten-1 caption">({{list.point.toFixed(1)}})</span>
            </v-list-item-subtitle>
            <div class="text-right">
              <v-icon>{{heartOut}}</v-icon>
            </div>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-divider />
      <v-list>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">최소주문금액</v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">{{list.minprice}}원</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">결제방법</v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">바로결제, 만나서결제</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">배달시간</v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">{{list.deliverytime}} 소요 예상</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin my-0 py-0">배달팁</v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium my-0 py-0">{{list.deliverytip}}원</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
  </div>
</template>

<script>
import UserOrder from "../services/UserOrder";
import { mdiHeartOutline, mdiCardsHeart } from "@mdi/js";
export default {
  name: "StoreInfo",
  props: {
    storeId: { type: String }
  },
  data() {
    return {
      heartOut: mdiHeartOutline,
      heartIn: mdiCardsHeart,
      storeName: "앗싸마늘닭 인동점",
      starPoint: "4.8",
      minimumCost: "14900",
      deliveryTime: "45",
      deliveryTip: "1000",
      tips:
        "앗싸는 사랑입니다,,,,수준 높은 마늘닭, 찜닭, 똥집의 맛을 즐겨보세요",
      list: []
    };
  },
  mounted() {
    this.getStoreInfo();
  },
  methods: {
    getStoreInfo() {
      UserOrder.getStoreInfo(this.storeId, response => {
        this.list = response.data;
        console.log("UserOrder getStoreInfo");
        console.log(this.list);
      });
    }
  }
};
</script>

<style>
</style>