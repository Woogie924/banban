<template>
  <div>
    <v-card elevation="0">
      <v-list class="pt-5">
        <v-list-item class="my-0 py-0 justify-center text-center">
          <v-list-item-content class="justify-center text-center my-0 py-0" two-line>
            <v-list-item-title class="display-1 font-weight-regular justify-center my-0">
              <span>{{list.store.name}}</span>

              <span class="text-right justify-right">
                <v-icon>{{heartOut}}</v-icon>
              </span>
            </v-list-item-title>
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
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-divider />
      <v-list>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">
              <div class="underlined">최소주문금액</div>
            </v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">{{list.minprice}}원</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">
              <div class="underlined">결제방법</div>
            </v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">바로결제, 만나서결제</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin">
              <div class="underlined">배달시간</div>
            </v-list-item-title>
          </v-list-item-content>
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-medium">{{list.deliverytime}} 소요 예상</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="my-0">
          <v-list-item-content class="my-0 py-0">
            <v-list-item-title class="font-weight-thin my-0 py-0">
              <div class="underlined">배달팁</div>
            </v-list-item-title>
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
      list: {
        snum: 0,
        storeid: "",
        minprice: 0,
        deliverytip: 0,
        deliverytime: ""
      }
    };
  },

  created() {
    this.getStoreInfo(this.storeId);
  },
  mounted() {
    this.getStoreInfo(this.storeId);
  },
  methods: {
    getStoreInfo(id) {
      UserOrder.getStoreInfo(
        id,
        response => {
          this.list = response.data;
          console.log(this.list, 111);
        },
        error => {
          console.log(error);
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
  background-image: linear-gradient(to top, #23c984 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>