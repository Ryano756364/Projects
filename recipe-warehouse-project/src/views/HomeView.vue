<template>
  <section id="main-page">

    <div class="home">
      <h1>Recipe Warehouse</h1>
    </div>
  
    <div class="menuButton">
      <router-link to="/newrecipe" class="routerLink">Add New Recipe</router-link>
    </div>
    <div class="menuButton">
      <router-link to="/allrecipes" class="routerLink">View All Recipes</router-link>
    </div>
    <div class="menuButton">
      <router-link to="/" class="comingSoon">
        Search Recipes
        (coming soon)
      </router-link>
    </div>
    <div class="menuButton">
      <router-link to="/" class="comingSoon">
        View Recipes By Person
        (coming soon)
      </router-link>
    </div>
  
  </section>
</template>

<script>
  export default {
    name: 'HomeView',
  }
</script>

<style>
 
  .home {
    box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
    margin: 0.5rem 0.5rem 2.5rem 0.5rem;
    border-radius: 8px;
    padding: 0.5rem;
    background-color: #0f936c;
    color: white;
    text-align: center;
  }

  h1 {
    font-size: 3rem;
    margin-bottom: 32px;
  }

  .routerLink, .comingSoon{
    text-decoration: none;
    color: inherit;
    box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
    margin: 1.5rem 15rem;
    border-radius: 8px;
    padding: 0.5rem;
    color: rgb(0, 0, 0);
    text-align: center;
  }

  .routerLink {
    background-color: #0cf9b2;
  }
  .routerLink:hover {
    background-color: #65b29b;
  }

  .comingSoon {
    background-color: rgb(245, 233, 210);
  }
  .comingSoon:hover {
    background-color: rgb(247, 222, 176);
  }

  
  .menuButton {
    margin-top: 2rem;
  }
  
</style>