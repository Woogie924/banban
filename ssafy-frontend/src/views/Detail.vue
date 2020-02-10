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
          <p>남은 파티원 수:{{this.party }}</p>

          <v-btn
            v-if="userName === board.writer"
            text
            icon
            color="red"
            @click="deleteTest(board.num)"
          >
            <v-icon>{{ icons.mdiDelete }}</v-icon>
          </v-btn>
          <v-btn
            v-if="userName === board.writer"
            text
            icon
            color="blue"
            @click="updateData(board.num)"
          >
            <v-icon>fas fa-edit</v-icon>
          </v-btn>
          <v-btn text icon color="green" @click="move()">
            <v-icon>fas fa-list</v-icon>
          </v-btn>
          <v-btn text icon color="green" @click="showcomment = !showcomment">
            <v-icon>fas fa-list</v-icon>
          </v-btn>
        </li>
      </ul>

      <v-navigation-drawer v-model="showcomment" absolute temporary right color="black" width="500">
        <template>
          <div v-for="(value, index) in comment" :key="index">
            <div align="right" v-if="board.writer ===  value.writer">
              <v-chip>{{value.body}}</v-chip>
              <v-btn
                text
                icon
                color="indigo"
                v-if="userName ===  value.writer"
                @click="comment_delete(value.cnum)"
              >
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>

              <v-btn
                v-if="userName === value.writer"
                text
                icon
                color="deep-orange"
                @click="tooltipActive = value.cnum"
              >
                <v-icon dark @click="tooltipActive = value.cnum">mdi-pencil</v-icon>
              </v-btn>

              <input v-if="tooltipActive == value.cnum" v-model="comment_update_body" />
              <v-btn v-if="tooltipActive == value.cnum" text icon color="green">
                <v-icon dark @click="comment_update(value.cnum, value.bnum)">mdi-pencil</v-icon>
              </v-btn>
            </div>
            <div align="left" v-if="board.writer !==  value.writer">
              <v-chip>{{value.body}}</v-chip>
              <v-btn
                v-if="userName ===  value.writer || userName === board.writer"
                @click="comment_delete(value.cnum)"
                text
                icon
                color="pink"
              >
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>
            </div>
          </div>
        </template>
        <input v-model="comment_body" placeholder="댓글 내용작성" color="dark" />
        <v-btn @click="comment_create()" text icon color="red">
          <v-icon>fas fa-edit</v-icon>
        </v-btn>
      </v-navigation-drawer>
    </div>
  </v-parallax>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";
import { mdiAccount, mdiPencil, mdiShareVariant, mdiDelete } from "@mdi/js";
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
      party_member: [this.$store.state.userName],
      board: [],
      userName: this.$store.state.userName,
      party: null,
      snackbar: false,
      comment: [],
      comment_body: "",
      comment_update_body: "",
      writer: "",
      tooltipActive: -1,
      showcomment: false,
      title: null,
      len: "",
      icons: {
        mdiAccount,
        mdiPencil,
        mdiShareVariant,
        mdiDelete
      }
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
        this.len = res.data.length;
        console.log(this.len);
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
        this.party_member = res.data.party;
        this.get_members();
      });
    },
    get_members() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "get",
        url: `http://192.168.100.92:8080/notice/getmembers/${this.board.num}`
      }).then(res => {
        this.party_member = res.data;
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
          writer: this.$store.state.userName,
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
          writer: this.$store.state.userName,
          reg_date: null
        }
      })
        .then(res => this.get_comment())
        .then((this.comment_update_body = ""))
        .then((this.tooltipActive = -1));
    },
    join_party(user) {
      this.party_member.push(user);
      this.party -= 1;
    }
  }
};
</script>

