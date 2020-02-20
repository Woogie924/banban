<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container fluid class="my-5">
      <v-layout class="justify-center">
        <v-flex xs12 lg4 md6 sm6>
          <div v-if="userName !== writer && index !==undefined">
            <p>올바른 접근 방식이 아닙니다</p>
            <v-btn small color="primary" @click="move()">글목록</v-btn>
            <v-snackbar v-model="snackbar">
              잘못된 접근입니다.{{ countDown}}초후 글목록 페이지로 이동합니다.
              <v-btn color="teal" @click="snackbar = false">close</v-btn>
            </v-snackbar>
          </div>
          <div v-else-if="index !==undefined">
            <v-select
              v-model="category"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="카테고리"
              required
            ></v-select>
            <v-text-field
              v-model="title"
              :counter="10"
              :rules="[v => !!v || 'Item is required']"
              label="제목"
              required
              placeholder="제목을 입력해주세요"
            ></v-text-field>
            <v-text-field
              v-model="body"
              :counter="100"
              :rules="[v => !!v || 'Item is required']"
              label="내용"
              required
              placeholder="내용을 입력해주세요"
            ></v-text-field>
            <v-text-field value="2" label="인원수" readonly></v-text-field>
            <v-btn
              color="red"
              text
              @click="index !== undefined? updated() : write()"
            >{{index !== undefined ? '수정': '작성'}}</v-btn>
            <v-btn color="teal" text @click="move()">글목록</v-btn>
            <v-snackbar v-model="snackbar" color="white">
              <div class="grey--text">여기는 게시글 수정 페이지 입니다.</div>
              <v-btn color="teal lighten-2" text @click="snackbar = false">close</v-btn>
            </v-snackbar>
          </div>
          <div v-else class="justify-center text-center">
            <v-select
              v-model="category"
              :items="items"
              :rules="[v => !!v || 'Item is required']"
              label="카테고리"
              required
            ></v-select>
            <v-text-field
              v-model="title"
              :counter="10"
              :rules="[v => !!v || 'Item is required']"
              label="제목"
              required
              placeholder="제목을 입력해주세요"
            ></v-text-field>
            <v-text-field
              v-model="body"
              :counter="100"
              :rules="[v => !!v || 'Item is required']"
              label="내용"
              required
              placeholder="내용을 입력해주세요"
            ></v-text-field>
            <v-text-field value="2" label="인원수" readonly></v-text-field>
            <v-btn text color="teal" @click="move()">
              <v-icon>fas fa-list</v-icon>목록
            </v-btn>
            <v-btn text color="red" @click="write()">
              <v-icon>fas fa-edit</v-icon>작성
            </v-btn>

            <v-snackbar v-model="snackbar">
              <div class="grey--text">여기는 게시글 작성 페이지 입니다.</div>
              <v-btn color="teal lighten-2" text @click="snackbar = false, move()">close</v-btn>
            </v-snackbar>
          </div>
        </v-flex>
      </v-layout>
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
      count: 2,
      party: null,
      snackbar: false,
      countDown: 3,
      items: ["치킨", "피자", "족발"],
      nameRules: [
        v => !!v || "Name is required",
        v => (v && v.length <= 10) || "Name must be less than 10 characters"
      ]
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
        url: `http://54.180.163.74:8080/notice/board/2`,
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
        url: `http://54.180.163.74:8080/notice/board/${this.party}`,
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