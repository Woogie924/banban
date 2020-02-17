<template>
  <v-bottom-navigation :value="activeBtn" fixed hide-on-scroll color="teal">
    <!-- 가게 메뉴 관리(메뉴 등록, 메뉴 정보 확인) -->
    <!-- <MenuManagement></MenuManagement> -->
    <!-- 가게 메뉴 등록 -->
    <AddStoreMenu></AddStoreMenu>
    <!-- 가게 정보 등록 -->
    <AddStoreInfo></AddStoreInfo>

    <!-- 리뷰 확인+ 댓글 -->
    <AddReviewComment></AddReviewComment>
  </v-bottom-navigation>
</template>


<script>
import MenuManagement from "../components/MenuManagement";
import AddStoreInfo from "../components/AddStoreInfo";
import AddReviewComment from "../components/AddReviewComment";
import AddStoreMenu from "../components/AddStoreMenu";
import { mdiApps } from "@mdi/js";
import { mdiInformationVariant } from "@mdi/js";
import { mdiMessageTextOutline } from "@mdi/js";
export default {
  name: "bottomeNav",
  components: {
    MenuManagement,
    AddStoreInfo,
    AddReviewComment,
    AddStoreMenu
  },
  props: {},
  data() {
    return {
      bottomNav: 3,
      activeBtn: 1,
      item: {
        title: "메뉴 관리",
        icon: "mdi-history"
      },
      items: [
        { title: "메뉴 관리", icon: mdiApps },
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