<template>
  <div>
    <UserNavBar></UserNavBar>
    <br />
    <br />
    <br />
    <v-btn @click="get_info()">전체보기</v-btn>
    <v-btn @click="search('치킨')">치킨</v-btn>
    <v-btn @click="search('피자')">피자</v-btn>
    <v-btn @click="search('족발')">족발</v-btn>
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
    <v-btn small color="error" @click="write">글쓰기</v-btn>
    <v-btn small color="primary" @click="get_info">테스트</v-btn>
    <p>{{this.$store.state.user.data}}</p>
  </div>
</template>

<script>
import axios from "axios";
import test from "../services/test";
import UserNavBar from "@/components/UserNavBar.vue";
export default {
  components: {
    UserNavBar
  },
  name: "Read",
  data() {
    return {
      board: []
    };
  },

  methods: {
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
  }
  // beforeDestroy() {
  //   this.get_info();
  // }
};
</script>

<style>
</style>