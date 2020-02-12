<template>
  <div>
    <v-layout class="my-5" fluid>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-layout class="my-5 justify-center">
      <v-flex xs12 md3 lg3>
        <SelectMenu
          :a="menuInfo"
          :imageUrl="imageUrl"
          :name="name"
          :cost="cost"
          :tip="tip"
          :tag="tag"
        ></SelectMenu>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import SelectMenu from "../components/SelectMenu";
import UserNavBar from "../components/UserNavBar";
import router from "@/router.js";
export default {
  name: "SelectedMenu",
  components: {
    SelectMenu,
    UserNavBar
  },
  data() {
    return {
      menuInfo: [],
      imageUrl: this.$route.params.imageUrl,
      name: this.$route.params.name,
      cost: this.$route.params.cost,
      tip: this.$route.params.tip,
      tag: this.$route.params.tag
    };
  },
  props: {},
  created() {
    console.log("SelectedMenuPage Created");
  },
  mounted() {
    console.log("SelectedMenuPage mounted");
    this.imageUrl = this.$route.params.imageUrl;
    this.name = this.$route.params.name;
    this.cost = this.$route.params.cost;
    this.tip = this.$route.params.tip;
    this.tag = this.$route.params.tag;
  }
};
</script>

<style>
</style>