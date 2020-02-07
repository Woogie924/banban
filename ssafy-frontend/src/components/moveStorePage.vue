<template>
  <v-hover>
    <template v-slot:default="{hover}">
      <v-card max-width="400" @click="move">
        <v-img
          class="white--text align-end"
          height="200px"
          src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        >
          <v-card-title>사업자 회원</v-card-title>
        </v-img>

        <v-card-subtitle class="pb-0">사업자 전용페이지</v-card-subtitle>

        <v-card-text class="text--primary">
          <div>사업자 전용 페이지입니다...</div>
        </v-card-text>

        <v-fade-transition>
          <v-overlay v-if="hover" absolute color="#058">
            <v-btn>See more info</v-btn>
          </v-overlay>
        </v-fade-transition>
      </v-card>
    </template>
  </v-hover>
</template>

<script>
export default {
  name: "moveStorePage",
  props: {},
  methods: {
    move() {
      this.$router.push({
        path: "storelogin"
      });
    }
  }
};
</script>

<style>
</style>