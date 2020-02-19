<template>
  <div>
    <v-card elevation="1">
      <v-card-title class="text-center justify-center">
        <div class="underlined">주문 가능</div>
      </v-card-title>
      <v-card elevation="0">
        <div v-if="this.$store.state.res.length === 0">
          <p>주문 가능한 매장이 없습니다.</p>
        </div>
        <div v-else>
          <div v-for="value in this.$store.state.res" :key="value.index">
            <div v-if="value.category === index">
              <v-card-text class="text-center justify-center">{{value.name}}</v-card-text>
              <!-- <v-card-text v-for="value in this.$store.state.res" :key="value.index">{{value.name}}</v-card-text> -->
            </div>
          </div>
          <!-- <div v-if="num===0">
            <p>주문 가능한 매장이 없습니다.</p>
          </div>-->
        </div>
      </v-card>
    </v-card>
  </div>
</template>
<script>
import EventBus from "../EventBus";
export default {
  name: "OrderPossible",
  data() {
    return {
      index: "",
      num: 0
    };
  },
  mounted() {
    EventBus.$on("orderp", i => {
      this.index = i;
      console.log(this.index);
    });
  },
  methods: {
    plus() {
      this.num++;
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