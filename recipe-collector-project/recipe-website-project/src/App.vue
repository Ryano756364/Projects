<template>
  <the-header></the-header>
    <ul>
      <main-menu 
        menu-item ="Add New Recipe">
      </main-menu>
      <main-menu 
        menu-item ="View All Recipes">
      </main-menu>
      <main-menu 
        menu-item ="Search Recipes">
      </main-menu>
      <main-menu 
        menu-item ="View Recipes By Person">
      </main-menu>
    </ul>
</template>

<script>
import TheHeader from './components/TheHeader.vue'
import MainMenu from './components/MainMenuItem.vue';

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