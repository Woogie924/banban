<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-layout my-5>
      <v-container fluid>
        <v-layout wrap>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <Ranking></Ranking>
          </v-flex>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <Menu></Menu>
          </v-flex>
          <v-flex pa-2 xs="12" sm="4" lg="4" md="4">
            <OrderPossible></OrderPossible>
          </v-flex>
        </v-layout>
      </v-container>
    </v-layout>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import Ranking from "../components/Ranking";
import Menu from "../components/Menu";
import OrderPossible from "../components/OrderPossible";
import temp from "../components/temp";
export default {
  name: "UserMainPage",
  components: {
    Ranking,
    Menu,
    UserNavBar,
    OrderPossible,
    temp
  },
  methods: {
    test() {
      console.log(localStorage.user);
    }
  }
};
</script>

<style>
</style>