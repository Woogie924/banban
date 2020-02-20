<template>
  <div>
    <!-- 네비게이션 바(유저) -->
    <!-- #009688 -->
    <v-app-bar fixed dense color="teal lighten-1">
      <v-app-bar-nav-icon dark @click.stop="overlay = !overlay"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <v-label>
          <router-link to="/UserMainPage" tag="span" style="cursor: pointer">
            <v-img src="../assets/배너.png" width="150" text aspect-ratio="2.0"></v-img>
          </router-link>
        </v-label>
      </v-toolbar-title>
      <v-icon dark>{{quoteopen}}</v-icon>

      <span
        @click="moveToInfo"
        class="font-weight-regular white--text lighten-3"
        style="cursor: pointer"
      >{{userInfo}}님 공간</span>
      <v-icon dark>{{quoteclose}}</v-icon>
      <v-spacer>
        <alarm />
      </v-spacer>
      <v-spacer />
      <!-- 메뉴 -->
      <v-toolbar-items class="hidden-xs-only">
        <v-btn dark text @click="getAddress">
          <v-icon left dark>{{regLocationIcon}}</v-icon>주소지
        </v-btn>
        <v-btn dark text @click="getLocation">
          <v-icon left dark>{{curLocationIcon}}</v-icon>현위치
        </v-btn>
        <v-btn dark text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
        <v-btn dark v-if="userState === true" text @click="logout">
          <v-icon left dark>{{logoutIcon}}</v-icon>로그아웃
        </v-btn>
        <v-btn dark v-else text @click="login">
          <v-icon left dark>{{loginIcon}}</v-icon>로그인
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <!-- 메뉴 아이콘 클릭 -->
    <v-overlay :value="overlay" opacity="0.8">
      <v-navigation-drawer v-model="overlay" absolute color="transparent" style="position:fixed;">
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
            <v-card fluid color="transparent">
              <br />
              <br />
              <v-list nav>
                <v-list-item-group>
                  <v-list-item v-for="item in menuItems" :key="item.title" :to="item.path">
                    <v-tooltip bottom>
                      <template v-slot:activator="{ on }">
                        <v-list-item-content v-on="on">
                          <v-list-item-title>
                            <span class="display-1 text-shadow font-weight-bold">
                              <div class="link-7">
                                <p id="effect">{{ item.title }}</p>
                              </div>
                            </span>
                          </v-list-item-title>
                        </v-list-item-content>
                      </template>
                      <span>{{item.info}}</span>
                    </v-tooltip>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-card>
          </v-flex>
        </v-layout>
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
              <v-list-item-title>
                <p class="font-weight-bold">{{ item.title }}</p>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>
<script>
import axios from "axios";
import store from "@/vuex/store.js";
import router from "@/router.js";
import { mdiFormatQuoteClose, mdiFormatQuoteOpen } from "@mdi/js";
import {
  mdiCalendarTextOutline,
  mdiAccountCircle,
  mdiHelp,
  mdiLockOpenVariant,
  mdiLock
} from "@mdi/js";
import { mdiLogin, mdiLogout } from "@mdi/js";
import { mdiArrowLeftThick, mdiCrosshairsGps } from "@mdi/js";
import { mdiBookOpen } from "@mdi/js";
import alarm from "@/components/alarm.vue";
export default {
  name: "UserNavBar",
  components: {
    alarm
  },
  data() {
    return {
      appTitle: "ㅇㅇㅇ님 공간",
      curLocationIcon: mdiCrosshairsGps,
      regLocationIcon: mdiBookOpen,
      loginIcon: mdiLogin,
      logoutIcon: mdiLogout,
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen,
      leftArrowPath: mdiArrowLeftThick,
      sidebar: false,
      userName: "userName",
      userInfo: this.$store.state.userName,
      overlay: false,
      userState: null,
      menuItems: [
        {
          title: "게시판",
          path: "/Read",
          icon: mdiCalendarTextOutline,
          info: "게시판입니다."
        },
        {
          title: "내 정보",
          path: "/profile",
          icon: mdiAccountCircle,
          info: "내 정보입니다."
        },
        {
          title: "FAQ",
          path: "/userFAQ",
          icon: mdiHelp,
          info: "FAQ입니다."
        }
      ],
      latitude: 0,
      longitude: 0
    };
  },
  mounted() {
    // this.getUsername();
    if (this.$store.state.token) {
      this.userState = true;
    } else {
      this.userState = false;
    }
    if (this.$store.state.userType !== 1) {
      alert("권한이 없습니다. 로그인해주세요");
      this.$router.push("/StoreLogin");
    }
    // this.getUsername();
  },
  methods: {
    getLocation() {
      navigator.geolocation.getCurrentPosition(function(pos) {
        store.dispatch("getLocation", {
          lat: pos.coords.latitude,
          lon: pos.coords.longitude
        });
        // alert(`위치가 갱신되었습니다.`);

        store
          .dispatch("resetaddr", {
            lat: store.state.lat,
            lon: store.state.lon
          })
          .then(() => {
            console.log("성공");
            window.location.reload();
          });
      }); //백으로 다시 보내서 계산...
    },
    getAddress() {
      var geocoder = new kakao.maps.services.Geocoder();
      // console.log(this.address);
      geocoder.addressSearch(
        store.state.userAddr,
        function(result, status) {
          if (status == kakao.maps.services.Status.OK) {
            // console.log(result[0].y + " " + result[0].x);
            // console.log(result[0].x);
            store.state.lat = result[0].y;
            store.state.lon = result[0].x;
            console.log("1");
            this.aaaaa();
          }
        }.bind(this)
      );
    },
    aaaaa() {
      store
        .dispatch("getLocation", {
          //주소지를 위도, 경도로 바꿔준다..
          lat: store.state.lat,
          lon: store.state.lon
        })
        .then(() => {
          console.log("2");
          store
            .dispatch("resetaddr", {
              lat: store.state.lat,
              lon: store.state.lon
            })
            .then(() => {
              window.location.reload();
            });
        });
      // alert(`주소지로 이동!`);

      store
        .dispatch("resetaddr", {
          lat: store.state.lat,
          lon: store.state.lon
        })
        .then(() => {
          console.log("3");
          // console.log(store.state.userAddr);
          window.location.reload();
          // window.location.reload();
        });
    },
    logout() {
      this.$store.dispatch("logout");
    },
    login() {
      this.$router.push("Mlogin");
    },
    moveToInfo() {
      this.$router.push("profile");
    }
  }
};
</script>
 
<style scoped>
@import url("https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Jua|Nanum+Gothic|Sunflower:300");
* {
  font-family: "Do Hyeon", sans-serif;
}
#effect {
  text-transform: uppercase;
  font-size: 36px;
  color: white;
  text-decoration: none;
  position: relative;
}
[class^="link-"] {
  display: inline-block;
}
.toolbarSize {
  width: 30px;
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
}
</style>
