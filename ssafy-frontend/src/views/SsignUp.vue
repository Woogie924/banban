<template>
  <div>
    <form class="frm">
      <v-content relative>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" sm="6" md="4">
              <v-card-text class="vcard" text-xs-center>
                <v-form @submit.prevent="Sregister">
                  <p class="display-1 text-center font-weight-bold">회원가입</p>
                  <br />
                  <v-row>
                    <v-text-field v-model="id" label="아이디" name="id" type="text" outlined dense></v-text-field>
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="pw"
                      label="비밀번호"
                      name="pw"
                      type="password"
                      outlined
                      dense
                    />
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="name"
                      label="상호명"
                      name="name"
                      type="text"
                      outlined
                      dense
                    />
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="email"
                      label="이메일"
                      name="email"
                      type="email"
                      outlined
                      dense
                    ></v-text-field>
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="address"
                      label="주소"
                      name="address"
                      type="text"
                      outlined
                      dense
                    />
                  </v-row>
                  <v-row>
                    <v-text-field v-model="tel" label="핸드폰번호" name="tel" outlined dense />
                  </v-row>
                  <v-row>
                    <v-btn
                      type="submit"
                      large
                      color="primary"
                      @click="Sregister()"
                      width="100%"
                      height="50"
                      fill="#0076ff"
                      font-size="16"
                      font-family="SourceHanSansK-Bold, Source Han Sans K"
                      font-weight="700"
                    >가입완료</v-btn>
                  </v-row>
                </v-form>
              </v-card-text>
            </v-col>
          </v-row>
        </v-container>
      </v-content>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: "",
      pw: "",
      name: "",
      tel: "",
      email: "",
      address: "",
      chk: 2,
      point: 0,
      signupDate: null,
      likes: 0
    };
  },
  methods: {
    Sregister() {
      this.$store
        .dispatch("Sregister", {
          id: this.id,
          pw: this.pw,
          name: this.name,
          tel: this.tel,
          email: this.email,
          address: this.address,
          chk: this.chk,
          point: this.point,
          signup_date: this.signupDate,
          likes: this.likes
        })
        .then(() => {
          this.$router.push({ path: "/" });
        });
    }
  }
};
</script>

<style scoped>
.title {
  margin-left: 38%;
}
@import url("https://fonts.googleapis.com/css?family=Black+Han+Sans|Do+Hyeon|Jua|Nanum+Gothic|Sunflower:300");
* {
  font-family: "Do Hyeon", sans-serif;
}
</style>