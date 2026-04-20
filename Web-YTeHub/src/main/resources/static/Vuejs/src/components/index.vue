<template>
  <main class="pt-20">
    <CenterToast :visible="toast.visible" :message="toast.message" :type="toast.type" />
    <section class="relative min-h-[819px] flex items-center bg-surface-container-low overflow-hidden">
      <div class="max-w-screen-2xl mx-auto px-8 grid grid-cols-1 lg:grid-cols-2 gap-12 items-center w-full">
        <div class="z-10">
          <span class="inline-block px-4 py-1.5 rounded-full bg-tertiary-container text-on-tertiary-container text-xs font-bold tracking-widest uppercase mb-6">
            Tiêu chuẩn y tế quốc tế
          </span>
          <h1 class="text-5xl md:text-7xl font-extrabold text-primary tracking-tight leading-[1.1] mb-8">
            Thiết bị y tế <br /><span class="text-primary-container">chính hãng.</span>
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
          <img
            alt="Medical Professional"
            class="absolute z-10 w-[90%] h-full object-cover rounded-3xl shadow-2xl transform rotate-2 hover:rotate-0 transition-transform duration-700"
            src="https://lh3.googleusercontent.com/aida-public/AB6AXuBICcx9liugbi_cUcjwd4EU40bCmYb4CNC96iZWiLT08XHTvJKzxv0yXIvmp3u5M325igUB1GQKuaM-oxGmu-QOEsMI2b1dgsZaN2k9Za9drPWyk1QUURIhooLs6j63k_WOem8znuqPOdFfmwXpW9rn7eb6pGslJy4P8uNmXMbxHv217cQ8Rr8tO1OHWRejyPw84yz5jprtW3Aco9mwZwBVZs2jpZVVmUtYPVXuG4VKPbGKTKppTQZHvhwAK3K4XjfL9ZwYR5OVS5Y"
          />
        </div>
      </div>
    </section>

    <section class="py-24 border border-secondary-container/30 rounded-[2.5rem] shadow-sm p-8 md:p-12 mx-4 md:mx-8 mb-24 bg-[#f0f7ff]">
      <div class="max-w-screen-2xl mx-auto px-8 relative">
        <div class="flex justify-between items-end mb-12">
          <div>
            <h2 class="text-3xl md:text-4xl font-extrabold text-primary mb-4 tracking-tight">Sản phẩm nổi bật</h2>
            <p class="text-on-surface-variant">Các dòng sản phẩm được quan tâm nhiều nhất trong tháng qua.</p>
          </div>
        </div>

        <div class="flex gap-6 overflow-x-auto no-scrollbar snap-x pb-4">
          <div
            v-for="product in featuredProducts"
            :key="product.productID"
            @click="goToDetail(product.productID)"
            class="min-w-[280px] md:min-w-[320px] snap-start bg-surface-container-lowest p-6 rounded-2xl border border-secondary-container/50 shadow-sm hover:shadow-xl hover:border-secondary-container transition-all group cursor-pointer"
          >
            <div class="h-48 rounded-xl bg-surface mb-6 overflow-hidden flex items-center justify-center">
              <img
                :alt="product.productName"
                class="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
                :src="getImageUrl(product.imageURL)"
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
                @click.stop="addToCart(product)"
                class="bg-primary-container/10 p-2.5 rounded-md text-primary hover:bg-primary hover:text-white transition-colors"
              >
                <span class="material-symbols-outlined">add_shopping_cart</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="py-24 bg-surface">
      <div class="max-w-screen-2xl mx-auto px-8">
        <div class="text-center mb-16">
          <h2 class="text-4xl md:text-5xl font-extrabold text-primary mb-6 tracking-tight">Sản phẩm mới</h2>
          <p class="text-lg text-on-surface-variant max-w-2xl mx-auto">
            Cập nhật những công nghệ y tế mới nhất được tin dùng bởi các chuyên gia y tế trên toàn quốc.
          </p>
        </div>

        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8 mb-16">
          <div
            v-for="product in newProducts"
            :key="product.productID"
            @click="goToDetail(product.productID)"
            class="bg-surface-container-lowest p-6 rounded-xl border border-outline-variant/10 hover:shadow-xl transition-all duration-300 flex flex-col group cursor-pointer"
          >
            <div class="relative overflow-hidden rounded-lg mb-6 aspect-square bg-surface">
              <img
                :alt="product.productName"
                class="w-full h-full object-contain mix-blend-multiply group-hover:scale-105 transition-transform"
                :src="getImageUrl(product.imageURL)"
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

              <button
                @click.stop="addToCart(product)"
                class="bg-primary-container/10 p-2.5 rounded-md text-primary hover:bg-primary hover:text-white transition-colors"
              >
                <span class="material-symbols-outlined">add_shopping_cart</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import CenterToast from './common/CenterToast.vue'

const API_BASE = 'http://localhost:8080'
const router = useRouter()
const featuredProducts = ref([])
const newProducts = ref([])
const toast = ref({ visible: false, message: '', type: 'success' })
let toastTimer = null

const showToast = (message, type = 'success') => {
  if (toastTimer) clearTimeout(toastTimer)
  toast.value = { visible: true, message, type }
  toastTimer = setTimeout(() => {
    toast.value.visible = false
  }, 1800)
}

const goToDetail = (id) => {
  router.push(`/detail/${id}`)
}

const addToCart = async (product) => {
  try {
    await axios.post(
      'http://localhost:8080/api/cart',
      {
        productId: product.productID,
        quantity: 1
      },
      {
        withCredentials: true
      }
    )
    showToast('Đã thêm vào giỏ hàng')
  } catch (error) {
    if (error.response?.status === 401) {
      router.push('/login')
      return
    }
    showToast(error.response?.data?.message || 'Thêm vào giỏ hàng thất bại', 'error')
  }
}

const loadFeaturedProducts = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/products/featured')
    featuredProducts.value = res.data
  } catch (error) {
    console.error('Lỗi tải sản phẩm nổi bật:', error)
  }
}

const loadNewProducts = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/products/new')
    newProducts.value = res.data
  } catch (error) {
    console.error('Lỗi tải sản phẩm mới:', error)
  }
}

const formatPrice = (price) => {
  return Number(price || 0).toLocaleString('vi-VN')
}

const getImageUrl = (imageURL) => {
  return imageURL ? `${API_BASE}/images/${imageURL}` : 'https://via.placeholder.com/300x300?text=No+Image'
}

onMounted(() => {
  loadFeaturedProducts()
  loadNewProducts()
})
</script>