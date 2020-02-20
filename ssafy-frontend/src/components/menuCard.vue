<template>
  <v-container>
    <v-card max-width="344" elevation="0" style="border: 1px solid ;">
      <v-img :src="item.img" height="30vh" contain />
      <v-card-title class="text-center justify-center my-1">{{item.name}}</v-card-title>
      <v-card-subtitle class="text-center justify-center my-1">{{item.cost}}원</v-card-subtitle>

      <v-card-actions>
        <v-btn class="justify-right" icon @click="show = !show">
          <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="show">
          <v-divider></v-divider>

          <v-card-text>{{item.tip}}</v-card-text>
          <v-card-text>
            <v-btn v-for="i in tags.length-1" :key="i" label :color="color[i]" dark class="ma-2">
              <v-icon dark left large>{{svgPath}}</v-icon>
              {{tags[i-1]}}
            </v-btn>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
  </v-container>
</template>

<script>
import { mdiMusicAccidentalSharp } from "@mdi/js";
export default {
  name: "menuCard",
  props: {
    // imageUrl: { type: String },
    // name: { type: String },
    // cost: { type: Number },
    // tip: { type: String },
    // tag: {}
    item: {
      img: { type: Object },
      name: { type: String },
      cost: { type: Number },
      tip: { type: String },
      tag: {}
    }
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
    // console.log("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
    // console.log(this.item);
    console.log(this.item.imageUrl + "-menuCard image");
    this.splitTag();
    this.item.img = "http://192.168.100.92:8080/image/" + this.item.img;
    console.log(this.item.img);
  },
  methods: {
    splitTag() {
      this.tags = this.item.tag.split("<");
      //   alert(tags);
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Jua|Nanum+Gothic|Sunflower:300");
* {
  font-family: "Do Hyeon", sans-serif;
}
</style>

