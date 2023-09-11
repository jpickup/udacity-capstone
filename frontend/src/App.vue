<template>
  <!--nav>
    <router-link to="/">Home</router-link>
    <router-link to="/upload">Upload</router-link>
  </nav-->
  <router-view/>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
html {
    font-size: 62.5%;    
    box-sizing: border-box;
}

body {
    margin: 0;
    padding: 0;
    font-family: 'Roboto', sans-serif;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>