<template>

  <div class="home">
    <h1>Cookbook Lab</h1>
  
    <section class="buttons">
      <div class="buttonContainer">
        <router-link to="/newrecipe" class="routerLink">Add New Recipe</router-link>
      </div>

      <div class="buttonContainer">
        <router-link to="/allrecipes" class="routerLink">View All Recipes</router-link>
      </div>

      <div class="buttonContainer">
        <router-link to="/" class="comingSoon">
          Search Recipes
          (coming soon)
        </router-link>
      </div>

      <div class="buttonContainer">
        <router-link to="/" class="comingSoon">
          View Recipes By Person 
          (coming soon)
        </router-link>
      </div>
    </section>
  </div>
</template>

<script>
  export default {
    name: 'HomeView',
  }
</script>

<style>
 
  .home {
    color: white;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  h1 {
    box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
    padding: 0.5rem;
    border-radius: 8px;
    margin: 1rem;
    font-size: 3rem;
    background-color: #0f936c;
  }

  .buttonContainer{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 1rem;
  }

  .routerLink, .comingSoon {
    text-decoration: none;
    color: inherit;
    box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
    border-radius: 8px;
    padding: 1rem;
    color: rgb(0, 0, 0);
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
  
</style>