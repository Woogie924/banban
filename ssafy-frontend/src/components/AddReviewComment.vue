<template>
  <v-dialog
    v-model="dialog"
    hide-overlay
    transition="dialog-bottom-transition"
    max-width="48vw"
    :fullscreen="$vuetify.breakpoint.xs"
  >
    <template v-slot:activator="{ on }">
      <v-btn text outlined v-on="on" height="5em">
        <span>리뷰</span>
        <v-icon>{{iconPath}}</v-icon>
      </v-btn>
    </template>
    <v-card max-width="48vw">
      <v-toolbar color="transparent" dense fixed>
        <v-btn icon @click="dialog = false">
          <v-icon>{{reply}}</v-icon>
        </v-btn>
      </v-toolbar>
      <v-divider />
      <v-list dense v-for="(item,idx) in items.starpoint" :key="idx">
        <v-list-item three-line>
          <v-list-item-content class="justify-center text-center">
            <v-row class="align-center">
              <v-col cols="3">
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
              </v-col>
              <v-col cols="7">
                <span>
                  <v-icon>{{quoteopen}}</v-icon>
                  <span>{{item.body}}</span>
                  <v-icon>{{quoteclose}}</v-icon>
                </span>
              </v-col>
            </v-row>
          </v-list-item-content>
        </v-list-item>
        <!-- 댓글 - 사장님 댓글 매칭 -->
        <div v-for="(a,i) in items.storestarpoint" :key="i">
          <v-list-item v-show="a.starnum == item.starnum">
            <v-list-item-content class="my-0">
              <div class="pinched">
                <div>
                  <v-list-item-title class="text-left">
                    <div>
                      <div>사장님</div>
                      <div>{{a.regDate}}</div>
                    </div>
                  </v-list-item-title>
                  <div>
                    <p>{{a.body}}</p>
                  </div>
                </div>
              </div>
            </v-list-item-content>
          </v-list-item>
        </div>
        <v-row class="justify-center align-center">
          <v-col cols="10">
            <v-text-field
              label="댓글"
              v-model="comment"
              color="black"
              background-color="white"
              prepend-icon="mdi-subdirectory-arrow-right"
              maxlength="50"
              counter
              @keyup.enter="writeComment(item)"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-divider />
      </v-list>
    </v-card>
  </v-dialog>
</template>

<script>
import store from "@/vuex/store.js";
import shopkeeper from "../services/shopkeeper.js";
import { mdiFormatQuoteClose, mdiFormatQuoteOpen } from "@mdi/js";
import { mdiMessageTextOutline } from "@mdi/js";
import { mdiReply } from "@mdi/js";
export default {
  name: "AddReviewComment",
  data() {
    return {
      iconPath: mdiMessageTextOutline,
      reply: mdiReply,
      dialog: false,
      myid: this.$store.state.userName,
      list: [],
      starPoint: 4.8,
      items: [],
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen,
      comment: ""
    };
  },
  mounted() {
    console.log("[ADDReview Comment Component] mounted");
    this.getStoreInfo();
  },
  methods: {
    writeComment(item) {
      console.log("writeComment");
      console.log(item);
      console.log(this.comment);
      shopkeeper.setComment(item, this.comment, res => {
        this.comment = "";
        this.getStoreInfo();
      });
    },
    getStoreInfo() {
      shopkeeper.getMyStoreInfo(response => {
        console.log("[AddReviewComment Component]UserOrder getStoreInfo");
        console.log(this.items);
        console.log(response.data);
        this.items = response.data;
      });
    }
  }
};
</script>

<style scoped>
.pinched {
  position: relative;
  padding: 5px;
  margin: 0px 0 0em;
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