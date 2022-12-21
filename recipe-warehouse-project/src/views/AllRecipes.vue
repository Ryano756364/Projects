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

<style scoped>

  .recipes {
    display: grid;
  }

  h3, p{
    margin-bottom: 5px;
  }

  .card {
    border-style: solid;
    border-radius: 5px;
    border-width: 2px;
    background-color: white;
    padding: 1rem;
    margin: 1rem;
    max-width: 100%;
  }

  #return-to-home {
    margin-top: 3rem;
  }
</style>