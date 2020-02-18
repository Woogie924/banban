<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container>
      <v-layout my-5 wrap class="justify-center text-center">
        <v-flex lg12 md12 sm12 xs12>
          <v-btn large tile text @click="get_info()" class="pa-0">
            <div class="underlined">전체보기</div>
          </v-btn>
          <v-btn text @click="search('치킨')" class="pa-0">
            <div class="underlined">치킨</div>
          </v-btn>
          <v-btn text @click="search('피자')" class="pa-0">
            <div class="underlined">피자</div>
          </v-btn>
          <v-btn text @click="search('족발')" class="pa-0">
            <div class="underlined">족발</div>
          </v-btn>
        </v-flex>
        <v-flex lg12 md12 sm12 xs12 class="justify-center text-center">
          <v-card elevation="0" class="justify-right">
            <v-list>
              <v-list-item>
                <v-list-item-content>
                  <span style="inline;">
                    <v-text-field
                      v-model="search_find"
                      append-icon="search"
                      label="게시판 검색"
                      single-line
                      hide-details
                      color="teal"
                      style="inline"
                    ></v-text-field>
                  </span>
                </v-list-item-content>
                <v-list-item-content class="justify-right text-right">
                  <span style="inline">
                    <v-btn text large color="teal" @click="write">
                      <v-icon>fas fa-edit</v-icon>글 작성
                    </v-btn>
                  </span>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card>
        </v-flex>
        <v-flex lg12 md12 sm12 xs12>
          <v-data-table
            :headers="headers"
            :items="board"
            :search="search_find"
            class="elevation-0 my-0 py-0"
            style="border:1px solid;"
          >
            <template v-slot:item.distance="{ item }">
              <div>
                <p>{{Math.floor(item.distance + 1) }} km이내</p>
              </div>
            </template>
            <template v-slot:item.action="{ item }">
              <div class="mx-4">
                <v-icon text icon color="teal" @click="detail(item.num)">fas fa-search</v-icon>
              </div>
            </template>
          </v-data-table>
        </v-flex>
        <v-flex lg12 md12 sm12 xs12></v-flex>
      </v-layout>

      <!-- <v-btn @click="move">메인페이지로 이동하기</v-btn> -->
      <v-btn @click="test">카카오</v-btn>
      <v-snackbar v-model="snackbar">
        여기는 게시글 페이지 입니다.
        <v-btn color="pink" @click="snackbar = false">close</v-btn>
      </v-snackbar>
    </v-container>
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

        { text: "작성자", value: "writer" },
        { text: "남은인원수", value: "remain" },
        { text: "자세히 보기", value: "action", sortable: false }
      ],
      dialog: true
    };
  },

  methods: {
    test() {
      axios({
        method: "post",
        url: `http://192.168.100.92:8080/kakaoPay`
      }).then(res => {
        document.location.href = res.data;
      });
    },
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

<style scoped>
.underlined {
  text-decoration: none;
  font-weight: bold;
  font-size: 16px;
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
  background-image: linear-gradient(to top, #b4e7f8 25%, rgba(0, 0, 0, 0) 40%);
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>