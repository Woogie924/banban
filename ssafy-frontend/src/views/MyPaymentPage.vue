<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>

    <v-container class="my-5">
      <!-- <transition appear @before-enter="beforeEnter" @enter="enter" :css="false"> -->
      <!-- <div class="card" v-if="show">
          <p>하하하하</p>
      </div>-->
      <v-layout class="justify-center text-center">
        <v-flex lg6 sm6 md6 xs12>
          <MyPaymentInfo
            :storeid="storeid"
            :num="num"
            :price="price"
            :default_price="default_price"
            :total_price="total_price"
          ></MyPaymentInfo>
        </v-flex>
      </v-layout>

      <!-- </transition> -->
    </v-container>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import MyPaymentInfo from "../components/MyPaymentInfo";
import Velocity from "velocity-animate";
export default {
  name: "MyPaymentPage",
  components: {
    UserNavBar,
    MyPaymentInfo
  },
  data() {
    return {
      storeid: null,
      list: [],
      num: 0,
      price: 0,
      default_price: 0,
      total_price: 0
    };
  },
  mounted() {
    console.log("결제 정보 확인 및 결제!!! MyPaymentPage mounted");
    this.storeid = this.$route.params.storeid;
    this.list = this.$route.params.list;
    this.num = this.$route.params.num;
    this.price = this.$route.params.price;
    this.default_price = this.$route.params.default_price;
    this.total_price = this.$route.params.total_price;
    console.log("ㅑ캬캬캬캬" + this.storeid);
  },
  methods: {
    beforeEnter(el) {
      el.style.opacity = 0;
      el.style.width = "0em";
    },
    enter(el, done) {
      Velocity(
        el,
        { opacity: 1, width: "12em", rotateZ: "3deg" },
        { duration: 1000, easing: [70, 8], complete: done }
      );
    }
  },
  computed: {}
};
</script>
<style scoped>
.card {
  height: 4em;
  width: 12em;
  border-radius: 1%;
  background-color: #e0e0e0;
  box-shadow: 0.08em 0.03em 0.4em #ababab;
  padding-top: 1em;
}
</style>