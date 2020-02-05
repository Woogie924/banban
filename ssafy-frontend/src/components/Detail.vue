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
      <input v-model="comment_body" placeholder="댓글 내용작성" />
      <v-btn @click="comment_create()" small color="error">댓글 생성</v-btn>
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
              <v-btn
                v-if="userName ===  value.writer"
                @click="comment_delete(value.cnum)"
                small
                color="error"
              >댓글 삭제</v-btn>
              <v-btn v-if="userName === value.writer" @click="tooltipActive = value.cnum">댓글 수정</v-btn>
              <input
                v-if="tooltipActive == value.cnum"
                v-model="comment_update_body"
                placeholder="댓글 내용 수정"
              />
              <v-btn
                v-if="tooltipActive == value.cnum"
                @click="comment_update(value.cnum, value.bnum)"
                small
                color="error"
              >수정</v-btn>
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
      userName: this.$store.state.user.id,
      snackbar: false,
      comment: [],
      comment_body: "",
      comment_update_body: "",
      writer: "",
      tooltipActive: -1
    };
  },
  methods: {
    get_comment(func) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
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
      ] = `Bearer ${store.state.token}`;
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
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "Delete",
        url: `http://192.168.100.92:8080/notice/board/${num}`
      }).then(res => this.move());
    },
    updateData(num) {
      this.$router.push({
        path: `../Create/${num}`
      });
    },
    comment_create() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "post",
        url: `http://192.168.100.92:8080/notice/comment`,
        data: {
          cnum: 0,
          bnum: this.contentId,
          body: this.comment_body,
          writer: this.$store.state.user.id,
          reg_date: null
        }
      })
        .then(res => this.get_comment())
        .then((this.comment_body = ""));
    },
    comment_delete(num) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "Delete",
        url: `http://192.168.100.92:8080/notice/comment/${num}`
      }).then(res => this.get_comment());
    },
    comment_update(cnum, bnum) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "PUT",
        url: `http://192.168.100.92:8080/notice/comment/`,
        data: {
          cnum: cnum,
          bnum: bnum,
          body: this.comment_update_body,
          writer: this.$store.state.user.id,
          reg_date: null
        }
      })
        .then(res => this.get_comment())
        .then((this.comment_update_body = ""))
        .then((this.tooltipActive = -1));
    }
  }
};
</script>
