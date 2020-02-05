<template>
  <v-layout py-6 h-100>
    <v-flex row>
      <div id="date" class="caption">{{formatedDate}}</div>
      <h2 id="title" class="color-333 headline font-weight-light">{{title}}</h2>
      <br />
      <br />
      <p id="body" class="mb-1 color-666 font-weight-light subheading">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  name: "menuCard",
  props: {
    title: { type: String },
    body: { type: String }
  },
  data() {
    return {};
  },
  mounted() {},
  methods: {}
};
</script>

<style>
.color-666 {
  color: #666;
}
.color-333 {
  color: #333;
}
.h-100 {
  height: 100%;
}
#title {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  white-space: normal;
  line-height: 1.2;
  height: 1.2em;
  text-align: left;
  word-wrap: break-word;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
#body {
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
  white-space: normal;
  line-height: 1.2;
  height: 4.8em;
  text-align: left;
  word-wrap: break-word;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
#date {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  white-space: normal;
  line-height: 1.2;
  height: 2.4em;
  text-align: left;
  word-wrap: break-word;
  width: 100%;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>

