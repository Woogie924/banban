<template>
  <div>
    <v-card elevation="24">
      <v-card>
        <v-card-title>주문 가능</v-card-title>
        <v-divider></v-divider>

        <v-card-text v-for="value in this.$store.state.res" :key="value.index">{{value.name}}</v-card-text>
      </v-card>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "OrderPossible"
};
</script>

<style>
</style>