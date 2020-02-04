<template>
  <div>
    <v-card fluid>
      <p class="headline grey--text font-weight-bold text-center">실시간 순위</p>
      <v-card>
        <v-card-text id="rankText" v-for="(value, idx) in list" :key="value.id">
          <div>
            <div class="rankNum font-italic grey--text font-weight-bold">{{idx+1}}</div>
            <div id="rank" class="rankValue black--text display-5">{{ value }}</div>
          </div>
        </v-card-text>
      </v-card>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
import store from "@/vuex/store.js";
export default {
  name: "Ranking",
  data() {
    return {
      list: []
    };
  },
  created() {},
  mounted() {
    this.fetchRank();

    this.interval = setInterval(
      function() {
        this.fetchRank();
      }.bind(this),
      3000
    );
  },
  methods: {
    fetchRank() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${store.state.user}`;
      axios
        .get("http://192.168.100.92:8080/shopkeeper/countLikes")
        .then(response => {
          this.list = [];
          for (let index = 0; index < response.data.length; index++) {
            this.$set(this.list, index, response.data[index].name);
          }
          console.log(this.list);
        });
    }
  }
};
</script>

<style scoped>
#rank {
  font-weight: bold;
  position: relative;
  -webkit-animation: mymove 5s infinite; /* Safari 4.0 - 8.0 */
  animation: mymove 5s infinite;
}
/* Safari 4.0 - 8.0 */
#rank {
  -webkit-animation-timing-function: ease;
}

/* Standard syntax */

#rank {
  animation-timing-function: ease;
}

/* Safari 4.0 - 8.0 */
@-webkit-keyframes mymove {
  from {
    left: 0px;
  }
  to {
    left: 5vw;
  }
}

/* Standard syntax */
@keyframes mymove {
  from {
    left: 0px;
  }
  to {
    left: 5vw;
  }
}
.rankNum,
.rankValue {
  display: inline-block;
  margin: 0;
}
#rankText {
  margin: 0;
  padding: 0;
}
</style>