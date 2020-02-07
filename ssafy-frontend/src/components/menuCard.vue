<template>
  <v-layout py-6 h-100>
    <v-card class="mx-auto" max-width="344">
      <v-img :src="imageUrl" height="200px" aspect-ratio="3.0" />

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
          <v-card-text>
            <v-btn v-for="i in tags.length-1" :key="i" label :color="color[i]" dark class="ma-2">
              <v-icon dark left large>{{svgPath}}</v-icon>
              {{tags[i-1]}}
            </v-btn>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-layout>
</template>

<script>
import { mdiMusicAccidentalSharp } from "@mdi/js";
export default {
  name: "menuCard",
  props: {
    imageUrl: { type: String },
    name: { type: String },
    cost: { type: Number },
    tip: { type: String },
    tag: {}
  },
  data() {
    return {
      show: false,
      tags: [],
      color: ["indigo", "orange", "primary", "green", "teal"],
      svgPath: mdiMusicAccidentalSharp
    };
  },
  mounted() {
    // console.log(this.imageUrl + "-menuCard image");
    this.splitTag();
  },
  methods: {
    splitTag() {
      this.tags = this.tag.split("<");
      //   alert(tags);
    }
  }
};
</script>

<style>
</style>

