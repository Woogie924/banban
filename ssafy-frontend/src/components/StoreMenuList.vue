<template>
  <div>
    <v-navigation-drawer width="100%" color="transparent">
      <v-spacer></v-spacer>
      <!-- 검색창 -->
      <v-card elevation="6">
        <v-toolbar>
          <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
        </v-toolbar>
      </v-card>

      <br />
      <v-card elevation="6">
        <v-list rounded color="transparent">
          <v-list-item-title class="title">가게 운영</v-list-item-title>
          <v-list-item v-for="item in items" :key="item.title" link>
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <template>
              <MenuManagement :flag="item.title"></MenuManagement>
            </template>
          </v-list-item>
        </v-list>
      </v-card>
      <br />
      <v-spacer></v-spacer>
    </v-navigation-drawer>
  </div>
</template>

<script>
import MenuManagement from "../components/MenuManagement";

export default {
  name: "StoreMenuList",
  methods: {
    test(item) {
      if (item.dialog == "MenuManagement") {
      }
    }
  },
  components: {
    MenuManagement
  },
  data() {
    return {
      dialog: false,
      items: [
        { title: "메뉴 관리", icon: "account_circle" },
        { title: "메뉴 품절", icon: "account_circle" },
        { title: "영업 임시중지", icon: "account_circle" },
        { title: "운영 정보", icon: "account_circle" }
      ]
    };
  }
};
</script>

<style>
</style>