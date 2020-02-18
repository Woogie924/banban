<template>
  <div>
    <v-layout class="my-5">
      <!-- <StoreNavBar></StoreNavBar> -->
    </v-layout>
    <v-layout class="my-5">
      <v-btn @click="show = !show">Card</v-btn>
    </v-layout>
    <v-layout class="my-5">
      <transition appear @before-enter="beforeEnter" @enter="enter" :css="false">
        <!-- <div class="card" v-if="show">
          <p>하하하하</p>
        </div>-->
        <MyPaymentInfo></MyPaymentInfo>
      </transition>
    </v-layout>
    <v-layout>
      <WaitPayment></WaitPayment>
    </v-layout>
  </div>
</template>

<script>
import StoreNavBar from "../components/StoreNavBar";
import MyPaymentInfo from "../components/MyPaymentInfo";
import WaitPayment from "../components/WaitPayment";
import Velocity from "velocity-animate";
export default {
  name: "MyPaymentPage",
  components: {
    StoreNavBar,
    MyPaymentInfo,
    WaitPayment
  },
  data() {
    return {
      show: false,
      dialogSize: null
    };
  },
  mounted() {
    this.dialogSize = this.dialogResizing;
    console.log(this.dialogSize);
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