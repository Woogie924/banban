<template>
  <div>
    <!-- 네비게이션 바(유저) -->
    <!-- #009688 -->
    <v-app-bar fixed dense color="teal lighten-1">
      <v-app-bar-nav-icon dark @click.stop="overlay = !overlay"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <v-label>
          <router-link to="/UserMainPage" tag="span" style="cursor: pointer">
            <v-img src="../assets/배너.png" width="200" text aspect-ratio="5.0"></v-img>
          </router-link>
        </v-label>
      </v-toolbar-title>
      <v-spacer>
        <v-icon dark>{{quoteopen}}</v-icon>

        <span class="font-weight-bold white--text lighten-3">{{userInfo}}님 공간</span>
        <v-icon dark>{{quoteclose}}</v-icon>
      </v-spacer>
      <v-spacer />
      <!-- 검색바 -->
      <v-toolbar flat dense color="teal lighten-1" dark>
        <v-text-field hide-details prepend-icon="search" single-line></v-text-field>
      </v-toolbar>
      <!-- 메뉴 -->
      <v-toolbar-items class="hidden-xs-only">
        <v-btn dark text @click="getLocation">
          <v-icon left dark>mdi-wrench</v-icon>위치받기
        </v-btn>
        <v-btn dark text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
        <v-btn dark v-if="userState === true" text @click="logout">
          <v-icon left dark>folder_open</v-icon>로그아웃
        </v-btn>
        <v-btn dark v-else text @click="login">
          <v-icon left dark>folder_open</v-icon>로그인
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <!-- 메뉴 아이콘 클릭 -->
    <v-overlay :value="overlay">
      <v-navigation-drawer v-model="overlay" absolute color="transparent" style="position:fixed;">
        <v-layout>
          <v-flex>
            <v-toolbar width="100%" absolute dense color="transparent" style="position:fixed;">
              <v-icon @click.stop="overlay = !overlay" style="cursor:pointer;">mdi-reply</v-icon>

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
export default {
  name: "UserNavBar",
  data() {
    return {
      appTitle: "ㅇㅇㅇ님 공간",
      quoteclose: mdiFormatQuoteClose,
      quoteopen: mdiFormatQuoteOpen,
      sidebar: false,
      userName: "userName",
      userInfo: this.$store.state.userName,
      overlay: false,
      userState: null,
      menuItems: [
        {
          title: "게시판",
          path: "/Read",
          icon: "account_circle",
          info: "이것은 게시판입니다."
        },
        {
          title: "내 정보",
          path: "/",
          icon: "folder_open",
          info: "이것은 내 정보입니다."
        },
        {
          title: "고객 센터",
          path: "/StoreInfoPage",
          icon: "folder_open",
          info: "이것은 고객센터입니다."
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
    // this.getUsername();
  },
  methods: {
    getLocation() {
      this.$store.commit("getLocation");
      // console.log(this.$store.state.lat);
      // console.log(this.$store.state.lon);
      this.$store
        .dispatch("resetaddr", {
          lat: this.$store.state.lat,
          lon: this.$store.state.lon
        })
        .then(() => {
          console.log("성공");
          window.location.reload();
        });
    },
    logout() {
      this.$store.dispatch("logout");
    },
    login() {
      this.$router.push("Mlogin");
    }
    // getUsername() {
    //   axios.defaults.headers.common[
    //     "Authorization"
    //   ] = `Bearer ${store.state.token}`;
    //   axios.get("http://192.168.100.92:8080/api/user");
    // }
  }
};
</script>
 
<style scoped>
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
