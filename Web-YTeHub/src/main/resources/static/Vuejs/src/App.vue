<script setup>
import Header from './components/header.vue'
import Footer from './components/footer.vue'
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
    <Header />
  
  <router-view />
  
    <Footer />
</template>
