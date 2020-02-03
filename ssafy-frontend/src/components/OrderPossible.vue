<template>
  <v-card>
    <v-card-title>주문 가능</v-card-title>
    <v-card-text v-for="value in StoreList" :key="value.index">{{value.data}}</v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "OrderPossible",
  components: {},
  data() {
    return {
      StoreList: [
        { index: "1", data: "교촌치킨 진평점" },
        { index: "1", data: "오꾸닭 구미진평점" },
        { index: "1", data: "BBQ 구미구평점" },
        { index: "1", data: "60계 치킨 구미 진평점" },
        { index: "1", data: "BHC치킨 구미진평점" },
        { index: "1", data: "굽네치킨 구미인동점" }
      ]
    };
  }
};
</script>

<style>
</style>