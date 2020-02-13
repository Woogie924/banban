<template>
  <v-dialog v-model="dialog" hide-overlay transition="dialog-bottom-transition" xs12 lg6 md12 sm12>
    <template v-slot:activator="{ on }">
      <v-btn text outlined v-on="on">리뷰 쓰기</v-btn>
    </template>
    <v-card>
      <v-toolbar color="transparent">
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn text @click="submitReview()">완료</v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <v-layout class="text-center justify-center">
        <v-list>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title class="display-1 text-shadow font-weight-bold">식사는 어떠셨어요?</v-list-item-title>
              <v-list-item-subtitle class="font-weight-black text--grey">{{storename}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-rating v-model="rating" background-color="orange lighten-3" color="orange" large></v-rating>
            </v-list-item-content>
          </v-list-item>
          <v-list-item v-show="rating>0">
            <div class="pinched">
              <v-list-item-title class="text-left">
                <v-textarea
                  v-model="txt"
                  solo
                  clearable
                  clear-icon="cancel"
                  color="black"
                  label="가슴은 뜨겁게, 리뷰는 솔직하게 작성해주세요"
                ></v-textarea>
              </v-list-item-title>
            </div>
          </v-list-item>
        </v-list>
      </v-layout>
    </v-card>
  </v-dialog>
</template>

<script>
import store from "@/vuex/store.js";
import UserOrder from "../services/UserOrder";
export default {
  name: "WriteReview",
  props: {
    storename: { type: String },
    storeid: { type: String }
  },
  data() {
    return {
      dialog: false,
      rating: 0.0,
      txt: ""
    };
  },
  methods: {
    submitReview() {
      // 내 리뷰 객체 만들기
      //        "body": "string",
      //   "menu": "string",
      //   "regDate": "string",
      //   "starnum": 0,
      //   "starpoint": 0,
      //   "storeid": "string",
      //   "userid": "string"
      var reviewVO = {
        body: this.txt,
        menu: "메뉴",
        regDate: this.rating,
        starnum: this.rating,
        starpoint: this.rating,
        storeid: this.storeid,
        userid: this.$store.state.userName
      };
      UserOrder.setReview(reviewVO, response => {
        console.log(response);
        console.log("캬캬캬캬캬");
      });

      // dialog 초기화 및 닫기

      (this.rating = 0), (this.textarea = "");
      this.dialog = false;
    }
  }
};
</script>

<style scoped>
.v-dialog {
  position: absolute;
  bottom: 0;
  right: 0;
}
p {
  margin: 1em 0;
}
.pinched {
  position: relative;
  padding: 5px;
  margin: 10px 0 3em;
  text-align: center;
  color: #000000;
  background: #e6e6e6;
  width: 50vw;
  /* css3 */
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}
</style>