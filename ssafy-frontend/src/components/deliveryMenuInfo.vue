<template>
  <div>
    <v-container>
      <v-card elevation="1" width="80vw">
        <v-row>
          <v-col cols="8">
            <v-card-title class="display-1">{{name}}</v-card-title>
            <v-card-title class="display-2">{{cost}}</v-card-title>
            <v-card-text></v-card-text>
          </v-col>

          <v-col cols="4">
            <v-img :src="imageUrl" height="200px" aspect-ratio="3.0" />
          </v-col>
        </v-row>
      </v-card>
    </v-container>
  </div>
</template>

<script>
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
      tags: []
    };
  },
  mounted() {
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