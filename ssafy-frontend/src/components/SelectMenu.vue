<template>
  <div>
    <v-card id="card" color="grey lighten-3" max-height="100%">
      <v-card class="my-5">
        <!-- 메뉴 이미지 -->
        <v-layout class="my-5 mx-5">
          <v-img width="20vw" :src="imageUrl" />
        </v-layout>
        <v-divider />
        <v-list-item tile dense>
          <v-list-item-content>
            <v-list-item-title class="headline">
              {{name}}
              <v-btn
                v-for="i in tags.length >2? tag.length-1:2"
                :key="i"
                label
                :color="color[i]"
                dark
                class="ma-2"
              >
                <v-icon dark left large>{{svgPath}}</v-icon>
                {{tags[i-1]}}
              </v-btn>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item tile dense>
          <v-list-item-content>
            <v-card-text class="text-left">기본</v-card-text>
          </v-list-item-content>
          <v-list-item-content>
            <v-card-text class="text-right">{{cost}} 원</v-card-text>
          </v-list-item-content>
        </v-list-item>

        <v-divider />
        <!-- 수량 -->
        <v-list-item tile dense>
          <v-list-item-content>
            <v-card-text>수량</v-card-text>
          </v-list-item-content>
          <v-list-item-content>
            <v-card-actions>
              <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="minus()">
                <v-icon dark>mdi-minus</v-icon>
              </v-btn>
              {{ this.num }}
              <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="plus()">
                <v-icon dark>mdi-plus</v-icon>
              </v-btn>
            </v-card-actions>
          </v-list-item-content>
        </v-list-item>

        <v-spacer />

        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
        <v-expand-transition>
          <div v-show="show">
            <v-divider></v-divider>

            <v-card-text>{{tip}}</v-card-text>
          </div>
        </v-expand-transition>
      </v-card>
      <v-card class="my-5 justify-center">
        <!-- 총가격 -->
        <v-list-item>
          <v-list-item-content>
            <v-card-text>최소주문금액 :</v-card-text>
          </v-list-item-content>
          <v-list-item-content>
            <v-card-text>{{this.price}} 원</v-card-text>
          </v-list-item-content>
        </v-list-item>

        <v-btn
          fixed
          bottom
          large
          dark
          color="teal lighten-2"
          @click="addCart()"
          class="justify-center"
        >
          <v-spacer />
          {{this.num}}개 담기
          <v-spacer />
          {{this.price}} 원
        </v-btn>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import { mdiMusicAccidentalSharp } from "@mdi/js";
import MenuManagementVue from "./MenuManagement.vue";
import store from "@/vuex/store.js";
import UserCartService from "../services/UserCartService";
export default {
  name: "SelectMenu",
  props: {
    imageUrl: { type: String },
    name: { type: String },
    cost: { type: Number },
    tip: { type: String },
    tag: {}
  },
  data() {
    return {
      num: 1,
      price: this.cost,
      default_price: this.cost,
      show: false,
      color: ["indigo", "orange", "primary", "green", "teal"],
      tags: [],
      svgPath: mdiMusicAccidentalSharp
    };
  },
  mounted() {
    this.splitTag();
  },
  methods: {
    minus() {
      if (this.num > 1) {
        this.num -= 1;
        this.price -= this.default_price;
      }
    },
    plus() {
      this.num += 1;
      this.price += this.default_price;
    },
    splitTag() {
      this.tags = this.tag.split("<");
      //   alert(tags);
    },
    moveStoreMenuPage() {
      // 가게 메뉴 페이지로 분기
      this.$router.push({
        name: "StoreInfoPage",
        params: {
          //   imageUrl: that.imageUrl,
          imageUrl: this.imageUrl,
          name: this.name,
          cost: this.cost,
          tip: this.tip,
          tag: this.tag
        }
      });
    },
    addCart() {
      // 내 id로 된 장바구니에 정보 담기
      var cartVO = {
        unum: 0,
        userid: this.$store.state.userName,
        menuName: this.name,
        storeid: "asia924",
        price: this.price,
        quantity: this.num
      };
      UserCartService.setCartVO(cartVO);

      // 가게 메뉴 페이지로 분기
      this.moveStoreMenuPage();
    }
  }
};
</script>

<style scoped>
#card {
  width: 100%;
  height: 100%;
}
</style>