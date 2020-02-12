<template>
  <div>
    <v-container>
      <v-hover v-slot:default="{hover}" open-delay="200">
        <v-card
          :elevation="hover?16:1"
          width="80vw"
          style="cursor:pointer;"
          background-color="transparent"
          @click="moveSelectedMenu()"
        >
          <v-row fluid>
            <v-col cols="8" fluid>
              <v-container>
                <v-card-title id="title" class="display-2">
                  {{name}}
                  <v-card-text>
                    <v-btn
                      v-for="idx in tags.length >=2 ? tags.length-1 : 1"
                      :key="idx"
                      label
                      :color="color[idx]"
                      dark
                      class="ma-2"
                    >
                      <v-icon dark left large>{{svgPath}}</v-icon>
                      {{tags[idx-1]}}
                    </v-btn>
                  </v-card-text>
                </v-card-title>
                <v-card-title id="body" class="display-1">{{cost}}</v-card-title>
              </v-container>
            </v-col>
            <v-col cols="4">
              <v-layout style="max-width: 200px;
    margin: auto;">
                <v-img :src="imageUrl" height="200px" aspect-ratio="3.0" />
              </v-layout>
            </v-col>
          </v-row>
        </v-card>
      </v-hover>
    </v-container>
  </div>
</template>

<script>
import router from "@/router.js";
import { mdiMusicAccidentalSharp } from "@mdi/js";
export default {
  name: "deliveryMenuInfo",
  props: {
    imageUrl: { type: String },
    name: { type: String },
    cost: { type: Number },
    tip: { type: String },
    tag: {}
  },
  data() {
    return {
      color: ["indigo", "orange", "primary", "green", "teal"],
      tags: [],
      hover: false,
      svgPath: mdiMusicAccidentalSharp
    };
  },
  created() {
    // console.log("menuinfo created");
  },
  mounted() {
    // console.log("menuinfo mounted");
    this.splitTag();
  },
  updated() {
    // console.log("menuinfo updated");
  },
  methods: {
    splitTag() {
      this.tags = this.tag.split("<");
      //   alert(tags);
    },
    moveSelectedMenu() {
      this.$router.push({
        name: "SelectedMenuPage",
        params: {
          //   imageUrl: that.imageUrl,
          imageUrl: this.imageUrl,
          name: this.name,
          cost: this.cost,
          tip: this.tip,
          tag: this.tag
        }
      });
    }
  }
};
</script>

<style scoped>
#title {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  white-space: normal;
  line-height: 1;
  height: 1.4em;
  text-align: left;
  word-wrap: break-word;
  width: 100%;
  height: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
#body {
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
  white-space: normal;
  line-height: 1;
  height: 1.4em;
  text-align: left;
  word-wrap: break-word;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>