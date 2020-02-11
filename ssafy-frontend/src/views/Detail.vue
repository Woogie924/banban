<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-snackbar v-model="snackbar">
      여기는 상세 게시글 페이지 입니다.
      <v-btn color="pink" @click="snackbar = false">close</v-btn>
    </v-snackbar>
    <!-- Detail -->
    <br />

    <div :model="board">
      <v-layout my-5 fluid v-for="item in board " :key="item">
        <v-flex class="display-1">
          <v-card>{{item}}</v-card>
        </v-flex>
      </v-layout>
    </div>

    <v-hover v-model="hover">
      <v-card-text style="height:100px; position:relative">
        <v-btn absolute dark fab top right color="teal lighten-3" @click="showChat=!showChat">
          <v-icon>mdi-message-text</v-icon>
        </v-btn>
      </v-card-text>
    </v-hover>

    <v-navigation-drawer
      v-model="showChat"
      right
      app
      width="30vw"
      height="90vh"
      style="padding-bottom: 64px;"
    >
      <!-- 다른 유저일때 -->
      <v-container id="scroll-target" style="max-height: 400px" class="overflow-y-auto">
        <div v-scroll:#scroll-target="onScroll" style="height: 400px;">
          <div v-for="(value, index) in comment" :key="index">
            <div v-if="value.bnum == board.num">
              <v-row fluid v-if="value.writer != userName">
                <v-col>
                  <v-avatar color="teal" size="48">
                    <span class="white--text headline">{{value.writer}}</span>
                  </v-avatar>
                  <v-card elevation="1" class="ps-12">{{value.body}}</v-card>
                  <v-btn
                    v-if="userName === value.writer"
                    text
                    icon
                    color="red"
                    @click="comment_delete(value.cnum)"
                  >
                    <v-icon>{{ icons.mdiDelete }}</v-icon>
                  </v-btn>
                </v-col>
                <v-col></v-col>
              </v-row>
              <!-- 나 일때 -->
              <v-row fluid v-else>
                <v-col></v-col>
                <v-col>
                  <v-card class="pr-12">
                    <v-avatar color="teal" size="48">
                      <span class="white--text headline">{{userName}}}</span>
                    </v-avatar>
                    <v-card-text>{{value.body}}</v-card-text>
                    <v-btn
                      v-if="userName === value.writer"
                      text
                      icon
                      color="red"
                      @click="comment_delete(value.cnum)"
                    >
                      <v-icon>{{ icons.mdiDelete }}</v-icon>
                    </v-btn>
                  </v-card>
                </v-col>
              </v-row>
            </div>
          </div>
        </div>
      </v-container>
      <!-- 채팅 텍스트 박스 -->
      <v-bottom-navigation absolute mt-1>
        <v-toolbar fluid elevation="0">
          <v-text-field
            v-model="comment_body"
            label="Outlined"
            dense
            filled
            placeholder="캬캬캬"
            outlined
            color="teal"
            @keyup.enter="comment_create()"
          ></v-text-field>
          <v-btn text dark color="teal" rounded class @click="comment_create()">전송</v-btn>
        </v-toolbar>
      </v-bottom-navigation>
    </v-navigation-drawer>

    <v-navigation-drawer v-model="showcomment" app clipped right color="blue" width="500">
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

    <ul>
      <li>
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
        <v-btn text icon color="red" @click="showcomment = !showcomment">
          <v-icon>fas fa-list</v-icon>
        </v-btn>
      </li>
    </ul>
  </div>
</template>

<script>
import "@fortawesome/fontawesome-free/css/all.css";
import { mdiAccount, mdiPencil, mdiShareVariant, mdiDelete } from "@mdi/js";
import axios from "axios";
import test from "../services/test";
import store from "@/vuex/store.js";
import UserNavBar from "../components/UserNavBar";
export default {
  name: "Detail",
  mounted() {
    this.get_info();
    this.start();
    this.get_comment();
  },
  components: {
    UserNavBar
  },
  props: ["contentId"],
  data() {
    return {
      hover: false,
      showChat: false,
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
      },
      offsetTop: 0
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
    },

    onScroll(e) {
      console.log(e.target.scrollTop);
      this.offsetTop = e.target.scrollBottom;
    }
  }
};
</script>

