<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const loginId = ref("")
const password = ref("")

const router = useRouter()

const login = function() {
    axios.post('api/login', {
        loginId: loginId.value,
        password: password.value,
    })
    .then(() => {
        router.replace({ name: 'home' })
    })
}

const logout = function() {
    axios.post('api/logout', {

    })
    .then(() => {
        router.replace({ name: 'home' })
    })
}

</script>

<template>
    <div>
        <el-input v-model="loginId" placeholder="아이디"/>
    </div>

    <div class="mt-2">
        <el-input v-model="password" type="password" placeholder="비밀번호"/>
    </div>

    <div class="mt-2">
        <el-button type="primary" @click="login()">로그인</el-button>
        <el-button type="primary" @click="logout()">로그아웃</el-button>
    </div>

    <div class="mt-2">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=7b50c8cfb314efb023fa626a6919a0b5&redirect_uri=http://localhost:8080/kakao/login&response_type=code"><img src="@/assets/loginButton.png" @click="loginwithKakao"/></a>
    </div>
</template>

<style>

</style>
