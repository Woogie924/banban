<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>

    <v-card-title>
      <v-layout my-5>
        <v-container>
          <v-btn small text @click="get_info()">전체보기</v-btn>
          <v-btn small text @click="search('치킨')">치킨</v-btn>
          <v-btn small text @click="search('피자')">피자</v-btn>
          <v-btn small text @click="search('족발')">족발</v-btn>
          <v-snackbar v-model="snackbar">
            여기는 게시글 페이지 입니다.
            <v-btn color="pink" @click="snackbar = false">close</v-btn>
          </v-snackbar>
        </v-container>
      </v-layout>
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search_find"
        append-icon="search"
        label="search_find"
        single-line
        hide-details
      ></v-text-field>
    </v-card-title>

    <v-data-table :headers="headers" :items="board" :search="search_find" class="elevation-1">
      <template v-slot:item.distance="{ item }">
        <p>{{Math.floor(item.distance + 1) }} km이내</p>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon text icon color="pink" @click="detail(item.num)">fas fa-search</v-icon>
      </template>
    </v-data-table>

    <v-btn text icon color="pink" @click="write">
      <v-icon>fas fa-edit</v-icon>
    </v-btn>

    <v-btn @click="move">메인페이지로 이동하기</v-btn>
  </div>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";
import axios from "axios";
import test from "../services/test";
import UserNavBar from "../components/UserNavBar";
import router from "@/router.js";
import store from "@/vuex/store.js";
export default {
  components: {
    UserNavBar
  },
  name: "Read",
  data() {
    return {
      board: [],
      snackbar: false,
      search_find: "",
      headers: [
        {
          text: "category",
          align: "left",
          sortable: false,
          value: "category"
        },
        { text: "등록일", value: "regDate" },
        { text: "거리", value: "distance" },
        { text: "제목 ", value: "title" },
        { text: "남은 인원수 ", value: "remain" },
        { text: "작성자", value: "writer" },
        { text: "자세히 보기", value: "action", sortable: false }
      ],
      dialog: true
    };
  },

  methods: {
    test() {},
    move() {
      this.$router.push({
        path: "UserMainpage"
      });
    },
    start() {
      this.snackbar = true;
    },
    write() {
      this.$router.push({
        path: "Create/"
      });
    },
    detail(index) {
      this.$router.push({
        path: `/Detail/${index}`
      });
    },
    get_info() {
      test.backendService(
        res => {
          this.board = res;
        },
        error => {}
      );
    },
    search(category) {
      test.backendService(
        res => {
          this.board = [];
          for (var b in res) {
            if (res[b].category == category) this.board.push(res[b]);
          }
        },
        error => {}
      );
    }
  },
  mounted() {
    this.get_info();
    this.start();
    if (this.$store.state.userType !== 1) {
      alert("권한이 없습니다. 로그인해주세요");
      this.$router.push("/Mlogin");
    }
  }
};
</script>

<style>
</style>