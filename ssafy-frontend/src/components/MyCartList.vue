<template>
  <div>
    <v-flex v-for="(item,i) in list" :key="i" class="my-5">
      <v-card>
        <v-card-title class="justify-center headline font-weight-bold">{{item.storeid}}의 가게</v-card-title>
        <v-btn color="teal lighten-2" text dark @click="deletecart(item.unum)">삭제</v-btn>
        <v-divider></v-divider>

        <v-list>
          <v-list-item>
            <v-list-item-content class="justify-center text-center">
              <v-list-item-title>
                <div class="underlined">{{item.menuname}}</div>
              </v-list-item-title>
              <v-list-item-subtitle>
                <v-card-actions class="justify-center">
                  <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="minus(i)">
                    <v-icon dark>mdi-minus</v-icon>
                  </v-btn>
                  {{list[i].quantity}}개
                  <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="plus(i)">
                    <v-icon dark>mdi-plus</v-icon>
                  </v-btn>
                </v-card-actions>
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-flex>

    <!-- <v-bottom-navigation fixed background-color="transparent"> -->
    <v-btn
      block
      large
      dark
      outlined
      color="teal lighten-2"
      @click="movePaymentPage()"
    >{{total_price}} 원</v-btn>
    <!-- </v-bottom-navigation> -->
  </div>
</template>

<script>
import axios from "axios";
import { mdiMusicAccidentalSharp } from "@mdi/js";
import store from "@/vuex/store.js";
import UserCartService from "../services/UserCartService";
import router from "@/router.js";
export default {
  name: "MyCartList",
  data() {
    return {
      list: [],
      num: 1,
      price: 0,
      total_price: 0
    };
  },
  mounted() {
    this.getCartList();
  },

  methods: {
    getCartList() {
      UserCartService.getCartList(response => {
        this.list = response.data;
        console.log(this.list);
        for (let index = 0; index < this.list.length; index++) {
          this.list[index].price =
            this.list[index].price / this.list[index].quantity;
        }
        this.initPrice();
        // this.summationPrice();
      });
    },
    // summationPrice() {
    //   for (let index = 0; index < this.list.length; index++) {
    //     this.total_price += this.list[i].price;
    //   }
    // },
    initPrice() {
      this.total_price = 0;

      for (let index = 0; index < this.list.length; index++) {
        this.total_price += this.list[index].quantity * this.list[index].price;
      }
    },
    minus(idx) {
      if (this.list[idx].quantity > 1) {
        this.list[idx].quantity -= 1;
        this.initPrice();
      }
    },
    plus(idx) {
      this.list[idx].quantity += 1;
      this.initPrice();
    },
    deletecart(unum) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "delete",
        url: `http://192.168.100.92:8080/api/cart/${unum}`
      }).then(() => {
        this.getCartList();
      });
    },
    movePaymentPage() {
      // 내 결제 페이지로 분기
      var data = {
        storeid: this.list[0].storeid,
        list: this.list,
        num: this.num,
        price: this.price,
        default_price: this.default_price,
        total_price: this.total_price
      };

      this.$router.push({
        name: "MyPaymentPage",
        params: data
      });
    }
  }
};
</script>

<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  font-size: 1.2em;
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