<template>
  <div>
    <v-card elevation="24">
      <v-card>
        <v-card-title>주문 가능</v-card-title>
        <v-divider></v-divider>
        <div v-if="this.$store.state.res.length === 0">
          <p>주문 가능한 매장이 없습니다.</p>
        </div>
        <div v-else>
          <div v-for="value in this.$store.state.res" :key="value.index">
            <div v-if="value.category === index">
              <v-card-text>{{value.name}}</v-card-text>
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

<style>