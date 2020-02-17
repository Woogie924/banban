<template>
  <v-list-item>
    <v-card>
      <v-list>
        <v-list-item two-line>
          <v-list-item-content class="justify-center text-left">
            <v-card-title>최근 리뷰 {{items.starpoint.length}}개</v-card-title>
            <v-card-text>사장님댓글 {{items.storestarpoint.length}}개</v-card-text>
          </v-list-item-content>
          <v-list-item-content class="justify-right text-center">
            <WriteReview :storeid="items.storeid" :storename="items.storename"></WriteReview>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <v-divider />
      <v-list dense v-for="(item,idx) in items.starpoint" :key="idx">
        <v-list-item two-line>
          <v-list-item-content class="justify-center text-center">
            <v-list-item-title class="font-weight-bold my-0">
              <span>{{item.userid}}</span>
              <span class="grey--text text--lighten-1 caption">님</span>
            </v-list-item-title>
            <v-list-item-subtitle>
              <v-rating
                v-model="item.starpoint"
                class="my-0"
                color="yellow darken-1"
                small
                dense
                readonly
              ></v-rating>
              <div class="grey--text text--lighten-1 caption my-0">{{item.regDate}}</div>
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="mx-5">
          <v-list-item-icon>
            <v-icon>{{quoteopen}}</v-icon>
          </v-list-item-icon>
          <v-list-item-content class="my-0 mx-0 mr-3">
            <div class="text-center">{{item.body}}</div>
          </v-list-item-content>
          <v-list-item-icon>
            <v-icon>{{quoteclose}}</v-icon>
          </v-list-item-icon>
        </v-list-item>
        <v-list-item class="mx-2">
          <v-list-item-content class="my-0">
            <div v-for="(a,i) in items.storestarpoint" :key="i">
              <div class="pinched" v-show="a.starnum == item.starnum">
                <v-list-item-title class="text-left">
                  <div>사장님</div>
                  <div>{{a.regDate}}</div>
                  <p class="text-center">{{a.body}}</p>
                </v-list-item-title>
              </div>
            </div>
          </v-list-item-content>
        </v-list-item>
        <v-divider />
      </v-list>
    </v-card>
  </v-list-item>
</template>

<script>
import UserOrder from "../services/UserOrder";
import WriteReview from "../components/WriteReview";
import Vue from "vue";
import Vuetify from "vuetify";
import "@mdi/font/css/materialdesignicons.css";
import { mdiFormatQuoteClose, mdiFormatQuoteOpen } from "@mdi/js";
export default {
  name: "Review",
  props: {
    storeId: { type: String }
  },
  components: {
    WriteReview
  },
  data() {
    return {
      starPoint: 4.8,
      items: [],
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen
    };
  },
  mounted() {
    this.getStoreInfo();
  },
  methods: {
    getStoreInfo() {
      UserOrder.getStoreInfo(this.storeId, response => {
        this.items = response.data;
        console.log("UserOrder getStoreInfo");
        console.log(this.items);
      });
    }
  }
};
</script>

<style scoped>
p {
  margin: 1em 0;
}
.pinched {
  position: relative;
  padding: 5px;
  margin: 10px 0 3em;
  text-align: center;
  color: #000000;
  background: #e6e6e6;
  height: 100%;
  /* css3 */
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}

/* creates a rectangle of the colour wanted for the pointy bit */
.pinched:before {
  content: "";
  position: absolute;
  top: -20px;
  left: 50%;
  width: 100px;
  height: 20px;
  margin: 0 0 0 -50px;
  background: #e6e6e6;
}

/* creates a rounded rectangle to cover part of the rectangle generated above */
.pinched:after {
  content: "";
  position: absolute;
  top: -20px;
  left: 0;
  width: 50%;
  height: 20px;
  background: #fff;
  /* css3 */
  -webkit-border-bottom-right-radius: 15px;
  -moz-border-radius-bottomright: 15px;
  border-bottom-right-radius: 15px;
}

/* creates the other rounded rectangle */
.pinched > :first-child:before {
  content: "";
  position: absolute;
  top: -20px;
  right: 0;
  width: 50%;
  height: 20px;
  background: #fff;
  /* css3 */
  -webkit-border-bottom-left-radius: 15px;
  -moz-border-radius-bottomleft: 15px;
  border-bottom-left-radius: 15px;
}
</style>
