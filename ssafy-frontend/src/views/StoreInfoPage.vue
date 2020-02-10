<template>
  <div>
    <v-layout class="my-5">
      <UserNavBar></UserNavBar>
    </v-layout>
    <!-- Store Info Component -->
    <v-container class="my-5" fluid>
      <deliveryInfo></deliveryInfo>
    </v-container>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import deliveryInfo from "../components/deliveryInfo";
export default {
  name: "StoreInfoPage",
  components: {
    UserNavBar,
    deliveryInfo
  },
  data() {
    return {};
  },
  methods: {
    getMyImage() {}
  }
};
</script>

<style>
</style>