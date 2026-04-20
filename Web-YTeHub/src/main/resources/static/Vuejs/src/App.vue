<script setup>
import Header from './components/layouts/header.vue'
import Footer from './components/layouts/footer.vue'
import { onMounted } from 'vue'
import axios from 'axios'

onMounted(async () => {
  try {
    await axios.get('http://localhost:8080/api/auth/me', {
      withCredentials: true
    })
  } catch (e) {}
})
</script>

<template>
  <router-view />
</template>
