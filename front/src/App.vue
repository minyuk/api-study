<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/login">로그인</RouterLink>
      </nav>
  </header>

  <RouterView />
</template>

<style>

</style>
