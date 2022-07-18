<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <header>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/join">회원가입</RouterLink>
        <RouterLink to="/login">로그인</RouterLink>
        <RouterLink to="/logout">로그아웃</RouterLink>
      </nav>
  </header>

  <RouterView />
</template>

<style>

</style>
