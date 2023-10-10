<template>
  <div class="container" style="min-height: 100%; padding-bottom: 10px;">
    <p style="
    padding-left: 0px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 1 - send test instruments to canary VM.
    </p>
    <p style="
    padding-left: 135px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 2 - coordinate canary VM to prepare test run config in vCenter.
    </p>
    <p style="
    padding-left: 115px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 3 - coordinate canary VM to prepare test run config in NSX.
    </p>
  </div>
  <div class="container" style="min-height: 100%; padding-bottom: 10px;">
    List All Canary VM
    <a-table ref="vmTable" :dataSource="dataSource" :columns="columns" />
  </div>

  <div style="display:flex; margin: 66px; position: relative;">
    <van-button round block type="primary" native-type="submit" @click="test('ping')">
      Run Ping Test
    </van-button>
    <van-button round block type="primary" native-type="submit" @click="test('mtu')">
      Run MTU Test
    </van-button>
    <van-button round block type="primary" native-type="submit" @click="testPerf">
      Run Perf Test
    </van-button>
  </div>
</template>
<script>
import axios from 'axios';
import NProgress from "nprogress"
import { onMounted, onBeforeUpdate } from "vue";
export default {
  name: 'vmData',
  methods: {
    testPerf() {
      console.log("run perf test")
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
        method: 'get',
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
          'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
          'Content-Type': 'application/json',
        },
        url: 'http://localhost:8081/api/test/perf',
      }).then(response => {
        if (response.status === 200) {
          let pingRet = JSON.stringify(response.data)
          console.log("get perf test status:" + pingRet)
          this.$router.push('/result?type=perf')
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

    },

    test(type) {
      console.log("run ping test " + type)
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
        method: 'get',
        headers: {
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
          'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS',
          'Content-Type': 'application/json',
        },
        url: 'http://localhost:8081/api/test/l2/' + type,
      }).then(response => {
        if (response.status === 200) {
          let pingRet = JSON.stringify(response.data)
          console.log("get ping test status:" + pingRet)
          this.$router.push('/result?type=ping')
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

    },
  },

  setup() {
    // alert("setup:")

    let vmResult = [
      {
        // key: '1',
        name: 'VM-1',
        hostName: 'host-1',
        address: '10.111.4.1',
      },
      {
        // key: '2',
        name: 'VM-2',
        hostName: 'host-2',
        address: '10.112.5.3',
      },
    ]
    const key = 'vmData'
    vmResult = JSON.parse(localStorage.getItem(key))
    // alert("localStorage.getItem:" + JSON.stringify(vmResult))
    onMounted(() => {
      console.log("onMounted:" + JSON.stringify(vmResult))
      // alert("onMounted:" + JSON.stringify(vmResult))
    })

    // const obj = JSON.parse(vmResult);
    // alert(obj.code)

    return {
      dataSource: vmResult,
      // VM{name='photon-hw15-5.0-dde71ec57.x86_64', vm='vm-25', power_state='POWERED_ON', memory_size_MiB=2048, cpu_count=1, ip='10.185.103.89'}
      columns: [
        {
          title: 'VM',
          dataIndex: 'name',
          key: 'name',
        },
        {
          title: 'Address',
          dataIndex: 'ip',
          key: 'address',
        },
        {
          title: 'Name',
          dataIndex: 'vm',
          key: 'host',
        },
        {
          title: 'Power_state',
          dataIndex: 'power_state',
          key: 'address',
        },
        {
          title: 'Memory_Size_MiB',
          dataIndex: 'memory_size_MiB',
          key: 'address',
        },
        {
          title: 'CPU_Count',
          dataIndex: 'cpu_count',
          key: 'address',
        },

      ],
    };


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
  left: 20%;
}
</style>
