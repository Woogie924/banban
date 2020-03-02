<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-snackbar v-model="snackbar" color="white">
      <div class="grey--text">여기는 상세 게시글 페이지 입니다.</div>
      <v-btn color="teal lighten-2" text @click="snackbar = false">close</v-btn>
    </v-snackbar>
    <!-- Detail -->
    <br />

    <!-- 게시물 상세 정보 + 버튼 -->
    <v-layout class="justify-center">
      <v-card class="justify-center align-center">
        <v-list>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="text-shadow font-weight-bold my-2">
                <div class="underlined">번호</div>
              </v-list-item-title>
              <v-list-item-subtitle class="font-weight-black text--grey text-center">{{board.num}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="text-shadow font-weight-bold my-2">
                <div class="underlined">제목</div>
              </v-list-item-title>
              <v-list-item-subtitle class="font-weight-black text--grey text-center">{{board.title}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="text-shadow font-weight-bold my-2">
                <div class="underlined">내용</div>
              </v-list-item-title>
              <v-list-item-subtitle class="font-weight-black text--grey text-center">{{board.body}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="text-shadow font-weight-bold my-2">
                <div class="underlined">인원</div>
              </v-list-item-title>
              <v-list-item-subtitle
                class="font-weight-black text--grey text-center"
              >{{board.nowmember}}/{{board.party}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <div class="my-2">
                <v-btn
                  small
                  color="error"
                  v-if="this.state == false && board.party > board.nowmember"
                  @click="party_join(board.num, userName), showChat=!showChat"
                >파티참가하기</v-btn>
                <v-btn
                  small
                  color="error"
                  v-if="this.state == true && board.party > board.nowmember "
                  @click="party_out(board.num, userName)"
                >파티나가기</v-btn>
                <v-btn
                  small
                  color="error"
                  v-if="this.state == false && board.party <= board.nowmember "
                >지금은 모든 파티원이 구해졌습니다.</v-btn>
              </div>
            </v-list-item-content>
          </v-list-item>
          <v-list-item-action>
            <span style="inline">
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
              <v-btn
                v-if="userName === board.writer"
                text
                icon
                color="red"
                @click="deleteTest(board.num)"
              >
                <v-icon>{{ icons.mdiDelete }}</v-icon>
              </v-btn>
            </span>
          </v-list-item-action>
        </v-list>
      </v-card>
    </v-layout>

    <!-- 채팅 플로팅 아이콘 -->
    <v-hover v-model="hover" v-if="state == true">
      <div style="height:50vh; position:relative">
        <v-btn absolute dark fab top right color="teal lighten-3" @click="showChat=!showChat">
          <v-icon>mdi-message-text</v-icon>
        </v-btn>
      </div>
    </v-hover>

    <v-navigation-drawer
      v-model="showChat"
      right
      app
      width="30vw"
      height="90vh"
      style="padding-bottom: 64px; overflow:none;"
    >
      <div id="container" style="max-height:600px; ">
        <div v-for="(value, index) in comment" :key="index">
          <div v-if="value.bnum == board.num">
            <!-- 다른 유저일때 -->
            <v-row fluid v-if="value.writer != userName">
              <v-col>
                <div class="underlined">{{value.writer}}</div>

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
                <div class="underlined2">{{userName}}</div>
                <v-btn
                  v-if="userName === value.writer"
                  text
                  icon
                  color="red"
                  @click="comment_delete(value.cnum)"
                >
                  <v-icon>{{ icons.mdiDelete }}</v-icon>
                </v-btn>
                <v-card class="pr-12">
                  <v-card-text>{{value.body}}</v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </div>
        </div>
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
              @keyup.enter="test()"
            ></v-text-field>
            <v-btn text dark color="teal" rounded class @click="test()">전송</v-btn>
          </v-toolbar>
        </v-bottom-navigation>
      </div>
    </v-navigation-drawer>
  </div>
</template>
<script src="https://unpkg.com/vue/dist/vue.js"></script>
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
      state: false,
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
    async get_comment(func) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      await axios({
        method: "get",
        url: `http://54.180.163.74:8080/notice/comment/${this.contentId}`
      }).then(res => {
        this.comment = res.data;
        this.len = res.data.length;
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
        url: `http://54.180.163.74:8080/notice/board/${this.contentId}`
      }).then(res => {
        this.board = res.data;
        this.party_member = res.data.party;
        this.check();
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
        url: `http://54.180.163.74:8080/notice/board/${num}`
      }).then(res => this.move());
    },
    updateData(num) {
      this.$router.push({
        path: `../Create/${num}`
      });
    },
    async test() {
      await this.comment_create();
      this.scrollToEnd();
    },
    async comment_create() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      return await axios({
        method: "post",
        url: `http://54.180.163.74:8080/notice/comment`,
        data: {
          cnum: 0,
          bnum: this.contentId,
          body: this.comment_body,
          writer: this.$store.state.userName,
          reg_date: null
        }
      })
        .then(async res => await this.get_comment())
        .then(() => {
          this.comment_body = "";
        });
    },
    comment_delete(num) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "Delete",
        url: `http://54.180.163.74:8080/notice/comment/${num}`
      }).then(res => this.get_comment());
    },
    comment_update(cnum, bnum) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "PUT",
        url: `http://54.180.163.74:8080/notice/comment/`,
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
    addItem: function() {
      this.items.push("Item #" + this.items.length);
    },
    scrollToEnd: function() {
      var container = this.$el.querySelector("#container");
      container.scrollTop = container.scrollHeight;
    },
    party_join(bnum, username) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "POST",
        url: `http://54.180.163.74:8080/notice/members/${bnum}/${username}`
      }).then(res => this.get_info());
    },
    party_out(bnum, username) {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.token}`;
      axios({
        method: "DELETE",
        url: `http://54.180.163.74:8080/notice/members/${bnum}/${username}`
      }).then(res => this.get_info());
    },
    check() {
      this.state = false;
      for (var i in this.board.partymember) {
        if (this.board.partymember[i].id == this.userName) {
          this.state = true;
        }
      }
    }
  }
};
</script>
<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  position: relative;
  z-index: 1;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
}
.underlined::before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(to top, #36f3a4 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
.underlined2 {
  text-decoration: none;
  font-weight: bold;
  position: relative;
  z-index: 1;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
}
.underlined2::before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(to top, #b4e7f8 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
#container::-webkit-scrollbar {
  display: none;
}
</style>

