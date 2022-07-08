<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const userName = ref("")
const password = ref("")

const router = useRouter()

const login = function() {
    axios.post('api/login', {
        userName: userName.value,
        password: password.value,
    })
    .then(() => {
        router.replace({ name: 'home' })
    })
}

</script>

<template>
    <div>
        <el-input v-model="userName" placeholder="아이디"/>
    </div>

    <div class="mt-2">
        <el-input v-model="password" placeholder="비밀번호"/>
    </div>

    <div class="mt-2">
        <el-button type="primary" @click="login()">로그인</el-button>
    </div>
</template>

<style>

</style>
