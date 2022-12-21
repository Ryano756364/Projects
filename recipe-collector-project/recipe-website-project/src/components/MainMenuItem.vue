<template>
  <section>
    <div class="menu-button">
      <h2>{{ menuItem }}</h2>
    </div>
  </section>
</template>

<script>

export default {
  //Wanted to show control over data coming into prop
  props: {
    id: {
      type: Number, 
      required: true
    },
    menuItem: {
      type: String, 
      required: true
    }
  }
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