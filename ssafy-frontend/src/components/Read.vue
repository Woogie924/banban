<template>
  <div>
    <v-btn @click="get_info()">전체보기</v-btn>
    <v-btn @click="search('치킨')">치킨</v-btn>
    <v-btn @click="search('피자')">피자</v-btn>
    <v-btn @click="search('족발')">족발</v-btn>
    <v-snackbar v-model="snackbar">
      여기는 게시글 페이지 입니다.
      <v-btn color="pink" @click="snackbar = false">close</v-btn>
    </v-snackbar>
    <v-simple-table dark>
      <template v-slot:default>
        <thead>
          <tr>
            <td>num</td>
            <td>category</td>
            <td>주소</td>
            <td>제목</td>
            <td>글내용</td>
            <td>작성자</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(value, index) in board" @click="detail(value.num)" :key="index">
            <td>{{value.num}}</td>
            <td>{{value.category}}</td>
            <td>{{value.address}}</td>
            <td>{{value.title}}</td>
            <td>{{value.body}}</td>
            <td>{{value.writer}}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <v-btn text icon color="pink" @click="write">
      <v-icon>fas fa-edit</v-icon>
    </v-btn>
    <v-btn small color="primary" @click="get_info">테스트</v-btn>
    <v-btn color="success" class="mt-12" @click="overlay = !overlay">Show Overlay</v-btn>

    <v-btn @click="move">메인페이지로 이동하기</v-btn>
  </div>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";
import axios from "axios";
import test from "../services/test";
import router from "@/router.js";
import store from "@/vuex/store.js";
export default {
  components: {},
  name: "Read",
  data() {
    return {
      board: [],
      snackbar: false
    };
  },

  methods: {
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
        path: `Detail/${index}`
      });
    },
    get_info() {
      test.backendService(
        res => {
          console.log(res);
          this.board = res;
          window.console.log(this.board);
        },
        error => {}
      );
    },
    search(category) {
      window.console.log(category);
      test.backendService(
        res => {
          this.board = [];
          for (var b in res) {
            window.console.log(res[b]);
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
    // beforeDestroy() {
    //   this.get_info();
    // }
  }
};
</script>

<style>
</style>