<template>
  <v-app>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>

<script>
import store from "./vuex/store";

export default {
  name: "App",
  store,
  components: {}
};
</script>
