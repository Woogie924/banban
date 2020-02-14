<template>
  <div>
    <v-dialog
      v-model="dialog"
      hide-overlay
      transition="dialog-bottom-transition"
      max-width="50vw"
      :fullscreen="$vuetify.breakpoint.xs"
    >
      <template v-slot:activator="{ on }">
        <v-btn text outlined v-on="on" height="8vh">
          <span>가게 정보</span>
          <v-icon>mdi-book</v-icon>
        </v-btn>
      </template>
      <v-card height="150vh" width="100vw">
        <v-toolbar color="transparent">
          <v-btn icon @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn text @click="setStoreInfo()">완료</v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-container fluid class="text-center justify-center">
          <v-list>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title class="display-1 text-shadow font-weight-bold">사장님 광장</v-list-item-title>
                <v-list-item-subtitle class="font-weight-black text--grey">땅땅치킨</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="가게명"
                      v-model="storename"
                      clearable
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="store"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="카테고리"
                      v-model="category"
                      readonly
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="category"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="전화번호"
                      v-model="tel"
                      clearable
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="phone"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="최소주문금액"
                      v-model="minprice"
                      clearable
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="mdi-cash"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="배달시간"
                      v-model="deliverytime"
                      clearable
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="watch"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-text-field
                      label="배달팁"
                      v-model="deliverytip"
                      clearable
                      clear-icon="cancel"
                      color="black"
                      background-color="white"
                      prepend-icon="mdi-coin"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
            <v-list-item>
              <div class="pinched_TextArea">
                <v-row class="justify-center">
                  <v-col cols="12" sm="8" md="8" xs="12" lg="8">
                    <v-list-item-title class="text-center">
                      <v-textarea
                        label="가게소개"
                        clearable
                        clear-icon="cancel"
                        color="black"
                        background-color="white"
                        prepend-icon="speaker"
                        no-resize
                        counter="500"
                        v-model.lazy="storeintro"
                      ></v-textarea>
                    </v-list-item-title>
                  </v-col>
                </v-row>
              </div>
            </v-list-item>
          </v-list>
        </v-container>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import store from "@/vuex/store.js";
import shopkeeper from "../services/shopkeeper";
export default {
  name: "AddStoreInfo.vue",
  data() {
    return {
      dialog: false,
      myid: this.$store.state.userName,
      list: [],
      storename: "",
      category: "",
      tel: "",
      storeintro: "가나다라",
      minprice: "",
      deliverytime: "",
      deliverytip: ""
    };
  },
  mounted() {
    console.log("Addstoreinfo");
    this.getStoreInfo();
  },
  methods: {
    getStoreInfo() {
      shopkeeper.getMyStoreInfo(response => {
        console.log("AddStoreInfo getMyStoreInfo start");
        console.log(response.data);
        this.list = response.data;
        console.log(this.list);
        (this.storename = this.list.store.name),
          (this.category = "반반한동네>" + this.list.store.category),
          (this.tel = this.list.store.tel),
          (this.storeintro = this.list.storeintro),
          (this.minprice = this.list.minprice),
          (this.deliverytime = this.list.deliverytime),
          (this.deliverytip = this.list.deliverytip);
      });
    },
    setStoreInfo() {
      var storeInfoVO = {
        snum: this.list.snum,
        storeid: this.myid,
        minprice: this.minprice,
        deliverytip: this.deliverytip,
        deliverytime: this.deliverytime,
        storeintro: this.storeintro
      };
      shopkeeper.setMyStoreInfo(storeInfoVO, response => {
        console.log("ADDStoreInfo set StoreInfo completed");
        console.log(response);
        // this.storename = "";
        // this.category = "";
        // this.tel = "";
        // this.storeintro = "";
        // this.minprice = "";
        // this.deliverytime = "";
        // this.deliverytip = "";
        this.getStoreInfo();
      });
    }
  }
};
</script>

<style scoped>
.pinched {
  position: relative;
  padding: 5px;
  margin: 0 0 1em;
  text-align: center;
  color: #000000;
  background: #e6e6e6;
  width: 50vw;
  height: 13vh;
  /* css3 */
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}
.pinched_TextArea {
  position: relative;
  padding: 5px;
  margin: 0 0 1em;
  text-align: center;
  color: #000000;
  background: #e6e6e6;
  width: 50vw;
  height: 25vh;
  /* css3 */
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}
</style>