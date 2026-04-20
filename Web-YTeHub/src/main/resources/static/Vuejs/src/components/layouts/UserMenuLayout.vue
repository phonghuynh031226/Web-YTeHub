<script setup>
import Menu from './menu.vue'
import Header from './header.vue'
import Footer from './footer.vue'
</script>

<template>
<Header />

<main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
        <Menu />

        <router-view />
        </main>
        <Footer />
</template>