<template>
  <v-layout row wrap mw-700>
    <v-flex v-for="i in cards.length > limits ? limits : cards.length" px-3 xs12 md6 lg3 :key="i">
      <menuCard style="cursor:pointer" :title="cards[i-1].title" :body="cards[i-1].body"></menuCard>
      <v-divider></v-divider>
    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" dark v-on:click="loadMorePosts">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import menuCard from "../components/menuCard";
export default {
  name: "menuList",
  props: {
    column: { type: Number, default: 1 },
    limits: { type: Number, default: 4 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      cards: []
    };
  },
  mounted() {
    this.getCard();
  },
  methods: {
    getCard() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.user}`;
      axios
        .get("http://192.168.100.92:8080/shopkeeper/countLikes")
        .then(response => {
          this.cards = response.data;
        });
    }
  }
};
</script>

<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>