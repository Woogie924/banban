<template>
  <div>
    <v-card id="card" color="grey lighten-3" max-height="100%">
      <v-card class="my-5">
        <!-- 메뉴 이미지 -->
        <v-img width="20vw" :src="imageUrl" />
        <v-list-item>
          <v-list-item-context>
            <v-list-item-title class="headline">{{name}}</v-list-item-title>
          </v-list-item-context>
        </v-list-item>

        <v-card-text class="text-left">기본</v-card-text>
        <v-card-text class="text-right">{{cost}} 원</v-card-text>
        <v-divider />
        <!-- 수량 -->

        <v-card-text>수량</v-card-text>
        <v-spacer />
        <v-card-action>
          <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="minus()">
            <v-icon dark>mdi-minus</v-icon>
          </v-btn>
          {{ this.num}}
          <v-btn class="ma-2" elevation="1" fab small tile color="white" @click="plus()">
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
        </v-card-action>
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
      <v-card class="my-5">
        <!-- 총가격 -->

        <v-card-text>
          <v-spacer />
          최소주문금액: {{this.price}}
          <v-spacer />
        </v-card-text>

        <v-btn block large dark color="teal lighten-2">
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
export default {
  name: "SelectMenu",
  props: {
    a: { type: String },
    imageUrl: { type: String },
    name: { type: String },
    cost: { type: Number },
    tip: { type: String },
    tag: {}
  },
  data() {
    return {
      num: 0,
      price: 0,
      default_price: 15000,
      show: false
    };
  },
  methods: {
    minus() {
      if (this.num > 0) {
        this.num -= 1;
        this.price -= this.default_price;
      }
    },
    plus() {
      this.num += 1;
      this.price += this.default_price;
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