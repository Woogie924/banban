<template>
  <div>
    <v-toolbar dense>
      <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
    </v-toolbar>
    <v-card>
      <v-card-title>주문 가능</v-card-title>
      <v-divider></v-divider>

      <v-card-text v-for="value in StoreList" :key="value.index">{{value.data}}</v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "OrderPossible",
  data() {
    return {
      StoreList: [
        { index: "1", data: "교촌치킨 진평점" },
        { index: "2", data: "오꾸닭 구미진평점" },
        { index: "3", data: "BBQ 구미구평점" },
        { index: "4", data: "60계 치킨 구미 진평점" },
        { index: "5", data: "BHC치킨 구미진평점" },
        { index: "6", data: "굽네치킨 구미인동점" }
      ]
    };
  }
};
</script>

<style>
</style>