<template>
  <div>
    <v-alert color="teal" dark :max-width="mySize" :value="value" dismissible dense>
      새로운 알림이 도착했습니다!
      <v-btn class="ma-2">동의</v-btn>
      <v-btn class="ma-2">거부</v-btn>
    </v-alert>
  </div>
</template>
<script>
import Velocity from "velocity-animate";
export default {
  name: "notification",
  props: {
    value: { type: Boolean }
  },
  data() {
    return {
      mySize: "50vw"
    };
  },
  create() {},
  mounted() {},
  methods: {
    resizing() {
      switch (this.$vuetify.breakpoint.name) {
        case "xs":
          return "100vw";
        case "lg":
          return "20vw";
        case "md":
          return "20vw";
        default:
          return "50vw";
      }
    }
  }
};
</script>
<style scoped>
.v-enter {
  /* starting style */
  opacity: 0;
}
.v-enter-active {
  /* active entering style */
  transition: opacity 2s ease-in;
}
.v-leave-active {
  /* active leaving style */
  transition: opacity 2s ease-out;
}
.v-leave-to {
  /* ending style */
  opacity: 0;
}
.fade-enter {
  opacity: 0;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}
.fade-leave-to {
  opacity: 0;
}
</style>





