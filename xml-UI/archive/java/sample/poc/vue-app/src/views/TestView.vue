<template>
  <div class="container" style="min-height: 100%; padding-bottom: 10px;">
    <p style="
    padding-left: 0px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 1 - check test result in canary VM.
    </p>
    <p style="
    padding-left: 30px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 2 - retrieve test report from canary VM.
    </p>
    <p style="
    padding-left: 80px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 3 - clean all canary test config in vCenter&NSX.
    </p>
  </div>
  <div class="container" style="min-height: 100%; padding-bottom: 10px; height: 149px;">
    Display Test Result In Canary VM
    <div style="display:flex; margin: 66px; position: relative;">
      <van-button round block type="primary" native-type="submit" @click="checkResult">
        Check Test Result
      </van-button>
      <van-button round block type="primary" native-type="submit" @click="goBack">
        Back
      </van-button>
    </div>
  </div>
  <van-cell-group inset>
    <van-cell title="Test Item" is-link arrow-direction="down"
      style="white-space: pre-line; font-size: medium;font-weight: bold;text-align-last: left;">
      {{ this.$route.query.type.toUpperCase().concat(" Result ...") }}
    </van-cell>
    <van-cell title="" style="white-space: pre-line; font-size: medium;text-align-last: left;">
      {{ this.testRet.replace(/(\r\n|\r|\n|\\n)/g, "\n\r") }}
    </van-cell>
  </van-cell-group>
</template>

<script>
import axios from 'axios';
import NProgress from "nprogress"
import 'nprogress/nprogress.css'
import { onMounted, onBeforeUpdate } from "vue";
export default {
  data() {
    return {
      testRet: 'No Result Here...'
    }
  },
  created() {
    console.log("afterCreate..." + JSON.stringify(this.$data))
  },

  beforeCreate() {
    console.log("beforeCreate..." + this.$el)
    console.log("beforeCreate..." + JSON.stringify(this.$data))
    console.log("beforeCreate type is ..." + this.$route.query.type)
    let type = this.$route.query.type
    console.log("beforeCreate type is ..." + type)
    let res = (type == "perf")
    console.log("comparation is..." + res)
  },

  methods: {
    goBack() {
      this.$router.push({
        name: 'home'
        // params: {
        //   userId: '1111'
        // }
      })
    },
    checkResult() {
      const type = this.$route.query.type
      // axios请求拦截
      // 在request拦截器中，展示进度条
      axios.interceptors.request.use(config => {
        NProgress.start();
        // 在最后必须return config;
        return config;
      });
      // 在response拦截器中，隐藏进度条
      axios.interceptors.response.use(config => {
        NProgress.done();
        return config;
      });

      let ret_url = 'http://localhost:8081/api/test/l2/result'
      if (type == "perf") {
        console.log("type is..." + type)
        ret_url = 'http://localhost:8081/api/test/perf/result'
      }

      axios({
        method: 'get',
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
          'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
          'Content-Type': 'application/json',
        },
        // url: 'http://localhost:8081/api/test/l2/result',
        url: ret_url,
      }).then(response => {
        if (response.status === 200) {
          this.testRet = JSON.stringify(response.data)
          console.log("show ping test result:\n" + this.testRet)
        } else {
          // 处理登录失败的响应
          alert("Failed to list VM:" + response.data)
        }
      })
        .catch(err => {
          console.log(err)
          let result = JSON.stringify(err.response.data)
          this.$router.push({
            name: 'about'
            // params: {
            //   userId: '1111'
            // }
          })
          const key = 'errResult'
          localStorage.setItem(key, result)
        })
      console.log("afterCreate..." + JSON.stringify(this.$data))
    },
  },

};
</script>

<style scoped>
.container {}

.van-button--block {
  display: block;
  width: 20%;
  margin-right: 30px;
}

.van-button {
  position: relative;
  top: 0px;
  left: 30%;
}
</style>
