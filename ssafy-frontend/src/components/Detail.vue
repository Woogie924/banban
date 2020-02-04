<template>
  <v-parallax height="1000" src="https://cdn.vuetifyjs.com/images/parallax/material.jpg">
    <div>
      <v-snackbar v-model="snackbar">
        여기는 상세 게시글 페이지 입니다.
        <v-btn color="pink" @click="snackbar = false">close</v-btn>
      </v-snackbar>
      <h1>Detail</h1>
      <ul>
        <li>
          <h2>제목: {{board.title}}</h2>
          <p>글번호: {{board.num}}</p>
          <p>메뉴: {{board.category}}</p>
          <p>주소: {{board.address}}</p>

          <p>내용: {{board.body}}</p>
          <p>글쓴이 id: {{board.writer}}</p>

          <v-btn
            v-if="userName === board.writer"
            small
            color="error"
            @click="deleteTest(board.num)"
          >글삭제</v-btn>
          <v-btn
            v-if="userName === board.writer"
            small
            color="danger"
            @click="updateData(board.num)"
          >글수정</v-btn>
          <v-btn small color="primary" @click="move()">글목록</v-btn>
        </li>
      </ul>
      <h3>댓글</h3>
      <v-simple-table dark>
        <template v-slot:default>
          <thead>
            <tr>
              <td>num</td>
              <td>작성자</td>
              <td>댓글내용</td>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(value, index) in comment" :key="index">
              <td>{{value.cnum}}</td>
              <td>{{value.writer}}</td>
              <td>{{value.body}}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </div>
  </v-parallax>
</template>

<script>
import axios from "axios";
import test from "../services/test";
import store from "@/vuex/store.js";
export default {
  name: "Detail",
  mounted() {
    this.get_info();
    this.start();
    this.get_comment();
  },
  props: ["contentId"],
  data() {
    return {
      board: [],
      userName: localStorage.userName,
      snackbar: false,
      comment: []
    };
  },
  methods: {
    get_comment(func) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.user}`;
      axios({
        method: "get",
        url: `http://192.168.100.92:8080/notice/comment/${this.contentId}`
      }).then(res => {
        this.comment = res.data;
        console.log(this.comment);
      });
    },
    start() {
      this.snackbar = true;
    },
    get_info(func) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.user}`;
      axios({
        method: "get",
        url: `http://192.168.100.92:8080/notice/board/${this.contentId}`
      }).then(res => {
        this.board = res.data;
      });
    },
    move() {
      this.$router.push({
        name: "Read"
      });
    },
    deleteTest(num) {
      axios({
        method: "Delete",
        url: `http://192.168.100.92:8080/notice/board/${num}`
      }).then(res => this.move());
    },
    updateData(num) {
      this.$router.push({
        path: `../Create/${num}`
      });
    }
  }
};
</script>