<template>
  <the-header></the-header>
  <main-menu></main-menu>
</template>

<script>
import TheHeader from './components/TheHeader.vue'
import MainMenu from './components/MainMenu.vue';

export default {
  name: 'App',
  components: {
    TheHeader, 
    MainMenu
  }
}
</script>

<style>
#app {
  font-family: 'Comfortaa', cursive, Verdana, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #0f936c;
  margin-top: 25px;
}
</style>