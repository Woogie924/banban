<template>
  <div>
    <v-flex v-for="(item,i) in list" :key="i" class="my-5">
      <v-card>
        <v-card-title class="justify-center headline font-weight-bold">{{item.storeid}}의 가게</v-card-title>
        <v-divider></v-divider>

        <v-list dense>
          <v-list-item>
            <v-list-item-content></v-list-item-content>
            <v-list-item-content class="justify-center">{{item.menuname}}</v-list-item-content>
            <v-list-item-content>
              <v-btn color="teal lighten-2" text dark>삭제</v-btn>
            </v-list-item-content>
          </v-list-item>
          <v-list-item tile dense>
            <v-list-item-content class="justify-center">
              <v-card-actions class="justify-center">
                <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="minus(i)">
                  <v-icon dark>mdi-minus</v-icon>
                </v-btn>
                {{list[i].quantity}}개
                <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="plus(i)">
                  <v-icon dark>mdi-plus</v-icon>
                </v-btn>
              </v-card-actions>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-flex>

    <!-- <v-bottom-navigation fixed background-color="transparent"> -->
    <v-btn
      fixed
      bottom
      left
      block
      large
      dark
      color="teal lighten-2"
      @click="addCart()"
    >{{total_price}} 원</v-btn>
    <!-- </v-bottom-navigation> -->
  </div>
</template>

<script>
import { mdiMusicAccidentalSharp } from "@mdi/js";
import store from "@/vuex/store.js";
import UserCartService from "../services/UserCartService";
export default {
  name: "MyCartList",
  data() {
    return {
      list: [],
      num: 1,
      price: 15000,
      default_price: 15000,
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
        console.log("MyCartList component getCartList,,,");
        console.log(response.data);
        this.initPrice();
        this.summationPrice();
      });
    },
    summationPrice() {
      for (let index = 0; index < this.list.length; index++) {
        this.total_price += this.list[i].price;
      }
    },
    initPrice() {
      console.log("initPrice");
      for (let index = 0; index < this.list.length; index++) {
        this.list[index].price =
          this.list[index].price / this.list[index].quantity;

        this.total_price += this.list[index].price;
      }
    },
    minus(idx) {
      if (this.list[idx].quantity > 1) {
        this.list[idx].quantity -= 1;
        this.list[idx].price -= this.list[idx].price;
        this.total_price -= this.list[idx].price / this.list[idx].quantity;
        console.log(this.total_price);
      }
    },
    plus(idx) {
      this.list[idx].quantity += 1;
      this.list[idx].price += this.list[idx].price;
      this.total_price += this.list[idx].price / this.list[idx].quantity;
      console.log(this.total_price);
    }
  }
};
</script>

<style scoped>
</style>