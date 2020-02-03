<template>
  <div>
    <v-tabs v-model="tab" fluid>
      <v-tab v-for="item in MenuList" :key="item.index">{{item.data}}</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item v-for="item in MenuList" :key="item.index">
        <v-row>
          <v-col>
            <!-- Google MAP API 삽입 부분-->
            <v-img src="../assets/지도.jpg" aspect-ratio="1.0"></v-img>
          </v-col>
        </v-row>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import Ranking from "../components/Ranking";
export default {
  name: "Menu",
  components: {
    Ranking
  },
  data() {
    return {
      tab: true,
      MenuList: [
        { index: "1", data: "치킨" },
        { index: "2", data: "피자" },
        { index: "3", data: "한식" },
        { index: "4", data: "분식" },
        { index: "5", data: "디저트" },
        { index: "6", data: "일식" },
        { index: "7", data: "패스트푸드" },
        { index: "8", data: "야식" },
        { index: "9", data: "중국집" },
        { index: "10", data: "족발,보쌈" }
      ]
    };
  }
};
</script>

<style>
</style>