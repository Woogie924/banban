<template>
  <div>
    <v-card fluid>
      <p class="headline grey--text font-weight-bold text-center">실시간 순위</p>
      <v-list>
        <v-list-item-group v-model="list" color="primary">
          <v-list-item v-for="value in list" :key="value.index">
            <p class="headline font-italic grey--text font-weight-bold">{{value.index}}</p>
            <div id="rank">
              <v-list-title class="black--text">{{ value.title }}</v-list-title>
            </div>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "Ranking",
  data() {
    return {
      list: [
        { index: "1", title: "양념치킨" },
        { index: "2", title: "간장치킨" },
        { index: "3", title: "반반치킨" },
        { index: "4", title: "불고기피자" },
        { index: "5", title: "고구마피자" },
        { index: "6", title: "치킨1" },
        { index: "7", title: "치킨32" },
        { index: "8", title: "치킨3" },
        { index: "9", title: "치킨4" },
        { index: "10", title: "치킨5" }
      ]
    };
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
    left: 4vw;
  }
}

/* Standard syntax */
@keyframes mymove {
  from {
    left: 0px;
  }
  to {
    left: 4vw;
  }
}
</style>