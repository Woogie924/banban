<template>
  <div>
    <form class="frm">
      <v-content relative>
        <v-container>
          <v-row justify="center">
            <v-col cols="12" sm="6" md="4">
              <v-card-text class="vcard" text-xs-center>
                <v-form @submit.prevent="Mregister">
                  <p class="display-1 text-center font-weight-bold">회원가입</p>
                  <br />
                  <v-row>
                    <v-text-field
                      v-model="id"
                      label="아이디"
                      name="id"
                      type="text"
                      outlined
                      dense
                      @blur="$v.id.$touch()"
                    ></v-text-field>
                  </v-row>
                  <div v-if="$v.id.$error">
                    <p v-if="!$v.id.required" class="errorMessage">* 아이디를 작성해 주세요.</p>
                    <p v-if="!$v.id.maxLength" class="errorMessage">* 최대 길이를 초과했습니다. (10자)</p>
                  </div>
                  <v-row>
                    <v-text-field
                      v-model="pw"
                      label="비밀번호"
                      name="pw"
                      type="password"
                      outlined
                      dense
                      @blur="$v.pw.$touch()"
                    />
                  </v-row>
                  <div v-if="$v.pw.$error">
                    <p v-if="!$v.pw.required" class="errorMessage">* 비밀번호를 작성해 주세요.</p>
                    <p v-if="!$v.pw.minLength" class="errorMessage">* 4자 이상 작성해주세요.</p>
                  </div>
                  <v-row>
                    <v-text-field
                      v-model="name"
                      label="이름"
                      name="name"
                      type="text"
                      outlined
                      dense
                      @blur="$v.name.$touch()"
                    />
                  </v-row>
                  <div v-if="$v.name.$error">
                    <p v-if="!$v.name.required" class="errorMessage">* 이름을 작성해 주세요.</p>
                  </div>
                  <v-row>
                    <v-text-field
                      v-model="email"
                      label="이메일"
                      name="email"
                      type="email"
                      outlined
                      dense
                      @blur="$v.email.$touch()"
                    ></v-text-field>
                  </v-row>
                  <div v-if="$v.email.$error">
                    <p v-if="!$v.email.email" class="errorMessage">* 이메일 형식에 맞게 작성해 주세요.</p>
                    <p v-if="!$v.email.required" class="errorMessage">* 이메일을 작성해 주세요.</p>
                  </div>
                  <v-row>
                    <v-btn @click="sample4_execDaumPostcode()" width="100%" outlined dense>우편번호찾기</v-btn>
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="zipcode"
                      outlined
                      dense
                      :disabled="true"
                      @blur="$v.zipcode.$touch()"
                    />
                  </v-row>
                  <v-row>
                    <v-text-field v-model="address" outlined dense @blur="$v.address.$touch()" />
                  </v-row>
                  <v-row>
                    <v-text-field v-model="address2" outlined dense @blur="$v.address2.$touch()" />
                  </v-row>
                  <v-row>
                    <v-text-field
                      v-model="address3"
                      label="상세주소"
                      outlined
                      dense
                      @blur="$v.address3.$touch()"
                    />
                  </v-row>
                  <div v-if="$v.zipcode.$error">
                    <p v-if="!$v.zipcode.required" class="errorMessage">* 주소를 작성해 주세요.</p>
                  </div>
                  <v-row>
                    <v-text-field
                      v-model="tel"
                      label="핸드폰번호"
                      name="tel"
                      outlined
                      dense
                      @blur="$v.tel.$touch()"
                    />
                  </v-row>
                  <div v-if="$v.tel.$error">
                    <p v-if="!$v.tel.required" class="errorMessage">* 전화번호를 작성해 주세요.</p>
                  </div>
                  <v-row>
                    <v-btn
                      type="submit"
                      large
                      color="primary"
                      width="100%"
                      height="50"
                      fill="#0076ff"
                      font-size="16"
                      font-family="SourceHanSansK-Bold, Source Han Sans K"
                      font-weight="700"
                      :disabled="$v.$invalid"
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
import {
  required,
  email,
  minLength,
  maxLength
} from "vuelidate/lib/validators/";
export default {
  data() {
    return {
      id: "",
      pw: "",
      name: "",
      tel: "",
      email: null,
      address: "",
      chk: 0,
      point: 0,
      signupDate: 214,
      zipcode: "",
      address2: "",
      address3: "",
      lat: 123,
      lon: 123
    };
  },
  validations: {
    id: {
      required,
      maxLength: maxLength(10)
    },
    pw: {
      required,
      minLength: minLength(4)
    },
    name: { required },
    tel: { required },
    email: { required, email },
    zipcode: { required },
    address: { required },
    address2: { required },
    address3: { required }
  },
  methods: {
    Mregister() {
      if (!this.$v.$invalid) {
        this.$store
          .dispatch("Mregister", {
            id: this.id,
            pw: this.pw,
            name: this.name,
            tel: this.tel,
            email: this.email,
            address: this.address + this.address3,
            latitude: this.latitude,
            longitude: this.longitude,
            chk: this.chk,
            point: this.point,
            signupDate: this.signupDate,
            latitude: this.lat,
            longitude: this.lon
          })
          .then(() => {
            console.log(this.$store.state.user);
            this.$router.push({ path: "/" });
          });
      }
    },
    sample4_execDaumPostcode() {
      new daum.Postcode({
        oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
          // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
          let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
          // alert(fullRoadAddr);
          let extraRoadAddr = ""; // 도로명 조합형 주소 변수

          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraRoadAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraRoadAddr +=
              extraRoadAddr !== ""
                ? ", " + data.buildingName
                : data.buildingName;
          }
          // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraRoadAddr !== "") {
            extraRoadAddr = " (" + extraRoadAddr + ")";
          }
          // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
          if (fullRoadAddr !== "") {
            fullRoadAddr += extraRoadAddr;
          }

          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          // document.getElementById("sample4_postcode").value = data.zonecode; //5자리 새우편번호 사용
          // document.getElementById("sample4_roadAddress").value = fullRoadAddr;
          // document.getElementById("sample4_jibunAddress").value =
          //   data.jibunAddress;
          // alert(data.zonecode);
          this.zipcode = data.zonecode;
          this.address = fullRoadAddr;
          this.address2 = data.jibunAddress;
          var geocoder = new kakao.maps.services.Geocoder();
          // console.log(this.address);
          geocoder.addressSearch(
            this.address,
            function(result, status) {
              if (status == kakao.maps.services.Status.OK) {
                // console.log(result[0].y + " " + result[0].x);
                // console.log(result[0].x);
                this.lat = result[0].y;
                this.lon = result[0].x;
                console.log(this.lat + " " + this.lon);
              }
            }.bind(this)
          );
        }.bind(this)
      }).open();
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
.errorMessage {
  color: red;
  padding-bottom: 0;
}
</style>