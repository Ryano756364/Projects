<template>
  <the-header></the-header>
  <call-log></call-log>
</template>

<script>
import CallLog from './components/DailyCallLog.vue'
import TheHeader from './components/TheHeader.vue'

export default {
  name: 'App',
  components: {
    CallLog,
    TheHeader
  }
}
</script>

<style>

* {
  box-sizing: border-box;
}

html {
  font-family: 'Jost', sans-serif;
}

body {
  margin: 0;
}

</style>