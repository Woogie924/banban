<template>
  <v-flex>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <template v-slot:activator="{on}">
        <v-btn v-on="on">
          <span>{{flag}}</span>
          <v-icon>{{icon}}</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-toolbar dark color="grey">
          <v-btn icon dark @click="cancel">
            <v-icon>mdi-close</v-icon>
          </v-btn>

          <v-toolbar-title>{{flag}}</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn dark text>Save</v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-card-text>
          <br />
          <v-btn color="blue-grey" dark text textclass="ma-2" @click="dialog2= !dialog2">메뉴 등록하기</v-btn>
        </v-card-text>
        <div style="flex: 1 1 auto;"></div>
      </v-card>
    </v-dialog>
    <v-dialog v-model="dialog2" max-width="800">
      <v-card>
        <v-card-title>메뉴 등록</v-card-title>
        <v-card-text>
          <v-container>
            <v-flex>
              <v-text-field label="음식명" :rules="rules" hide-details="auto"></v-text-field>
              <v-text-field label="가격"></v-text-field>
            </v-flex>
            <v-flex>
              <v-textarea
                name="input-7-1"
                label="Default style"
                value="'반반한 동네'
                맛있게 먹는 방법! 
                주문 꿀팁!
                설명해주세요!!"
                hint="쓰면 쓸수록 손님이 몰려와요,,"
              ></v-textarea>
            </v-flex>
            <br />
            <v-flex>
              <v-spacer></v-spacer>
              <v-autocomplete
                v-model="values"
                :items="tags"
                outlined
                dense
                chips
                small-chips
                label="Outlined"
                multiple
              ></v-autocomplete>
            </v-flex>
            <v-flex>
              <div id="aa">
                <file-pond
                  name="test"
                  ref="pond"
                  label-idle="Drop files here..."
                  allow-multiple="true"
                  accepted-file-types="image/jpeg, image/png"
                  :server="server"
                  v-bind:files="myFiles"
                  v-on:init="handleFilePondInit"
                />
              </div>
            </v-flex>
          </v-container>
          <!-- <v-select
                    :items="select"
                    label="A Select List"
                    item-value="text"
          ></v-select>-->
        </v-card-text>
        <v-card-actions>
          <v-btn color="primary" text @click="dialog2 = false">Close</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-flex>
</template>

<script>
// Import Vue FilePond
import vueFilePond from "vue-filepond";

// Import FilePond styles
import "filepond/dist/filepond.min.css";

// Import FilePond plugins
// Please note that you need to install these plugins separately

// Import image preview plugin styles
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css";

// Import image preview and file type validation plugins
import FilePondPluginFileValidateType from "filepond-plugin-file-validate-type";
import FilePondPluginImagePreview from "filepond-plugin-image-preview";
// Create component
const FilePond = vueFilePond(
  FilePondPluginFileValidateType,
  FilePondPluginImagePreview
);

export default {
  name: "MenuManagement",
  components: {
    FilePond
  },
  methods: {
    cancel() {
      this.dialog = !this.dialog;
    },
    handleFilePondInit() {
      console.log("FilePond has initialized");

      // FilePond instance methods are available on `this.$refs.pond`
    }
  },
  data() {
    return {
      dialog: false,
      dialog2: false,
      myFiles: [],
      server: {
        // url: "https://api.imgur.com/3/image",
        url: "http://192.168.100.92:8080/Img/insertImg",
        method: "POST",
        header: {
          "x-customheader": "Hello world",
          "Test-Header": "bb"
        },
        onload() {
          response => response.key;
          window.console.log(response);
        },
        onerror: response => response.data,
        ondata: formData => {
          formData.append("Hello", "World");
          formData.append("aa", "bb");
          return formData;
        }
      },
      tags: ["치킨", "피자", "야식", "혼밥"],
      values: ["치킨", "야식"],
      value: null,
      rules: [
        value => !!value || "반드시 입력해주세요.",
        value => (value && value.length >= 3) || "Min 3 characters"
      ]
    };
  },
  props: ["flag", "icon", "start"],
  mounted() {}
};
</script>

<style>
</style>