<template>
  <div>
    <v-toolbar dense>
      <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
    </v-toolbar>
    <v-card>
      <v-card-title>주문 가능</v-card-title>
      <v-divider></v-divider>

      <v-card-text v-for="value in this.$store.state.res" :key="value.index">{{value.name}}</v-card-text>
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