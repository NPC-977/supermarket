<template>
  <div id="app">
<!--    <nav>-->
<!--&lt;!&ndash;      <router-link to="/">Home</router-link> |&ndash;&gt;-->
<!--&lt;!&ndash;      <router-link to="/about">About</router-link>&ndash;&gt;-->
<!--    </nav>-->
<!--    <router-view/>-->

    <router-view id="routerview"/>
  </div>
</template>

<script>

// import HelloWorld from '@/components/HelloWorld.vue'
import Login from '@/components/page/Login.vue'

export default {
  name: 'App',
  components: {
    Login
  }
}
</script>
<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

}

nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
#routerview {

}
</style>
