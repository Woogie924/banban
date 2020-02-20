<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container>
      <v-layout my-5>
        <v-expansion-panels focusable elevation="0">
          <v-expansion-panel v-for="(value,index) in info" :key="index">
            <v-expansion-panel-header>{{value.orderDate}}</v-expansion-panel-header>
            <v-expansion-panel-content>{{value.storename}}</v-expansion-panel-content>
            <v-expansion-panel-content>{{value.menu}}</v-expansion-panel-content>
            <v-expansion-panel-content>{{value.price}}원</v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-layout>
    </v-container>
    <!-- <v-container>
    <v-card-text v-for="(value) in info" :key="value.idx">
      <div>{{value.orderDate}}</div>
      <div>{{vlaue.userid1}}</div>
      <div>{{vlaue.storeid}}</div>
    </v-card-text>
    </v-container>-->
  </div>
</template>

<script>
import UserNavBar from "@/components/UserNavBar.vue";
import store from "@/vuex/store.js";
import axios from "axios";
export default {
  name: "Orderl",
  components: {
    UserNavBar
  },
  data() {
    return {
      info: []
    };
  },
  mounted() {
    console.log("ㅎㅎㅎㅎ");
    this.test();
  },
  methods: {
    async test() {
      console.log("ㅋㅋ");
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      await axios({
        method: "get",
        url: `http://192.168.100.92:8080/api/order`
      }).then(res => {
        this.info = res.data;
        console.log("주문내역!!!");
        console.log(res.data);
      });
    }
  }
};
</script>

<style scoped>
.title {
  margin-left: 38%;
}
@import url("https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Jua|Nanum+Gothic|Sunflower:300");
* {
  font-family: "Do Hyeon", sans-serif;
}
.errorMessage {
  color: red;
}
</style>