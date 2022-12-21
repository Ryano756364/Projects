<template>
  <div class="recipe">
    <h2>{{ recipe.title }}</h2>
    <p id="description">{{ recipe.description }}</p>

    <div class="ingredients">
			<h3>Ingredients</h3>
			<ul>
				<li v-for="(ingredient, i) in recipe.ingredients" :key="i">
					{{ ingredient }}
				</li>
			</ul>
		</div>

		<div class="method">
			<h3>Method</h3>
			<ol>
				<li v-for="(step, i) in recipe.method" :key="i">
          <!--Incase there are line breaks submitted, this will clean up the text-->
					<span v-html="cleanText(step)"></span>
				</li>
			</ol>
		</div>

    <router-link to="/allrecipes">Go Back</router-link>
  </div>
</template>

<script>
export default {
	computed: {
		recipe () {
			return this.$store.state.recipes.find(recipe => 
      recipe.slug === this.$route.params.slug)
		}
	},

	methods: {
		cleanText (text) {
			return text.replace(/\n/g, '<br />')
		}
	}
}

</script>

<style>

</style>