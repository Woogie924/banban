<template>
  <div>
    <!-- 점주전용 NavBar -->
    <v-layout my-5 absolute>
      <StoreNavBar></StoreNavBar>
    </v-layout>

    <!-- StorePayment.vue + OrderList.vue -->

    <v-container>
      
      <v-layout row wrap>
        <v-card elevation="6">
          <v-toolbar>
            <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
          </v-toolbar>
        </v-card>
      </v-layout>
    </v-container>

    <v-container>
      <v-layout my-5 row wrap>
        <v-flex xs12 sm6 md6 lg6>
          <StorePayment></StorePayment>
        </v-flex>

        <v-flex xs12 sm6 md4 lg4>
          <OrderList></OrderList>
        </v-flex>
      </v-layout>
    </v-container>
    <v-layout>
      <bottomNav></bottomNav>
    </v-layout>
  </div>
</template>


<script>
import StoreNavBar from "../components/StoreNavBar";
import StorePayment from "../components/StorePayment";
import OrderList from "../components/OrderList";
import StoreMenuList from "../components/StoreMenuList";
import MenuManagement from "../components/MenuManagement";
import bottomNav from "../components/bottomNav";
export default {
  name: "StoreMainPage",
  components: {
    StoreNavBar,
    StorePayment,
    OrderList,
    StoreMenuList,
    MenuManagement,
    bottomNav
  },
  methods: {},
  data() {
    return {};
  }
};
</script>

<style>
</style>