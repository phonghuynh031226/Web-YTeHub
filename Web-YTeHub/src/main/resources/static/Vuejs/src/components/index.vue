<template>

<main class="pt-20">
<!-- Hero Banner Section -->
<section class="relative min-h-[819px] flex items-center bg-surface-container-low overflow-hidden">
<div class="max-w-screen-2xl mx-auto px-8 grid grid-cols-1 lg:grid-cols-2 gap-12 items-center w-full">
<div class="z-10">
<span class="inline-block px-4 py-1.5 rounded-full bg-tertiary-container text-on-tertiary-container text-xs font-bold tracking-widest uppercase mb-6">
                Tiêu chuẩn y tế quốc tế
            </span>
<h1 class="text-5xl md:text-7xl font-extrabold text-primary tracking-tight leading-[1.1] mb-8">
                Thiết bị y tế <br/><span class="text-primary-container">chính hãng.</span>
</h1>
<p class="text-lg text-on-surface-variant leading-relaxed mb-10 max-w-lg">
                Mang đến giải pháp chăm sóc sức khỏe chuyên nghiệp với công nghệ hiện đại nhất từ các thương hiệu hàng đầu thế giới.
            </p>
<div class="flex flex-wrap gap-4">
<button class="bg-primary text-on-primary px-8 py-4 rounded-md font-semibold text-lg hover:bg-primary-container transition-all shadow-lg active:scale-95">
                    Khám phá ngay
                </button>
<button class="bg-surface-container-lowest border-2 border-outline-variant/20 text-primary px-8 py-4 rounded-md font-semibold text-lg hover:bg-surface-container transition-all active:scale-95">
                    Xem danh mục
                </button>
</div>
</div>
<div class="relative h-[600px] w-full lg:block hidden">
<div class="absolute inset-0 bg-gradient-to-tr from-primary/10 to-transparent rounded-full blur-3xl"></div>
<img alt="Medical Professional" class="absolute z-10 w-[90%] h-full object-cover rounded-3xl shadow-2xl transform rotate-2 hover:rotate-0 transition-transform duration-700" data-alt="Professional doctor in a white lab coat standing confidently in a modern medical facility with high-tech equipment in the background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuBICcx9liugbi_cUcjwd4EU40bCmYb4CNC96iZWiLT08XHTvJKzxv0yXIvmp3u5M325igUB1GQKuaM-oxGmu-QOEsMI2b1dgsZaN2k9Za9drPWyk1QUURIhooLs6j63k_WOem8znuqPOdFfmwXpW9rn7eb6pGslJy4P8uNmXMbxHv217cQ8Rr8tO1OHWRejyPw84yz5jprtW3Aco9mwZwBVZs2jpZVVmUtYPVXuG4VKPbGKTKppTQZHvhwAK3K4XjfL9ZwYR5OVS5Y"/>
<div class="absolute -bottom-6 -left-6 z-20 bg-surface-container-lowest p-6 rounded-xl shadow-xl max-w-xs">
<div class="flex items-center gap-4 mb-2">
<span class="material-symbols-outlined text-tertiary text-4xl" data-icon="verified_user">verified_user</span>
<div class="font-bold text-primary">Chứng nhận FDA</div>
</div>
<p class="text-sm text-on-surface-variant">Đảm bảo an toàn và chính xác tuyệt đối trong mọi phép đo.</p>
</div>
</div>
</div>
</section>
<!-- Featured Categories Section (Updated with borders and shadows) -->
<section class="py-24 border border-secondary-container/30 rounded-[2.5rem] shadow-sm p-8 md:p-12 mx-4 md:mx-8 mb-24 bg-[#f0f7ff]">
<div class="max-w-screen-2xl mx-auto px-8 relative">
<div class="flex justify-between items-end mb-12">
<div>
<h2 class="text-3xl md:text-4xl font-extrabold text-primary mb-4 tracking-tight">Sản phẩm nổi bật</h2>
<p class="text-on-surface-variant">Các dòng sản phẩm được quan tâm nhiều nhất trong tháng qua.</p>
</div>
<div class="hidden md:flex gap-3">
<button class="w-12 h-12 rounded-full border border-outline-variant/30 flex items-center justify-center text-primary hover:bg-primary hover:text-white transition-all active:scale-95 bg-surface-container-lowest shadow-sm">
<span class="material-symbols-outlined">chevron_left</span>
</button>
<button class="w-12 h-12 rounded-full border border-outline-variant/30 flex items-center justify-center text-primary hover:bg-primary hover:text-white transition-all active:scale-95 bg-surface-container-lowest shadow-sm">
<span class="material-symbols-outlined">chevron_right</span>
</button>
</div>
</div>
<div class="flex gap-6 overflow-x-auto no-scrollbar snap-x pb-4">
  <div
    v-for="product in featuredProducts"
    :key="product.productID"
    class="min-w-[280px] md:min-w-[320px] snap-start bg-surface-container-lowest p-6 rounded-2xl border border-secondary-container/50 shadow-sm hover:shadow-xl hover:border-secondary-container transition-all group"
  >
    <div class="h-48 rounded-xl bg-surface mb-6 overflow-hidden flex items-center justify-center">
      <img
        :alt="product.productName"
        class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
        :src="'/images/' + product.imageURL"
      />
    </div>

    <h3 class="font-bold text-on-surface text-lg mb-2">
      {{ product.productName }}
    </h3>

    <div class="flex items-center gap-1 mb-4">
      <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
      <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
      <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
      <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
      <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
      <span class="text-xs text-on-surface-variant ml-2">(Nổi bật)</span>
    </div>

    <div class="mt-auto flex justify-between items-center">
      <span class="text-primary font-extrabold text-xl">
        {{ formatPrice(product.price) }}đ
      </span>

      <button
        class="bg-primary-container/10 p-2.5 rounded-md text-primary hover:bg-primary hover:text-white transition-colors"
      >
        <span class="material-symbols-outlined" data-icon="add_shopping_cart">
          add_shopping_cart
        </span>
      </button>
    </div>
  </div>
