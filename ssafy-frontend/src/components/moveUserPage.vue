<template>
  <v-hover>
    <template v-slot:default="{hover}">
      <v-card max-width="400" @click="move">
        <v-img
          class="white--text align-end"
          height="200px"
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        >
          <v-card-title>일반 회원</v-card-title>
        </v-img>

        <v-card-subtitle class="pb-0">회원 전용입니다</v-card-subtitle>

        <v-card-text class="text--primary">
          <div>회원 전용</div>

          <div>Whitsunday Island, Whitsunday Islands</div>
        </v-card-text>

        <v-fade-transition>
          <v-overlay v-if="hover" absolute color="#036358">
            <v-btn>See more info</v-btn>
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
export default {
  name: "moveUserPage",
  props: {},
  methods: {
    move() {
      this.$router.push({
        path: "mlogin"
      });
    }
  }
};
</script>

<style>
</style>