<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container my-5>
      <v-layout class="justify-center text-center">
        <v-flex lg6 md6 sm6 xs12>
          <WaitPayment></WaitPayment>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import WaitPayment from "../components/WaitPayment";
import UserNavBar from "../components/UserNavBar";
export default {
  name: "CompletePayPage",
  components: {
    WaitPayment,
    UserNavBar
  }
};
</script>

<style>
</style>