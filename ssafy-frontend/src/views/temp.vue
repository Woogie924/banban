<template>
  <div>
    <v-card elevation="0" style="border:1px solid grey">
      <v-card-title class="justify-center">
        <div class="underlined">주문 완료!!</div>
      </v-card-title>
      <v-list>
        <v-list-item-title class="justify-right text-right">
          <v-chip outlined pill color="red">
            <v-icon left>mdi-server-plus</v-icon>주문대기
          </v-chip>
          <v-chip outlined pill color="teal">
            <v-icon left>mdi-wrench</v-icon>주문완료
          </v-chip>
        </v-list-item-title>

        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>주문 번호</span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">번호,,,</div>
            </v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>일시</span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">일시,,,</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>메뉴명</span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">치킨명,,</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>
                <div class="underlined2">누구누구</div>님 주소
              </span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">dsdsds</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>
                <div class="underlined2">ggg</div>님 주소
              </span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">주소ㅡ,</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title class="justify-center text-center">
              <v-icon>{{quoteopen}}</v-icon>
              <span>가격</span>
              <v-icon>{{quoteclose}}</v-icon>
            </v-list-item-title>
            <v-list-item-subtitle>
              <div class="pinched">50000원</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider />
      </v-list>
    </v-card>
  </div>
</template>

<script>
import { mdiFormatQuoteClose, mdiFormatQuoteOpen } from "@mdi/js";
import router from "@/router.js";
export default {
  name: "temp",
  data() {
    return { quoteclose: mdiFormatQuoteClose, quoteopen: mdiFormatQuoteOpen };
  },
  mounted() {},
  methods: {}
};
</script>

<style scoped>
.pinched {
  position: relative;
  padding: 5px;
  margin: 0px 0 0em;
  text-align: center;
  color: #000000;
  background: #e6e6e6;
  height: 100%;
  /* css3 */
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}

/* creates a rectangle of the colour wanted for the pointy bit */
.pinched:before {
  content: "";
  position: absolute;
  top: -20px;
  left: 50%;
  width: 100px;
  height: 20px;
  margin: 0 0 0 -50px;
  background: #e6e6e6;
}

/* creates a rounded rectangle to cover part of the rectangle generated above */
.pinched:after {
  content: "";
  position: absolute;
  top: -20px;
  left: 0;
  width: 50%;
  height: 20px;
  background: #fff;
  /* css3 */
  -webkit-border-bottom-right-radius: 15px;
  -moz-border-radius-bottomright: 15px;
  border-bottom-right-radius: 15px;
}

/* creates the other rounded rectangle */
.pinched > :first-child:before {
  content: "";
  position: absolute;
  top: -20px;
  right: 0;
  width: 50%;
  height: 20px;
  background: #fff;
  /* css3 */
  -webkit-border-bottom-left-radius: 15px;
  -moz-border-radius-bottomleft: 15px;
  border-bottom-left-radius: 15px;
}
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
  background-image: linear-gradient(to top, #23c984 10%, rgba(0, 0, 0, 0) 30%);
  position: absolute;
  left: 3px;
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
  background-image: linear-gradient(to top, #053b3b 5%, rgba(0, 0, 0, 0) 30%);
  position: absolute;
  left: 3px;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
}
</style>