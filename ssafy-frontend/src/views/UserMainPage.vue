<template>
  <div>
    <v-layout my-5>
      <UserNavBar></UserNavBar>
    </v-layout>
    <v-container fluid my-5>
      <v-row no-gutters>
        <v-col cols="6" xs="12" sm="2" md="2" lg="2">
          <!-- 검색창 -->
          <v-layout wrap pa-2>
            <v-flex>
              <v-toolbar dense>
                <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
              </v-toolbar>
            </v-flex>
            <br />
            <v-flex>
              <Ranking></Ranking>
            </v-flex>
          </v-layout>
        </v-col>
        <v-col cols="12" xs="12" sm="4" lg="4" md="4">
          <v-layout pa-2>
            <v-flex>
              <Menu></Menu>
            </v-flex>
          </v-layout>
        </v-col>
        <v-col cols="12" xs="12" sm="4" lg="4" md="4">
          <v-layout pa-2>
            <OrderPossible></OrderPossible>
          </v-layout>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import UserNavBar from "../components/UserNavBar";
import Ranking from "../components/Ranking";
import Menu from "../components/Menu";
import OrderPossible from "../components/OrderPossible";
export default {
  name: "UserMainPage",
  components: {
    Ranking,
    Menu,
    UserNavBar,
    OrderPossible
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