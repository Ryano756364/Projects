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
			<h3>Steps</h3>
			<ol>
				<li class="recipe-step" v-for="(step, i) in recipe.method" :key="i">
          <!--Incase there are line breaks submitted, this will clean up the text-->
					<span v-html="cleanText(step)"></span>
				</li>
			</ol>
		</div>

    <router-link to="/allrecipes">
			<button>Go Back</button>
		</router-link>
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

<style scoped>

	.recipe {
		display: grid;
		padding: 1rem;
	}

	p{
		font-size: 1.25rem;
		text-align: left;
		border-bottom-style: solid;
		border-bottom-width: 1px;
		padding-bottom: 0.5rem;
		margin-bottom: 1rem;
	}

	.ingredients {
		padding: 1rem;
		background-color: white;
		border-radius: 0.5rem;
		margin-bottom: 2rem;
	}

	h3 {
		text-align: left;
	}

	ul {
		display: block;
		padding: 1rem;
	}

	.ingredients ul li {
		display: list-item;
		text-align: left;
		padding: 0.25rem;
	}

	.method ol {
		display: block;
		margin-inline-start: 0px;
		margin-top: 0.5rem;
	}

	.method ol li {
		display: list-item;
		text-align: left;
		border-bottom: 1px solid black;
		margin-bottom: 1rem;
		padding-bottom: 0.75rem;
		list-style-position: inside;
	}

</style>