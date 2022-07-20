<script setup lang="ts">
import axios from 'axios'
import {ref} from 'vue'

  const user = ref([])

  axios.get('/api/').then((response) => {
    user.value = response.data
  })
</script>

<template>
  <h2>{{user.userName}}</h2>

</template>
