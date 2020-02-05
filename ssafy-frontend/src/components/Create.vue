<template>
  <div v-if="userName !== writer && index !==undefined">
    <p>올바른 접근 방식이 아닙니다</p>
    <v-btn small color="primary" @click="move()">글목록</v-btn>
    <v-snackbar v-model="snackbar">
      잘못된 접근입니다.{{ countDown}}초후 글목록 페이지로 이동합니다.
      <v-btn color="pink" @click="snackbar = false">close</v-btn>
    </v-snackbar>
  </div>
  <div v-else-if="index !==undefined">
    <p>
      <select v-model="category">
        <option disabled value>하나를 선택 해 주세용</option>
        <option>치킨</option>
        <option>피자</option>
        <option>족발</option>
      </select>
    </p>
    <p>
      <input v-model="title" placeholder="title" />
    </p>
    <p>
      <input v-model="body" placeholder="body" />
    </p>
    <v-btn
      small
      color="error"
      @click="index !== undefined? updated() : write()"
    >{{index !== undefined ? '수정': '작성'}}</v-btn>
    <v-btn small color="primary" @click="move()">글목록</v-btn>
    <v-snackbar v-model="snackbar">
      여기는 게시글 수정 페이지 입니다.
      <v-btn color="pink" @click="snackbar = false">close</v-btn>
    </v-snackbar>
  </div>
  <div v-else>
    <select v-model="category">
      <option disabled value>하나를 선택 해 주세용</option>
      <option>치킨</option>
      <option>피자</option>
      <option>족발</option>
    </select>
    <p>
      <input v-model="address" placeholder="address" />
    </p>
    <p>
      <input v-model="title" placeholder="title" />
    </p>
    <p>
      <input v-model="body" placeholder="body" />
    </p>
    <v-btn
      small
      color="error"
      @click="index !== undefined? updated() : write()"
    >{{index !== undefined ? '수정': '작성'}}</v-btn>
    <v-btn small color="primary" @click="move()">글목록</v-btn>
    <v-snackbar v-model="snackbar">
      여기는 게시글 작성 페이지 입니다.
      <v-btn color="pink" @click="snackbar = false">close</v-btn>
    </v-snackbar>
  </div>
</template>

<script>
import axios from "axios";
import test from "../services/test";
import Read from "./Read";
import store from "@/vuex/store.js";
export default {
  name: "Create",
  mounted() {
    this.get_info();
    this.ischeck();
    this.start();
  },

  data() {
    const index = this.$route.params.contentId;

    return {
      userName: "",
      board: [],
      num: "",
      index: index,
      category: "",
      address: "",
      writer: "",
      title: "",
      body: "",
      snackbar: false,
      countDown: 3
    };
  },
  methods: {
    ischeck() {
      if (this.userName != this.writer && this.index != null) {
        console.log(this.userName);
        console.log(1222222222222123123);
        this.countDownTimer();
      }
    },
    countDownTimer() {
      if (this.countDown > 0) {
        setTimeout(() => {
          this.countDown -= 1;
          this.countDownTimer();
        }, 1000);
      }
      if (this.countDown == 0) {
        this.move();
      }
    },
    start() {
      this.snackbar = true;
    },
    move() {
      this.$router.push({
        name: "Read"
      });
    },
    write() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "post",
        url: "http://192.168.100.92:8080/notice/board",
        data: {
          category: this.category,
          address: this.address,
          title: this.title,
          body: this.body,
          writer: this.$store.state.user.id
        }
      }).then(() => {
        this.$router.push({ name: "Read" });
      });
    },
    updated() {
      axios({
        method: "put",
        url: "http://192.168.100.92:8080/notice/board",
        data: {
          num: this.num,
          category: this.category,
          address: this.address,
          title: this.title,
          body: this.body,
          writer: this.writer
        }
      }).then(() => {
        this.$router.push({ name: "Read" });
      });
    },
    get_info() {
      this.userName = localStorage.userName;
      test.backendService(
        res => {
          this.board = res;
          if (typeof this.index !== "undefined") {
            for (var i = 0; i < res.length; i++) {
              if (res[i].num == this.index) {
                this.num = this.board[i].num;

                this.address = this.board[i].address;
                this.writer = this.board[i].writer;
                this.title = this.board[i].title;
                this.body = this.board[i].body;
              }
            }
          }
        },
        error => {}
      );
    }
  }
};
</script>

<style>
</style>