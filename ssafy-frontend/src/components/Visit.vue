<template>
  <v-container>
    <v-layout>
      <v-flex>
        <v-row>
          <v-hover>
            <template v-slot:default="{hover}">
              <v-card height="100vh" width="50%" @click="moveUserLogin">
                <v-img class="white--text align-end" height="100%" src="../assets/일반.jpg" contain>
                  <v-card-title>일반 회원</v-card-title>
                </v-img>
                <v-fade-transition>
                  <v-overlay v-if="hover" absolute color="#058">
                    <v-btn>일반 회원 로그인</v-btn>
                  </v-overlay>
                </v-fade-transition>
              </v-card>
            </template>
          </v-hover>

          <v-hover>
            <template v-slot:default="{hover}">
              <v-card height="100vh" width="50%" @click="moveStoreLogin">
                <v-img class="white--text align-end" height="100%" src="../assets/사업자.jpg" contain>
                  <v-card-title>사업자 회원</v-card-title>
                </v-img>
                <v-fade-transition>
                  <v-overlay v-if="hover" absolute color="#058">
                    <v-btn>사업자 회원 로그인</v-btn>
                  </v-overlay>
                </v-fade-transition>
              </v-card>
            </template>
          </v-hover>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "visit",
  methods: {
    moveUserLogin() {
      this.$router.push({
        path: "mlogin"
      });
    },
    moveStoreLogin() {
      this.$router.push({
        path: "StoreLogin"
      });
    }
  }
};
</script>

<style>
</style>