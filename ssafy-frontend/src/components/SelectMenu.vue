<template>
  <div>
    <v-flex xs12 sm6 md4 lg3 xl2>
      <!-- 메뉴 이미지 -->
      <v-layout>
        <v-card max-width="400">
          <v-img
            class="white--text align-end"
            height="200px"
            src="https://t1.daumcdn.net/liveboard/interbiz/724b01edcbeb44dfa3fe10a3dbbda51f.JPG"
          >
            <v-card-title>황금올리브치킨</v-card-title>
          </v-img>
          <v-btn icon @click="show = !show">
            <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
          </v-btn>
          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>

              <v-card-text>
                올리브와 치킨이 만나 기분이 좋아
                좋아 올리브유니까
                맛도 올리고 건강도 올리고 나는 행복해
                주고 싶고 자꾸 먹고싶은 올리브 치킨
                엄마사랑 아이행복 BBQ
              </v-card-text>
            </div>
          </v-expand-transition>
        </v-card>
      </v-layout>

      <!-- 수량 -->
      <p>기본 {{this.default_price}} 원</p>
      <v-layout>
        <p>
          수량
          <v-btn class="mx-2" fab dark small color="primary" @click="minus()">
            <v-icon dark>mdi-minus</v-icon>
          </v-btn>
          {{ this.num}}
          <v-btn class="mx-2" fab dark small color="danger" @click="plus()">
            <v-icon dark>mdi-plus</v-icon>
          </v-btn>
        </p>
      </v-layout>

      <!-- 총가격 -->
      <v-layout>
        <v-btn>{{this.num}}개 담기 총 주문금액은 {{this.price}} 원입니다.</v-btn>
      </v-layout>
    </v-flex>
  </div>
</template>

<script>
export default {
  name: "SelectMenu",
  data() {
    return {
      num: 0,
      price: 0,
      default_price: 15000,
      show: false
    };
  },
  props: {
    a: { type: String }
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

<style>
</style>