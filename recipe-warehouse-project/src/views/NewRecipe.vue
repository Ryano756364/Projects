<template>
  <div class="add-recipe-popup">
      <div class="popup-content">
        <h2>Add New Recipe</h2>

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
            <label>Method</label>
            <div class="method" v-for="i in newRecipe.methodRows" :key="i">
              <textarea v-model="newRecipe.method[i - 1]"></textarea>
            </div>
            <button type="button" @click="addNewStep">Add Step</button>
          </div>

          <button type="submit">Add Recipe</button>
          
        </form>

        <router-link :to="'/allrecipes'">
            <button>Return To All Recipes</button>
        </router-link> 

        <router-link :to="'/'">
            <button>Return To Home</button>
        </router-link> 

      </div>
    </div>
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
    //when button is clicked, new row is added
    //using value because otherwise we will be grabbing functionality versus data of newRecipef

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
  /* .home {
    padding: 1rem;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  h2 {
    border-style: solid;
    border-width: 2px;
    border-radius: 8px;
    box-shadow: 0 5px 30px rgba(0, 0, 0, 0.30);
    color: #0f936c;
  } */

</style>