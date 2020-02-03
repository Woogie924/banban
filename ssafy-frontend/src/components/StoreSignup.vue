<template>
  <form class="frm">
    <v-content relative>
      <v-container>
        <v-row justify="center">
          <v-col cols="12" sm="8" md="3">
            <v-card-text class="vcard" text-xs-center>
              <v-form>
                <v-row>
                  <v-card-title class="title">회원가입</v-card-title>
                </v-row>
                <br />
                <v-row>
                  <v-text-field id="id" label="아이디" type="text" outlined dense />
                </v-row>
                <v-row>
                  <v-text-field id="password1" label="비밀번호" outlined dense type="password" />
                </v-row>
                <v-row>
                  <v-text-field id="password2" label="비밀번호 재확인" outlined dense type="password" />
                </v-row>
                <!-- <v-col cols="12" sm="6" md="3"> -->
                <v-row>
                  <v-text-field id="phonenumber" label="휴대폰" outlined dense type="phonenumber" />
                </v-row>
                <!-- </v-col> -->
                <v-row>
                  <v-text-field id="name" label="상호명" outlined dense type="text" />
                </v-row>
                <v-row>
                  <v-text-field id="address" label="주소" outlined dense type="address" />
                </v-row>
                <v-row>
                  <v-btn color="blue" width="100%">가입완료</v-btn>
                </v-row>
              </v-form>
            </v-card-text>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </form>
</template>

<script>
export default {
  name: "MsignUp",
  props: {},
  methods: {}
};
</script>

<style>
.title {
  margin-left: 38%;
}
</style>