<template>
  <section id="add-recipe">
    <h2>Add New Recipe</h2>

    <div class="recipe-content">

      <form @submit.prevent="addNewRecipe">
        <div class="group">
          <label>Title</label>
          <input type="text" v-model="newRecipe.title"/>
        </div>

        <div class="group">
          <label>Description</label>
          <textarea v-model="newRecipe.description"></textarea>
        </div>

        <div class="group">
          <label>Ingredients</label>
          <div class="ingredient" v-for="i in newRecipe.ingredientRows" :key="i">
            <input type="text" v-model="newRecipe.ingredients[i - 1]"/>
          </div>
          <button type="button" @click="addNewIngredient">Add Ingredient</button>
        </div>

        <div class="group">
          <label>Steps</label>
          <div class="method" v-for="i in newRecipe.methodRows" :key="i">
            <textarea v-model="newRecipe.method[i - 1]"></textarea>
          </div>
          <button type="button" @click="addNewStep">Add Step</button>
        </div>

        <button type="submit">Add Recipe</button>
        
      </form>

      <section id="bottom-buttons">
        <router-link :to="'/allrecipes'">
            <button>Go To All Recipes</button>
        </router-link> 

        <router-link :to="'/'">
            <button>Return To Home</button>
        </router-link> 
      </section>
    </div>
  </section>
</template>


<script>
  import { ref } from 'vue';

  // Vuex allows us to access the store from the composition API
  import {useStore } from 'vuex';

  export default {
    setup () {
    const newRecipe = ref({
      title: '',
      description: '',
      ingredients: [],
      method: [],
      ingredientRows: 1,
      methodRows: 1
    });

    const store = useStore();
    
      const addNewIngredient = () => {
        //when button is clicked, new row is added
        newRecipe.value.ingredientRows++;
      }

      const addNewStep = () => {
        //when button is clicked, new row is added
        newRecipe.value.methodRows++;
      }

      const addNewRecipe = () => {
        //using value because otherwise we will be grabbing functionality versus data of newRecipef
        newRecipe.value.slug = newRecipe.value.title.toLowerCase().replace(/\s/g, '-');

        if (newRecipe.value.slug == ''){
          alert("Please enter a title");
          return;
        }
      
      // ...newRecipe.value is called destructuring your object
      store.commit('ADD_RECIPE', { ...newRecipe.value });

      newRecipe.value = {
        title: '',
        description: '',
        ingredients: [],
        method: [],
        ingredientRows: 1,
        methodRows: 1
      };
    }

      //pass constants into function below
      return { 
        newRecipe,
        addNewIngredient,
        addNewStep,
        addNewRecipe
      }
    }
  }

</script>

<style>
  #add-recipe {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  h2 {
    border-style: solid;
    border-width: 2px;
    border-radius: 8px;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.30);
    background-color: #0f936c;
    margin-top: 1rem;
    font-size: 2.25rem;
    margin-bottom: 1rem;
  }

  .recipe-content {
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
    font-size: 1.75rem;
  }

  form{
    width: 100%;
  }

  label {
    display: block;
  }

  input, textarea{
    width: 100%;
    border: 1.5px solid black;
    border-radius: 5px;
    padding: 0.5rem;
    margin: 1rem;
  }

  textarea {
    height: 100px;
    resize: none;
  }

  .group {
    margin-bottom: 1.5rem;
  }

  button {
    border: none;
    cursor: pointer;
    background-color: #0f936c;
    font-size: 1.50rem;
    padding: 0.5rem 1rem;
    border-radius: 5px;
    border-style: solid;
    border-width: 2px;
    border-radius: 8px;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.30);
  }

  #bottom-buttons {
    margin-top: 30px;
  }

  #bottom-buttons button {
    font-size: 1rem;
  }

  a{
    margin: 5px;
  }

</style>