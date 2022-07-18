<script setup lang="ts">
import {ref} from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const userName = ref("")
const loginId = ref("")
const password = ref("")

const router = useRouter()

const join = function() {
    axios.post('api/join', {
        userName: userName.value,
        loginId: loginId.value,
        password: password.value,
    })
    .then(() => {
        router.replace({ name: 'home' })
    })
}

</script>

<template>
    <div>
        <el-input v-model="userName" placeholder="이름"/>
    </div>

    <div class="mt-2">
        <el-input v-model="loginId" placeholder="아이디"/>
    </div>

    <div class="mt-2">
        <el-input v-model="password" type="password" placeholder="비밀번호"/>
    </div>

    <div class="mt-2">
        <el-button type="primary" @click="join()">회원가입</el-button>
    </div>
</template>

<style>

</style>
