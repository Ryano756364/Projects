<template>
  <section>
    <div class="menu-button">
      <h2>Add New Recipe</h2>
    </div>
    <div class="menu-button">
      <h2>View All Recipes</h2>
    </div>
    <div class="menu-button">
      <h2>Search Recipes</h2>
    </div>
    <div class="menu-button">
      <h2>View Recipes By Person</h2>
    </div>
  </section>
</template>

<script>
export default {
  name: 'MainMenu'
}
</script>

<style>
 .menu-button{
  box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
  margin: 1.5rem 15rem;
  border-radius: 8px;
  padding: 0.5rem;
  background-color: #0cf9b2;
  color: rgb(0, 0, 0);
  text-align: center;
 }
</style>