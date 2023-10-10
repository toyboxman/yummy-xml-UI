<template>
  <div class="about">
    <h1>The Error:<br> {{ this.userId }}</h1>
  </div>
  <van-button round block type="primary" @click="this.goBack" native-type="submit">
    Back
  </van-button>
</template>

<script>
const key = 'errResult'
export default {
  name: 'resultData',
  data() {
    return {
      userId: ''
    }
  },
  created() {
    let userId = localStorage.getItem(key)
    if (userId) {
      this.userId = userId
      localStorage.setItem(key, userId)
    }
  },
  beforeDestory() {
    localStorage.removeItem(key)
  },
  methods: {
    goBack() {
      this.$router.push('/')
    },
  }
}
</script>