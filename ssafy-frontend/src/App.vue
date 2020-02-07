<template>
  <v-app>
    <v-content>
      <transition name="fade">
        <router-view />
      </transition>
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
<style >
.slide-fade-enter {
  transform: translateX(10px);
  opacity: 0;
}
.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: opacity 0.5s ease-out;
}
.fade-enter {
  opacity: 0;
}
.fade-enter-active {
  transition: opacity 0.5s ease-out;
}
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}
.fade-leave-to {
  opacity: 0;
}
</style>
