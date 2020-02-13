<template>
  <div>
    <button class="mdi mdi-bell-ring" @click="push"></button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  methods: {
    push() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `key=AAAA8fvy-ls:APA91bGWO0UCXWPWot0DNUz0qBzNZdm1p8OVwscXSPm5EqZOse_tlMSuPAQw8D3ZEBkAq_a7Vd1BuzBHteUydouwhsWAKeycbxYjmnL9i_cXc9rEubsJqhksV3mX3fUqxWhG0SRDjsao`;
      axios
        .post("https://fcm.googleapis.com/fcm/send", {
          to:
            "fI8mLwwDd3__7j3rHgpwP9:APA91bG4W9Mem1W_VE3aXMt9AB9j6zhAaxPB9HNRZdlv0ObusJ_ruFLIkLUPgvCACtV6vJf_eg9qZ4JfYjDN-OoaShQQUMKcw6WSzX_aHOY0eQYniz6fM7qTrnR7sxZ6YnFH-UKol73V",
          data: {
            status: "주문 받아라!"
          }
        })
        .then(console.log(this.to));
    }
  }
};
</script>

<style>
</style>