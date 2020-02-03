<template>
  <div>
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
  </div>
</template>

<script>
import axios from "axios";
import test from "../services/test";
export default {
  name: "Detail",
  mounted() {
    this.get_info();
    window.console.log(localStorage.userName);
  },
  props: ["contentId"],
  data() {
    return {
      board: [],
      userName: localStorage.userName
    };
  },
  methods: {
    get_info(func) {
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