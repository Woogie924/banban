<template>
  <div>
    <div id="in_body">
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />

      <v-layout xs12 lg6 md6>
        <div class="loop-wrapper">
          <div class="mountain"></div>
          <div class="hill"></div>
          <div class="tree"></div>
          <div class="tree"></div>
          <div class="tree"></div>
          <div class="rock"></div>
          <div class="truck"></div>
          <div class="wheels"></div>
        </div>
      </v-layout>
      <br />
      <br />
      <br />
      <!-- <Visit></Visit> -->
    </div>

    <div id="inner">
      <h1 id="main_h1" class="animated infinite tada delay-1s">반반한 동네</h1>
    </div>

    <!-- <div id="down_Body">
      <v-btn large dark color="primary">일반</v-btn>
      <v-btn large dark color="red">사업자</v-btn>
    </div>-->
    <div class="text-center">
      <div
        v-ripple="{ center: true }"
        class="success--text text-center elevation-2 pa-12"
        style="cursor:pointer;"
        @click="move"
      >
        <h1 id="next_h1" class="animated rubberBand delay-3s">로그인 하기(Click me!)</h1>
      </div>
    </div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
  </div>
</template>

<script>
import { setInterval } from "../../Kakao";
export default {
  name: "index",
  data() {
    return {
      isOpen: false,
      value: 0
    };
  },
  methods: {
    move() {
      this.$router.push({
        path: "/visit"
      });
    }
  },
  mounted() {
    this.interval = setInterval(
      function() {
        this.value = this.value + 10;
      }.bind(this),
      1000
    );
  }
};
</script>

<style scoped>
/* animation */
#inner {
  width: 100%;
  position: absolute;
  top: 10%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
}
#down_Body {
  width: 100%;
  position: absolute;
  left: 45%;
}

#main_h1 {
  font-size: 4.3em;
  letter-spacing: -4px;
  font-weight: 700;
  color: #ffffff;
  text-align: center;
}
#next_h1 {
  font-size: 4.3em;
  letter-spacing: -4px;
  font-weight: 700;
  color: rgb(6, 180, 143);
  text-align: center;
}

/* intro 애니메이션 */
#in_body {
  background: #009688;
  overflow: hidden;
  font-family: "Open Sans", sans-serif;
}

.loop-wrapper {
  margin: 0 auto;
  position: relative;
  display: block;
  width: 600px;
  height: 250px;
  overflow: hidden;
  border-bottom: 3px solid #fff;
  color: #fff;
}

.mountain {
  position: absolute;
  right: -900px;
  bottom: -20px;
  width: 2px;
  height: 2px;
  box-shadow: 0 0 0 50px #4db6ac, 60px 50px 0 70px #4db6ac,
    90px 90px 0 50px #4db6ac, 250px 250px 0 50px #4db6ac,
    290px 320px 0 50px #4db6ac, 320px 400px 0 50px #4db6ac;
  transform: rotate(130deg);
  animation: mtn 20s linear infinite;
}

.hill {
  position: absolute;
  right: -900px;
  bottom: -50px;
  width: 400px;
  border-radius: 50%;
  height: 20px;
  box-shadow: 0 0 0 50px #4db6ac, -20px 0 0 20px #4db6ac, -90px 0 0 50px #4db6ac,
    250px 0 0 50px #4db6ac, 290px 0 0 50px #4db6ac, 620px 0 0 50px #4db6ac;
  animation: hill 4s 2s linear infinite;
}

.tree,
.tree:nth-child(2),
.tree:nth-child(3) {
  position: absolute;
  height: 100px;
  width: 35px;
  bottom: 0;
  background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/130015/tree.svg)
    no-repeat;
}

.rock {
  margin-top: -17%;
  height: 2%;
  width: 2%;
  bottom: -2px;
  border-radius: 20px;
  position: absolute;
  background: #ddd;
}

.truck,
.wheels {
  transition: all ease;
  width: 85px;
  margin-right: -60px;
  bottom: 0px;
  right: 50%;
  position: absolute;
  background: #eee;
}

.truck {
  background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/130015/truck.svg)
    no-repeat;
  background-size: contain;
  height: 60px;
}

.truck:before {
  content: " ";
  position: absolute;
  width: 25px;
  box-shadow: -30px 28px 0 1.5px #fff, -35px 18px 0 1.5px #fff;
}

.wheels {
  background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/130015/wheels.svg)
    no-repeat;
  height: 15px;
  margin-bottom: 0;
}

.tree {
  animation: tree 3s 0s linear infinite;
}

.tree:nth-child(2) {
  animation: tree2 2s 0.15s linear infinite;
}

.tree:nth-child(3) {
  animation: tree3 8s 0.05s linear infinite;
}

.rock {
  animation: rock 4s -0.53s linear infinite;
}

.truck {
  animation: truck 4s 0.08s ease infinite;
}

.wheels {
  animation: truck 4s 0.001s ease infinite;
}

.truck:before {
  animation: wind 1.5s 0s ease infinite;
}

@keyframes tree {
  0% {
    transform: translate(1350px);
  }

  50% {
  }

  100% {
    transform: translate(-50px);
  }
}

@keyframes tree2 {
  0% {
    transform: translate(650px);
  }

  50% {
  }

  100% {
    transform: translate(-50px);
  }
}

@keyframes tree3 {
  0% {
    transform: translate(2750px);
  }

  50% {
  }

  100% {
    transform: translate(-50px);
  }
}

@keyframes rock {
  0% {
    right: -200px;
  }

  100% {
    right: 2000px;
  }
}

@keyframes truck {
  0% {
  }

  6% {
    transform: translateY(0px);
  }

  7% {
    transform: translateY(-6px);
  }

  9% {
    transform: translateY(0px);
  }

  10% {
    transform: translateY(-1px);
  }

  11% {
    transform: translateY(0px);
  }

  100% {
  }
}

@keyframes wind {
  0% {
  }

  50% {
    transform: translateY(3px);
  }

  100% {
  }
}

@keyframes mtn {
  100% {
    transform: translateX(-2000px) rotate(130deg);
  }
}

@keyframes hill {
  100% {
    transform: translateX(-2000px);
  }
}
</style>