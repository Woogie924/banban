<template>
  <div>
    <v-toolbar width="100%" absolute dense style="position:fixed;" color="#009688">
      <v-app-bar-nav-icon dark @click.stop="overlay = !overlay">
        <v-icon>{{menuIconPath}}</v-icon>
      </v-app-bar-nav-icon>

      <!-- <v-app-bar-nav-icon dark @click.stop="overlay = !overlay"></v-app-bar-nav-icon> -->
      <v-toolbar-title>
        <v-label>
          <router-link to="/StoreMainPage" tag="span" style="cursor:pointer;">
            <v-img src="../assets/홈3.png" width="300" text aspect-ratio="7.0"></v-img>
          </router-link>
        </v-label>
      </v-toolbar-title>
      <!-- 닉네임 -->
      <v-spacer>
        <span class="font-weight-bold">{{ userInfo }}사장님 공간</span>
      </v-spacer>
      <v-spacer></v-spacer>
      <!-- 검색바 -->
      <v-toolbar flat dense color="#009688" dark>
        <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
      </v-toolbar>
      <!-- 메뉴 -->
      <v-toolbar-items class="hidden-xs-only">
        <v-btn dark text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
        <v-btn dark text @click="logout">
          <v-icon left dark>folder_open</v-icon>로그아웃
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>
    <!-- 메뉴 아이콘 클릭 -->
    <v-overlay :value="overlay" opacity="0.8">
      <v-navigation-drawer
        v-model="overlay"
        absolute
        color="transparent"
        style="position:fixed;"
        width="100vw"
      >
        <v-toolbar width="100%" absolute dense color="transparent" style="position:fixed;">
          <v-icon @click.stop="overlay = !overlay" style="cursor:pointer;">mdi-reply</v-icon>

          <!-- mdi-arrow-left, mdi-reply -->
        </v-toolbar>
        <v-row fluid>
          <!-- 오버레이 메뉴리스트 -->
          <v-col cols="3">
            <v-layout>
              <v-flex>
                <v-toolbar width="100%" absolute dense color="transparent" style="position:fixed;">
                  <v-card color="white" light>
                    <v-icon
                      @click.stop="overlay = !overlay"
                      style="cursor:pointer;"
                      light
                    >{{leftArrowPath}}</v-icon>
                  </v-card>
                  <!-- mdi-arrow-left, mdi-reply -->
                </v-toolbar>
              </v-flex>
              <v-flex absolute fill-height>
                <v-card fluid color="transparent" elevation="0">
                  <v-list>
                    <v-list-item
                      v-for="(item,index) in menuItems"
                      :key="item.title"
                      style="cursor:pointer;"
                    >
                      <v-tooltip bottom>
                        <template v-slot:activator="{ on }">
                          <v-list-item-content v-on="on">
                            <v-list-item-title @click="getSubDirectory(item,index)">
                              <span class="display-1 text-shadow font-weight-bold text-center">
                                <div class="link-7">
                                  <p id="effect">{{ item.title }}</p>
                                </div>
                              </span>
                            </v-list-item-title>
                          </v-list-item-content>
                        </template>
                        <span>{{ item.info }}</span>
                      </v-tooltip>
                    </v-list-item>
                  </v-list>
                </v-card>
              </v-flex>
            </v-layout>
          </v-col>
          <v-col cols="9">
            <v-list>
              <v-list-item v-show="curSubDirectory[0].flag">
                <v-list-item-content>
                  <v-list-item-title
                    v-for="(subItem,index) in menuItems[0].sub"
                    :key="index"
                    class="display-1 text-shadow font-weight-bold my-5"
                  >{{subItem.title}}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item style="top:10vh" v-show="curSubDirectory[1].flag">
                <v-list-item-content>
                  <v-list-item-title
                    v-for="(subItem,index) in menuItems[1].sub"
                    :key="index"
                    class="display-1 text-shadow font-weight-bold my-5"
                  >{{subItem.title}}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <!-- <v-list-item style="top:20vh">
                <v-list-item-content>
                  <v-list-item-title
                    v-for="(subItem,index) in menuItems[1].sub"
                    :key="index"
                  >{{subItem.title}}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>-->
            </v-list>
          </v-col>
        </v-row>
      </v-navigation-drawer>
    </v-overlay>
    <v-navigation-drawer v-model="sidebar" temporary style="position:fixed;">
      <v-list nav dense>
        <v-list-item-group>
          <v-list-item v-for="item in menuItems" :key="item.title" :to="item.path">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>
