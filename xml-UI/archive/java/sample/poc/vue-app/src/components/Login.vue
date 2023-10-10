<template>
  <!-- <img alt="Vue logo" src="../assets/logo.png"> -->
  <HelloWorld msg="Welcome to Canary App"/>
  <SeeBtn msg="Welcome to use BtnApp"/>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from './HelloWorld.vue';
import SeeBtn from './SeeBtn.vue';

export default defineComponent({
  name: 'App',
  components: {
    HelloWorld,
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
