<template>
  <div>
    <form class="frm">
      <v-content relative>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" sm="6" md="4">
              <v-card-text class="vcard" text-xs-center>
                <v-form @submit.prevent="register">
                  <p class="display-1 text-center font-weight-bold">카카오연동 회원가입</p>
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
                    <v-text-field v-model="name" label="이름" name="name" type="text" outlined dense />
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
                    <v-text-field
                      v-model="tel"
                      label="핸드폰번호"
                      name="tel"
                      type="number"
                      outlined
                      dense
                    />
                  </v-row>
                  <v-row>
                    <v-btn
                      type="submit"
                      large
                      color="primary"
                      @click="login()"
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
      chk: 0,
      point: 0,
      signupDate: 214,
      test: ""
    };
  },
  methods: {
    register() {
      this.$store
        .dispatch("socialRegister", {
          id: this.id,
          pw: this.pw,
          name: this.name,
          tel: this.tel,
          email: this.email,
          address: this.address,
          chk: this.chk,
          point: this.point,
          signupDate: this.signupDate
        })
        .then(() => {
          this.$router.push({ path: "/UserMainPage" });
        });
    }
  },
  mounted() {
    this.test = this.$store.state.res;
    this.email = this.test.kaccount_email;
    this.name = this.test.properties["nickname"];
    this.id = this.test.kaccount_email;
    // this.id = this.test.id;
    console.log(this.$store.state.res);
  }
};
</script>

<style>
.title {
  margin-left: 38%;
}
</style>