<template>
  <div>
    <v-btn dark color="teal lighten-3" @click="checkIt()">
      <v-badge color="red" :content="quantity"></v-badge>
    </v-btn>
    <p style="display:none">{{count}}</p>
  </div>
</template>

<script>
import store from "@/vuex/store.js";
export default {
  data() {
    return {
      quantity: 0
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
    }
  }
};
</script>

<style>
</style>