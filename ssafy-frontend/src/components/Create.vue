<template>
  <div v-if="userName !== writer && index !==undefined">
    <p>올바른 접근 방식이 아닙니다.</p>
    <v-btn small color="primary" @click="move()">글목록</v-btn>
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
  </div>
</template>

<script>
import axios from "axios";
import test from "../services/test";
import Read from "./Read";
export default {
  name: "Create",
  mounted() {
    this.get_info();
    console.log(this.userName + "userName");
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
      body: ""
    };
  },
  methods: {
    move() {
      this.$router.push({
        name: "Read"
      });
    },
    write() {
      axios({
        method: "post",
        url: "http://192.168.100.92:8080/notice/board",
        data: {
          category: this.category,
          address: this.address,
          title: this.title,
          body: this.body,
          writer: localStorage.userName
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
                console.log(112312312312312);
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