<template>
  <div>
    <!-- 네비게이션 바(유저) -->
    <v-toolbar width="100%" absolute dense style="position:fixed;">
      <v-app-bar-nav-icon @click.stop="overlay = !overlay"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <v-label>
          <router-link to="/UserMainPage" tag="span" style="cursor: pointer">
            <v-img src="../assets/홈3.png" width="300" text aspect-ratio="7.0"></v-img>
          </router-link>
        </v-label>
      </v-toolbar-title>
      <v-spacer>
        <v-text class="font-weight-bold">{{userName}}님 공간</v-text>
      </v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
        <v-btn text @click="logout">
          <v-icon left dark>folder_open</v-icon>로그아웃
        </v-btn>
      </v-toolbar-items>
    </v-toolbar>

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
export default {
  name: "main-header",
  data() {
    return {
      appTitle: "ㅇㅇㅇ님 공간",
      sidebar: false,
      userName: "userName",
      overlay: false,
      menuItems: [
        {
          title: "게시판",
          path: "/read",
          icon: "account_circle",
          info: "이것은 게시판입니다."
        },
        {
          title: "내 정보",
          path: "/read",
          icon: "folder_open",
          info: "이것은 내 정보입니다."
        },
        {
          title: "고객 센터",
          path: "/",
          icon: "folder_open",
          info: "이것은 고객센터입니다."
        }
      ]
    };
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    }
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
