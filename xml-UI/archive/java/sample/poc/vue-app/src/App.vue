<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'App',
  // components: {
  //   HelloWorld,
  //   SeeBtn
  // }
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
#nprogress .bar {
  background: #29d;

  position: fixed;
  z-index: 1031;
  top: 0px !important;
  left: 0;

  width: 100%;
  height: 20px !important;
}
/* Remove these to get rid of the spinner */
#nprogress .spinner {
  display: block;
  position: fixed;
  z-index: 1031;
  top: 30px !important;
  right: 49% !important;
}

#nprogress .spinner-icon {
  width: 45px !important;
  height: 45px !important;
  box-sizing: border-box;

  border: solid 2px transparent;
  border-top-color: #29d;
  border-left-color: #29d;
  border-radius: 50%;
  animation: nprogress-spinner 400ms linear infinite;
}

</style>