</div>
</div>
</section>
<!-- New Products Bento Section -->
<section class="py-24 bg-surface">
<div class="max-w-screen-2xl mx-auto px-8">
<div class="text-center mb-16">
<h2 class="text-4xl md:text-5xl font-extrabold text-primary mb-6 tracking-tight">Sản phẩm mới</h2>
<p class="text-lg text-on-surface-variant max-w-2xl mx-auto">Cập nhật những công nghệ y tế mới nhất được tin dùng bởi các chuyên gia y tế trên toàn quốc.</p>
</div>
<div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8 mb-16">
<div
  v-for="product in newProducts"
  :key="product.productID"
  class="bg-surface-container-lowest p-6 rounded-xl border border-outline-variant/10 hover:shadow-xl transition-all duration-300 flex flex-col group"
>
  <div class="relative overflow-hidden rounded-lg mb-6 aspect-square bg-surface">
    <img
      :alt="product.productName"
      class="w-full h-full object-contain mix-blend-multiply group-hover:scale-105 transition-transform"
      :src="'/images/' + product.imageURL"
    />

    <span class="absolute top-3 left-3 bg-tertiary-fixed text-on-tertiary-fixed text-[10px] font-bold px-2 py-1 rounded">
      MỚI
    </span>
  </div>

  <h3 class="font-bold text-on-surface text-lg mb-2">
    {{ product.productName }}
  </h3>

  <div class="flex items-center gap-1 mb-4">
    <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
    <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
    <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
    <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
    <span class="material-symbols-outlined text-yellow-400 text-sm" style="font-variation-settings: 'FILL' 1;">star</span>
    <span class="text-xs text-on-surface-variant ml-2">(Mới thêm)</span>
  </div>

  <div class="mt-auto flex justify-between items-center">
    <span class="text-primary font-extrabold text-xl">
      {{ formatPrice(product.price) }}đ
    </span>

    <button class="bg-primary-container/10 p-2.5 rounded-md text-primary hover:bg-primary hover:text-white transition-colors">
      <span class="material-symbols-outlined" data-icon="add_shopping_cart">
        add_shopping_cart
      </span>
    </button>
  </div>
</div>

</div>
<div class="flex justify-center">
<button class="bg-surface-container-lowest border border-outline-variant/30 text-primary px-12 py-3 rounded-full font-bold text-lg hover:bg-primary hover:text-white transition-all active:scale-95 shadow-sm">
                Xem thêm
            </button>
</div>
</div>
</section>
<!-- Why Choose Us Section -->
<section class="py-24 max-w-screen-2xl mx-auto px-8 bg-surface">
<div class="grid grid-cols-1 md:grid-cols-3 gap-16">
<div class="flex flex-col items-center text-center group">
<div class="w-20 h-20 rounded-2xl bg-tertiary-container/10 flex items-center justify-center mb-8 group-hover:bg-tertiary-container/20 transition-colors">
<span class="material-symbols-outlined text-tertiary text-4xl" data-icon="verified">verified</span>
</div>
<h3 class="text-xl font-bold text-primary mb-4">Đảm bảo chất lượng</h3>
<p class="text-on-surface-variant leading-relaxed">
                100% sản phẩm nhập khẩu chính hãng, đầy đủ giấy tờ kiểm định CO/CQ từ các hãng thiết bị uy tín thế giới.
            </p>
</div>
<div class="flex flex-col items-center text-center group">
<div class="w-20 h-20 rounded-2xl bg-primary-container/10 flex items-center justify-center mb-8 group-hover:bg-primary-container/20 transition-colors">
<span class="material-symbols-outlined text-primary text-4xl" data-icon="local_shipping">local_shipping</span>
</div>
<h3 class="text-xl font-bold text-primary mb-4">Giao hàng nhanh</h3>
<p class="text-on-surface-variant leading-relaxed">
                Hệ thống logistics chuyên nghiệp, giao hàng hỏa tốc trong 2h tại nội thành và giao hàng an toàn toàn quốc.
            </p>
</div>
<div class="flex flex-col items-center text-center group">
<div class="w-20 h-20 rounded-2xl bg-secondary-container/10 flex items-center justify-center mb-8 group-hover:bg-secondary-container/20 transition-colors">
<span class="material-symbols-outlined text-secondary text-4xl" data-icon="support_agent">support_agent</span>
</div>
<h3 class="text-xl font-bold text-primary mb-4">Hỗ trợ 24/7</h3>
<p class="text-on-surface-variant leading-relaxed">
                Đội ngũ tư vấn là các bác sĩ và kỹ thuật viên y tế giàu kinh nghiệm luôn sẵn sàng giải đáp mọi thắc mắc.
            </p>
</div>
</div>
</section>
</main>

</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const featuredProducts = ref([])
const newProducts = ref([])

// load sản phẩm nổi bật
const loadFeaturedProducts = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/products/featured')
    featuredProducts.value = res.data
  } catch (error) {
    console.error('Lỗi tải sản phẩm nổi bật:', error)
  }
}

// load sản phẩm mới
const loadNewProducts = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/products/new')
    newProducts.value = res.data
  } catch (error) {
    console.error('Lỗi tải sản phẩm mới:', error)
  }
}

// format giá tiền
const formatPrice = (price) => {
  return Number(price).toLocaleString('vi-VN')
}

// chạy khi load trang
onMounted(() => {
  loadFeaturedProducts()
  loadNewProducts()
})
</script>