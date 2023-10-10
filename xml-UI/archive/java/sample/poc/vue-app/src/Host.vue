<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Canary App"/>
  <SeeBtn msg="Control Host"/>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import SeeBtn from './components/SeeBtn.vue';

export default defineComponent({
  name: 'HostApp',
  components: {
    SeeBtn
  }
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
