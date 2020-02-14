<template>
  <div>
    <v-card elevation="24">
      <v-card>
        <v-card-title>주문 가능</v-card-title>
        <v-divider></v-divider>
        <div v-if="this.$store.state.res.length === 0">
          <p>주문 가능한 매장이 없습니다.</p>
        </div>
        <div v-else>
          <v-card-text v-for="value in this.$store.state.res" :key="value.index">{{value.name}}</v-card-text>
        </div>
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