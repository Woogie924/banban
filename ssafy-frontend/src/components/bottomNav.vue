<template>
  <v-bottom-navigation fixed :value="activeBtn" grow hide-on-scroll>
    <v-card v-for="item in items" :key="item.title" link>
      <template>
        <MenuManagement :flag="item.title" :icon="item.icon"></MenuManagement>
      </template>
    </v-card>
  </v-bottom-navigation>
</template>


<script>
import MenuManagement from "../components/MenuManagement";
export default {
  name: "bottomeNav",
  components: {
    MenuManagement
  },
  data() {
    return {
      dialog: false,

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