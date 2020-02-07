<template>
  <v-bottom-navigation fixed grow hide-on-scroll>
    <!-- <v-card v-for="item in items" :key="item.title" link>
      <template>
        <MenuManagement :flag="item.title" :icon="item.icon"></MenuManagement>
      </template>
    </v-card>-->
    <v-btn>
      <MenuManagement :flag="item.title" :icon="item.icon">dsdsd</MenuManagement>
    </v-btn>
  </v-bottom-navigation>
</template>


<script>
import MenuManagement from "../components/MenuManagement";
export default {
  name: "bottomeNav",
  components: {
    MenuManagement
  },
  props: {},
  data() {
    return {
      dialog: false,
      item: {
        title: "메뉴 관리",
        icon: "mdi-history"
      },
      items: [
        { title: "메뉴 관리", icon: "mdi-history" },
        { title: "메뉴 품절", icon: "mdi-heart" },
        { title: "영업 임시중지", icon: "account_circle" },
        { title: "운영 정보", icon: "mdi-map-marker" }
      ]
    };
  },
  methods: {
    test(item) {
      this.dialog = !this.dialog;
    }
  }
};
</script>
      <style>
</style>