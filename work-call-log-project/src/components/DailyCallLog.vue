<template>

  <div class="call-log">
    <h2>My Calls</h2>
    <input type="text" v-model="enteredClient" />
    <button @click="addClient">Add Client To Call</button>
    <p v-if="clients.length === 0">
      No clients have been added yet - please start adding some!
    </p>
    <ul v-else>
      <p>Click name to remove from list</p>
      <li v-for="(client, index) in clients" :key="client" @click="removeClient(index)">
        <p> {{ client }} </p>
      </li>
    </ul>
  </div>
  
</template>

<script>
export default {
  name: 'DailyCallLog',
  data() {
    return { 
      enteredClient: '',
      clients: []
     };
  },
  methods: {
    addClient() {
      this.clients.push(this.enteredClient);
    },
    removeClient(idx) {
      this.clients.splice(idx, 1);
    }
  }
}
</script>

<style>
.call-log {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.26);
  margin: 3rem;
  border-radius: 10px;
  padding: 1rem 1rem 3rem 1rem;
  text-align: center;
}

.call-log h2 {
  font-size: 2rem;
  border-bottom: 4px solid rgb(157, 189, 204);
  color: #0c5da0;
  margin: 0 0 2rem 0;
}

.call-log ul {
  list-style: none;
  margin: 1rem 0;
  padding: 0;
}

.call-log li {
  margin: 1rem 0;
  font-size: 1.25rem;
  font-weight: bold;
  background-color: #6eaaee4d;
  padding: 0.5rem;
  color: #1f1f1f;
  border-radius: 25px;
}

.call-log input {
  font: inherit;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.call-log input:focus {
  outline: none;
  border-color: #61c0d8;
  background-color: #d7feff;
  border-radius: 5px;
}

.call-log button {
  font: inherit;
  cursor: pointer;
  border: 1px solid #00ccff;
  background-color: #00ccff;
  color: white;
  padding: 0.05rem 1rem;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.26);
  border-radius: 5px;
}

.call-log button:hover,
.call-log button:active {
  background-color: #61c0d8;
  border-color: #00ccff;
  box-shadow: 1px 1px 4px rgba(0, 0, 0, 0.26);
  border-radius: 5px;
}
</style>