<template>
  <div>
    <v-container>
      <v-hover v-slot:default="{hover}" open-delay="200">
        <v-card
          :elevation="hover?16:0"
          style="cursor:pointer;
          border-bottom:1px dotted brown; border-top:1px dotted brown;"
          background-color="transparent"
          @click="moveSelectedMenu()"
          class="pa-2"
        >
          <v-row fluid class="align-center">
            <v-col cols="9" class="justify-center text-center">
              <v-card-text>
                <v-chip
                  v-for="idx in tags.length >=2 ? tags.length-1 : 1"
                  :key="idx"
                  class="ma-2"
                  :color="color[idx]"
                  label
                  dark
                  small
                >
                  <span>
                    <v-icon dark>{{svgPath}}</v-icon>
                  </span>
                  <span>{{tags[idx-1]}}</span>
                </v-chip>
              </v-card-text>
              <v-divider />
              <v-card-title class="font-weight-regular justify-center">{{name}}</v-card-title>
              <v-card-title class="font-weight-regular justify-center">· {{cost}}원</v-card-title>
            </v-col>

            <v-col cols="3">
              <v-card width="10em" height="10em" elevation="0">
                <v-img :src="imageUrl" contain height="10em" width="10em" />
              </v-card>
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
    storeId: { type: String },
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
          storeId: this.storeId,
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