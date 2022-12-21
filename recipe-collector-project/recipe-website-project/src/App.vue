<template>
  <the-header></the-header>
    <ul>
      <main-menu 
        v-for="item in menu" 
        :key="item.id"
        :menu-item ="item.name">
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
  },
  data(){
    return {
      menu: [
        {
          id: 1,
          name: "Add New Recipe"
        }, 
        {
          id: 2,
          name: "View All Recipes"
        }, 
        {
          id: 3,
          name: "Search Recipes"
        }, 
        {
          id: 4,
          name: "View Recipes By Person"
        }, 
      ]
    }
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