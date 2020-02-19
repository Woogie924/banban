<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container>
      <v-layout row wrap justify-center fluid lg6 md6 xs12>
        <MyCartList style="width:30vw ;"></MyCartList>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import MyCartList from "../components/MyCartList";
import router from "@/router.js";
export default {
  name: "MyCartPage",
  components: {
    MyCartList,
    UserNavBar
  },

  data() {
    return {
      totalprice: this.$route.params.totalprice
    };
  }
};
</script>

<style>
</style>