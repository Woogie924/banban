<template>
  <div>
    <v-btn v-if="quantity > 0" dark fab text @click="checkIt()">
      <v-badge color="red" :content="quantity">
        <v-icon color="white">{{ icons.mdiBellRing }}</v-icon>
      </v-badge>
    </v-btn>
    <p style="display:none">{{count}}</p>
  </div>
</template>
<script>
import store from "@/vuex/store.js";
import { mdiBellRing } from "@mdi/js";
import router from "@/router.js";

export default {
  data() {
    return {
      quantity: 0,
      icons: {
        mdiBellRing
      }
    };
  },
  computed: {
    count() {
      return (this.quantity = store.state.order);
    }
  },
  methods: {
    checkIt() {
      this.$store.commit("CLEAR_ORDER");
      this.quantity = 0;
      this.$router.push("/StoreMainPage");
    }
  }
};
</script>

<style>
</style>