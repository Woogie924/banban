<template>
  <div>
    <v-card elevation="0">
      <v-card-text>
        <v-layout mt-5 wrap>
          <v-flex v-for="index in list.length" :key="index" lg4 sm6 md6 xs12>
            <menuCard :item="list[index]"></menuCard>
          </v-flex>
        </v-layout>

        <!-- <menuCard :imageUrl="childImg" :name="childName" :cost="childCost" :tip="childTip"></menuCard> -->
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/vuex/store.js";
import menuCard from "../components/menuCard";
import test from "../services/test";
import { mdiApps } from "@mdi/js";
import { mdiReply } from "@mdi/js";
export default {
  name: "MenuManagement",
  components: {
    menuCard
  },
  data() {
    return {
      iconPath: mdiApps,
      reply: mdiReply,
      // menuCard Component용
      list: []
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    // 내 아이디를 주고, 관련된 메뉴 객체 정보 받아오기
    getMyImage() {
      console.log("MyMenuList getMyImage start" + this.myId);
      test.getImage(
        this.myId,
        response => {
          this.list = response.data;
          console.log("하하하하하");
          console.log(response.data);
          //   console.log(typeof response.data.imageUrl);
          for (let index = 0; index < this.list.length; index++) {
            //   console.log(response.data[index]);
            // this.list[index] = response.data[index];
            // console.log(typeof this.list[index].imageUrl);
            this.list[index].imageUrl += "";
          }
          // console.log("menuManage getmyImage " + this.list);
          // console.log(response.data[0].img);
          // this.childId = response.data[0].id;
          // this.childName = response.data[0].name;
          // this.childCost = response.data[0].cost;
          // this.childTip = response.data[0].tip;
          // this.childImg = "192.168.100.92:8080/" + response.data[0].img;
          // console.log("이미지 경로:" + this.childImg);
        },
        errorcallback => {
          console.log("error:" + errorcallback);
        }
      );
    },
    //  token이용해서, 내 아이디 받아오기
    getUserInfo() {
      test.getUserInfo(
        async response => {
          this.myId = await response.data.id;
          console.log("MyMenuList getUserInfo start");
          console.log(response);
          this.getMyImage();
        },
        errorcallback => {
          console.log("error:" + errorcallback);
        }
      );
    }
  }
};
</script>

<style>
</style>