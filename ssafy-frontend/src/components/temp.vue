<template>
  <v-layout>
    <div v-for="item in list" :key="item.index">
      <img :src="item.source" aspect-ratio="1.0">{{item.data}}
    </div>
  </v-layout>
</template>

<script>
export default {
  name: "temp",
  data() {
    return {
      list: [
        { index: "1", data: "치킨", source: "../assets/치킨.png" },
        { index: "2", data: "디저트", source: "../assets/디저트.png" },
        { index: "3", data: "보쌈", source: "@/assets/보쌈.png" },
        { index: "4", data: "야식", source: "@/assets/야식.png" },
        { index: "5", data: "일식", source: "@/assets/일식.png" },
        { index: "6", data: "족발", source: "@/assets/족발.png" },
        { index: "7", data: "피자", source: "@/assets/피자.png" },
        { index: "8", data: "한식", source: "@/assets/한식.png" },
        { index: "9", data: "분식", source: "@/assets/분식.png" }
      ]
    };
  }
};
</script>

<style>
</style>