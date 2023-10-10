<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <p>
      This App will boot up canary VM to run some test cases for readiness.
    </p>
    <p style="
    padding-left: 0px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 1 - login a vCenter.
    </p>
    <p style="
    padding-left: 145px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 2 - deploy canary VM template in each host.
    </p>
    <p style="
    padding-left: 20px;
    color: blue;
    font-size: 13px;
    -webkit-text-stroke-width: thin;">
      Step 3 - boot up canary VM.
    </p>
    <p></p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HelloWorld',
  props: {
    msg: String,
  },
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
