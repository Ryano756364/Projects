<template>
  <h2>All Recipes</h2>
  <div class="recipes">
    <div class="card" v-for="recipe in $store.state.recipes" :key="recipe.slug">
      <h3>{{  recipe.title }}</h3>
      <p>{{  recipe.description }}</p>
      <router-link :to="`/recipe/${recipe.slug}`">
        <button>View Recipe</button>
      </router-link>
    </div>
  </div>
  <router-link :to="'/'">
    <button id="return-to-home">Return To Home</button>
  </router-link>
</template>

<script>

</script>

<style>
  #return-to-home {
    margin-top: 3rem;
  }
</style>