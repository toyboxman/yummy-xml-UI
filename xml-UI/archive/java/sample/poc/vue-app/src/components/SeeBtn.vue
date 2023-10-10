<template>
  <div class="container" style="min-height: 100%; min-width: 50%; padding-bottom: 100px;">
    <van-form @submit="onSubmit">
      <van-cell-group inset>
        <van-field v-model="username" name="Username" label="Username" placeholder="Username" size="small"
          :rules="[{ required: true, message: 'Username is required' }]" />
        <van-field v-model="password" type="password" name="Password" label="Password" placeholder="Password"
          :rules="[{ required: true, message: 'Password is required' }]" />
      </van-cell-group>
      <div style="margin: 16px;">
        <van-button round block type="primary" native-type="submit" @click="login">
          Submit
        </van-button>
      </div>
    </van-form>
  </div>
</template>
  
<script>
import axios from 'axios';
import NProgress from "nprogress"
export default {
  props: [],
  components: {},
  data() {
    return {
      password: "",
      username: "",
      showValue: "开启预览模式后,点击我显示预设逻辑",
      showText: "这里的值声明于预设JS代码",
    }
  },
  watch: {},
  computed: {},
  beforeCreate() {
    console.log("beforeCreate")
  },
  created() {
    console.log("created")
  },
  beforeMount() {
    console.log("beforeMount")
  },
  mounted() {
    console.log("mounted")
  },
  beforeUpdate() {
    console.log("beforeUpdate")
  },
  updated() {
    console.log("updated")
  },
  unmounted() {
    console.log("unmounted")
  },
  methods: {
    request() {
      console.log("request")
    },
    onEventClick() {
      console.log("onEventClick")
    },
    onSubmit(values) {
      console.log("submit", values)
    },
    hello() {
      alert("password" + this.password)
    },

    login() {
      if (this.username == '') {
        return false
      }
      if (this.password == '') {
        return false
      }

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

      axios({
        method: 'post',
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
          'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
          'Content-Type': 'application/json',
        },
        url: 'http://localhost:8081/api/connect/vc',
        params: { admin: this.username, password: this.password },
      })
        .then(response => {
          if (response.status === 200) {
            // 跳转到主界面
            axios({
              method: 'get',
              headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type, Authorization',
                'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
                'Content-Type': 'application/json',
              },
              url: 'http://localhost:8081/api/test/vm',
            }).then(response => {
              if (response.status === 200) {
                let vmData = JSON.stringify(response.data)
                console.log("get vm list:" + vmData)
                const key = 'vmData'
                localStorage.setItem(key, vmData)
                this.$router.push('/home')
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
          } else {
            // 处理登录失败的响应
            alert("Failed to login:" + response.data)
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

    },

  },
  fillter: {},
}
</script>
  
<style scoped>
.container {
  color: #42b983;
  width: 100%;
}

.van-button--block {
  display: block;
  width: 20%;
}

.van-button {
  position: relative;
  top: 0px;
  left: 40%;
}

.van-cell {
  position: relative;
  left: 30%;
}

.van-field__control {
  width: 50%;
  padding-left: 30%;
}
</style>
  