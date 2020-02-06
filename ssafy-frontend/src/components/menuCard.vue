<template>
  <v-layout py-6 h-100>
    <v-card class="mx-auto" max-width="344">
      <v-img :src="imageUrl" />

      <v-card-title>{{name}}</v-card-title>
      <v-card-title>{{imageUrl}}</v-card-title>

      <v-card-subtitle>{{cost}}원</v-card-subtitle>

      <v-spacer></v-spacer>
      <v-card-actions>
        <v-btn icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>{{tip}}</v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-layout>
</template>

<script>
export default {
  name: "menuCard",
  props: {
    imageUrl: { type: String },
    name: { type: String },
    cost: { type: Number },
    tip: { type: String }
    // tag: { type: String }
  },
  data() {
    return {
      show: false
    };
  },
  mounted() {
    console.log(this.imageUrl + "-menuCard image");
  },
  methods: {}
};
</script>

<style>
</style>