<script>
import { mdiMenu } from "@mdi/js";
import { mdiAnimationOutline } from "@mdi/js";
import { mdiArrowLeftThick } from "@mdi/js";
export default {
  name: "main-header",
  mounted() {
    if (this.$store.state.userType !== 2) {
      alert("권한이 없습니다. 로그인해주세요");
      this.$router.push("/StoreLogin");
    } else {
      this.connect();
    }
  },
  data() {
    return {
      menuIconPath: mdiMenu,
      leftArrowPath: mdiArrowLeftThick,
      appTitle: "'반반한 동네' 사장님 공간",
      sidebar: false,
      overlay: false,
      userInfo: null,
      menuItems: [
        {
          title: "내 정보",
          path: "/read",
          icon: "folder_open",
          info: "내 정보 가고싶으면 클릭!!",
          sub: [
            { idx: 1, title: "회사 개요" },
            { idx: 2, title: "우아한 비전" },
            { idx: 3, title: "우아한 문화" }
          ]
        },
        {
          title: "가게 메뉴",
          path: "/StoreMenuPage",
          icon: "folder_open",
          info: "Click!",
          sub: []
        },
        {
          title: "고객 센터",
          path: "/",
          icon: "folder_open",
          info: "고객 센터 가고싶으면 클릭!!",
          sub: [
            { idx: 1, title: "서비스 개요" },
            { idx: 2, title: "우아한 서비스 비전" },
            { idx: 3, title: "우아한 서비스 문화" }
          ]
        }
      ],
      curSubDirectory: [{ flag: false }, { flag: false }]
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
    getSubDirectory(item, index) {
      for (let idx = 0; idx < this.curSubDirectory.length; idx++) {
        this.curSubDirectory[idx].flag = false;
      }
      this.curSubDirectory[index].flag = true;
    },
    connect() {
      this.socket = new SockJS("http://192.168.100.92:8082/order");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          console.log("연결요");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe(
            `/topic/push/${this.$store.state.userName}`,
            tick => {
              console.log(JSON.parse(tick.body).message);
              this.received_messages.push(JSON.parse(tick.body));
            }
          );
        },
        error => {
          console.log("에러요");
          console.log(error);
          this.connected = false;
        }
      );
    }
  }
};
</script>
<style scoped>
#effect {
  text-decoration: none;
  position: relative;
  font-size: 36px;
  text-transform: uppercase;
  display: inline-flex;
  padding-left: 10px;
  padding-bottom: 5px;
  padding-right: 10px;
  margin-top: 0px;
  margin-bottom: 0px;
}
#effect::before {
  width: 0;
  transition: width 0.1s ease-out;
}
#effect:hover:before {
  content: "";
  width: 100%;
  height: 80%;
  background-image: linear-gradient(
    to top,
    rgb(18, 192, 149) 25%,
    rgba(0, 0, 0, 0) 40%
  );
  position: absolute;
  left: 0;
  bottom: 2px;
  z-index: -1;
  will-change: width;
  transform: rotate(-2deg);
  transform-origin: left bottom;
  transition-duration: 0.15s;
}
/* #effect {
  text-transform: uppercase;
  font-size: 36px;
  color: white;
  text-decoration: none;
  position: relative;
}
[class^="link-"] {
  display: inline-block;
}
.link-7 #effect:before {
  content: "";
  border-bottom: solid 1px white;
  position: absolute;
  bottom: 0;
  left: 30%;
  width: 0;
  -webkit-transform: scale(0);
  -moz-transform: scale(0);
  -ms-transform: scale(0);
  -o-transform: scale(0);
  transform: scale(0);
}
.link-7 #effect:hover:before {
  border-bottom: solid thin white;
  width: 40%;
  -webkit-animation: heartbeat-x 1.7s infinite ease-in;
  animation: heartbeat-x 1.7s infinite ease-in;
}
.link-7 #effect:hover {
  -webkit-animation: heartbeat 1.7s infinite ease-in;
  animation: heartbeat 1.7s infinite ease-in;
}
@-webkit-keyframes heartbeat {
  0% {
    -webkit-transform: scale(1);
  }
  10% {
    -webkit-transform: scale(1.1);
  }
  20% {
    -webkit-transform: scale(1);
  }
  30% {
    -webkit-transform: scale(1.1);
  }
  40% {
    -webkit-transform: scale(1);
  }
}
@-webkit-keyframes heartbeat-x {
  0% {
    -webkit-transform: scaleX(0);
  }
  10% {
    -webkit-transform: scaleX(1);
  }
  20% {
    -webkit-transform: scaleX(0);
  }
  30% {
    -webkit-transform: scaleX(1);
  }
  40% {
    -webkit-transform: scaleX(0);
  }
}
@keyframes heartbeat {
  0% {
    transform: scale(1);
  }
  10% {
    transform: scale(1.1);
  }
  20% {
    transform: scale(1);
  }
  30% {
    transform: scale(1.1);
  }
  40% {
    transform: scale(1);
  }
}
@keyframes heartbeat-x {
  0% {
    transform: scaleX(0);
  }
  10% {
    transform: scaleX(1);
  }
  20% {
    transform: scaleX(0);
  }
  30% {
    transform: scaleX(1);
  }
  40% {
    transform: scaleX(0);
  }
} */
</style>