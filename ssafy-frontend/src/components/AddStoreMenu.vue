<template>
  <v-dialog v-model="dialog2" max-width="800">
    <template v-slot:activator="{on}">
      <v-btn text large outlined v-on="on" height="5em">
        <span>메뉴 등록</span>
        <v-icon>{{iconPath}}</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>메뉴 등록</v-card-title>
      <v-card-text>
        <v-container>
          <v-flex>
            <v-text-field v-model="foodName" label="음식명" :rules="rules" hide-details="auto"></v-text-field>
            <v-text-field v-model="foodPrice" label="가격"></v-text-field>
          </v-flex>
          <v-flex>
            <v-textarea
              v-model="foodText"
              name="input-7-1"
              label="Default style"
              value="'반반한 동네'
                맛있게 먹는 방법! 
                주문 꿀팁!
                설명해주세요!!"
              hint="쓰면 쓸수록 손님이 몰려와요,,"
            ></v-textarea>
          </v-flex>
          <br />
          <v-flex>
            <v-spacer></v-spacer>
            <v-autocomplete
              v-model="values"
              :items="tags"
              outlined
              dense
              chips
              small-chips
              label="Outlined"
              multiple
            ></v-autocomplete>
          </v-flex>
          <v-flex>
            <div id="aa">
              <file-pond
                name="test"
                ref="pond"
                label-idle="Drop files here..."
                v-bind:allow-multiple="false"
                accepted-file-types="image/jpeg, image/png"
                :server="serverConfig"
                v-bind:files="myFiles"
                v-on:init="handleFilePondInit"
                v-bind:instantUpload="true"
                v-on:processFile="test"
              />
            </div>
            <!-- <button @click="test()">sssdsd</button> -->
          </v-flex>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" text @click="dialog2 = false">Close</v-btn>
        <v-btn color="green" text @click="submit">Submit</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
// Import Vue FilePond
import vueFilePond from "vue-filepond";

// Import FilePond styles
import "filepond/dist/filepond.min.css";

// Import FilePond plugins
// Please note that you need to install these plugins separately

// Import image preview plugin styles
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css";

// Import image preview and file type validation plugins
import FilePondPluginFileValidateType from "filepond-plugin-file-validate-type";
import FilePondPluginImagePreview from "filepond-plugin-image-preview";
// Create component

const FilePond = vueFilePond(
  FilePondPluginFileValidateType,
  FilePondPluginImagePreview
);

import axios from "axios";
import store from "@/vuex/store.js";
import menuCard from "../components/menuCard";
import test from "../services/test";
import { mdiApps } from "@mdi/js";
import { mdiReply } from "@mdi/js";

export default {
  name: "AddStoreMenu",
  components: {
    FilePond,
    menuCard
  },
  data() {
    return {
      iconPath: mdiApps,
      reply: mdiReply,
      // menuCard Component용
      childId: null,
      childName: null,
      childCost: null,
      childTip: null,
      childImg: null,
      list: [],
      // dialog용
      temp: "",
      dialog: false,
      dialog2: false,
      path: "패쓰,,",
      foodName: "",
      foodPrice: "",
      foodText: "",
      myId: "",
      tags: ["치킨", "피자", "야식", "혼밥"],
      values: ["치킨", "야식"],
      value: null,
      rules: [
        value => !!value || "반드시 입력해주세요.",
        value => (value && value.length >= 3) || "Min 3 characters"
      ],

      myFiles: [],
      serverConfig: {
        url: "http://192.168.100.92:8080/Img/insertImg/",
        process: {
          method: "POST",
          withCredentials: false,
          headers: { test: "zz" },
          onload: response => {
            // console.log("response:  " + response);
            // localStorage에 임시 경로 받아오기
            localStorage.setItem("path", response);
            return response;
          }
        }
      }
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    cancel() {
      this.dialog = !this.dialog;
      // childId = null;
      // childName = null;
      // childCost = null;
      // childTip = null;
      // childImg = null;
      // // dialog용
      // temp = "";
      // dialog = false;
      // dialog2 = false;
      // path = "패쓰,,";
      // foodName = "";
      // foodPrice = "";
      // foodText = "";
      // myId = "";
      // tags = ["치킨", "피자", "야식", "혼밥"];
      // values = ["치킨", "야식"];
      // value = null;
      // rules = [
      //   value => !!value || "반드시 입력해주세요.",
      //   value => (value && value.length >= 3) || "Min 3 characters"
      // ];
      // myFiles = [];
    },
    handleFilePondInit() {
      console.log("FilePond has initialized");

      // FilePond instance methods are available on `this.$refs.pond`
    },
    test(r) {
      // console.log(this.server);
      console.log("r :" + r);
    },
    // 내 아이디를 주고, 관련된 메뉴 객체 정보 받아오기
    getMyImage() {
      // console.log("getMyImage start" + this.myId);
      test.getImage(
        this.myId,
        response => {
          for (let index = 0; index < response.data.length; index++) {
            // console.log(response.data[index]);
            this.list[index] = response.data[index];
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
          // console.log(response);
          this.getMyImage();
        },
        errorcallback => {
          console.log("error:" + errorcallback);
        }
      );
    },
    submit() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;

      for (let index = 0; index < this.values.length; index++) {
        this.temp += this.values[index] + "<";
      }
      alert("태그 정보:" + this.temp);
      axios({
        method: "POST",
        url: "http://192.168.100.92:8080/shopkeeper/menu",
        data: {
          id: this.myId,
          name: this.foodName,
          cost: this.foodPrice,
          tip: this.foodText,
          tag: this.temp,
          img: localStorage.getItem("path")
        }
      }).then(response => {
        console.log("메뉴 등록 응답 : " + response);
        // console.log("메뉴 등록 응답(2) : " + response.data);
        alert("완료되었습니다");
      });
    }
  },

  props: ["flag", "icon", "start"]
};
</script>

<style>
</style>