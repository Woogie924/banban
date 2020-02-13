<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container class="my-5">
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
        <p>
          <input v-model="party" placeholder="같이먹을 인원수 입력(본인포함)" />
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
        <p></p>
        <p>
          <input v-model="title" placeholder="title" />
        </p>
        <p>
          <input v-model="body" placeholder="body" />
        </p>
        <p>
          <input v-model="party" placeholder="인원수 입력(본인포함)" />
        </p>
        <v-btn text icon color="red" @click="write()">
          <v-icon>fas fa-edit</v-icon>
        </v-btn>
        <v-btn text icon color="green" @click="move()">
          <v-icon>fas fa-list</v-icon>
        </v-btn>
        <v-snackbar v-model="snackbar">
          여기는 게시글 작성 페이지 입니다.
          <v-btn color="pink" @click="snackbar = false, move()">close</v-btn>
        </v-snackbar>
      </div>
    </v-container>
  </div>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";
import axios from "axios";
import test from "../services/test";

import store from "@/vuex/store.js";
import UserNavBar from "../components/UserNavBar";
export default {
  name: "Create",
  components: {
    UserNavBar
  },
  mounted() {
    this.get_info();
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
      party: null,
      snackbar: false,
      countDown: 3
    };
  },
  methods: {
    ischeck() {
      if (this.userName != this.writer && this.index != null) {
        console.log(this.userName);
        console.log(this.writer);
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
        url: `http://192.168.100.92:8080/notice/board/${this.party}`,
        data: {
          category: this.category,
          title: this.title,
          body: this.body,
          writer: this.$store.state.userName
        }
      }).then(() => {
        this.$router.push({ name: "Read" });
      });
    },
    updated() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "put",
        url: `http://192.168.100.92:8080/notice/board/${this.party}`,
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
      this.userName = this.$store.state.userName;
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
                this.ischeck();
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