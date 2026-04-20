<template>
  <main class="pt-32 pb-24 px-8 max-w-screen-2xl mx-auto min-h-screen">
    <div class="mb-12">
      <h1 class="font-headline text-4xl font-extrabold tracking-tight text-primary mb-2">
        Giỏ hàng của bạn
      </h1>
    </div>

    <div v-if="errorMessage" class="mb-6 rounded-lg bg-red-50 text-red-700 px-4 py-3">
      {{ errorMessage }}
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-12 items-start">
      <!-- Product List Section -->
      <div class="lg:col-span-8">
        <div v-if="items.length === 0" class="text-on-surface-variant space-y-6">
          <p>Giỏ hàng trống</p>

          <router-link
            to="/products"
            class="inline-flex items-center gap-2 text-primary font-bold hover:translate-x-[-4px] transition-transform"
          >
            <span class="material-symbols-outlined">arrow_back</span>
            Tiếp tục mua sắm
          </router-link>
        </div>

        <div v-else class="space-y-6">
          <div
            v-for="item in items"
            :key="item.cartID"
            class="bg-surface-container-lowest rounded-xl p-6 flex flex-col md:flex-row items-center gap-6 shadow-[0px_20px_40px_rgba(25,28,30,0.04)]"
          >
            <div class="w-32 h-32 bg-surface-container rounded-lg overflow-hidden flex-shrink-0">
              <img
                :src="getImageUrl(item.product.imageURL)"
                class="w-full h-full object-cover"
              />
            </div>

            <div class="flex-grow">
              <div class="flex justify-between items-start mb-2">
                <h3 class="font-headline font-bold text-lg text-on-surface">
                  {{ item.product.productName }}
                </h3>

                <button @click="removeItem(item.cartID)" class="text-outline hover:text-error transition-colors">
                  <span class="material-symbols-outlined">delete</span>
                </button>
              </div>

              <p class="text-sm text-on-surface-variant mb-4">
                SKU: {{ item.product.productID }}
              </p>

              <div class="flex flex-wrap items-center justify-between gap-4">
                <div class="flex items-center bg-surface-container-high rounded-full px-4 py-2">
                  <button
                    @click="updateQty(item, item.quantity - 1)"
                    class="w-8 h-8 flex items-center justify-center text-primary hover:bg-primary/10 rounded-full transition-all"
                  >
                    <span class="material-symbols-outlined text-sm">remove</span>
                  </button>

                  <span class="w-12 text-center font-bold text-on-surface">
                    {{ item.quantity }}
                  </span>

                  <button
                    @click="updateQty(item, item.quantity + 1)"
                    class="w-8 h-8 flex items-center justify-center text-primary hover:bg-primary/10 rounded-full transition-all"
                  >
                    <span class="material-symbols-outlined text-sm">add</span>
                  </button>
                </div>

                <div class="text-right">
                  <p class="text-xs text-on-surface-variant uppercase tracking-wider font-semibold mb-1">Đơn giá</p>
                  <p class="font-headline font-bold text-primary">
                    {{ formatPrice(item.product.price) }} ₫
                  </p>
                </div>

                <div class="text-right border-l border-outline-variant/20 pl-6 hidden md:block">
                  <p class="text-xs text-on-surface-variant uppercase tracking-wider font-semibold mb-1">Thành tiền</p>
                  <p class="font-headline font-extrabold text-primary text-xl">
                    {{ formatPrice(item.product.price * item.quantity) }} ₫
                  </p>
                </div>
              </div>
            </div>
          </div>

          <div class="pt-8">
            <router-link
              to="/products"
              class="inline-flex items-center gap-2 text-primary font-bold hover:translate-x-[-4px] transition-transform"
            >
              <span class="material-symbols-outlined">arrow_back</span>
              Tiếp tục mua sắm
            </router-link>
          </div>
        </div>
      </div>

      <!-- Summary Sidebar -->
      <div class="lg:col-span-4 sticky top-32" v-if="items.length > 0">
        <div class="bg-surface-container-low rounded-xl p-8 space-y-8">
          <h2 class="font-headline font-bold text-xl text-on-surface">Tóm tắt đơn hàng</h2>

          <div class="space-y-4">
            <div class="flex justify-between items-center text-on-surface-variant">
              <span>Tạm tính</span>
              <span class="font-semibold text-on-surface">{{ formatPrice(totalAmount) }} ₫</span>
            </div>
          </div>

          <div class="border-t border-outline-variant/15 pt-6">
            <div class="flex justify-between items-end mb-8">
              <div>
                <p class="text-sm text-on-surface-variant font-medium">Tổng cộng</p>
                <p class="text-xs text-on-surface-variant/70 italic">(Đã bao gồm VAT)</p>
              </div>
              <p class="font-headline font-black text-3xl text-primary tracking-tighter">
                {{ formatPrice(totalAmount) }} ₫
              </p>
            </div>

            <router-link to="/checkout">
              <button class="w-full bg-primary text-on-primary py-4 rounded-xl font-headline font-bold text-lg shadow-lg hover:bg-primary-container active:scale-[0.98] transition-all flex items-center justify-center gap-3">
                Tiến hành thanh toán
                <span class="material-symbols-outlined">arrow_forward</span>
              </button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

const items = ref([])
const errorMessage = ref('')
const API_BASE = 'http://localhost:8080'

const loadCart = async () => {
  try {
    const res = await axios.get('http://localhost:8080/api/cart', {
      withCredentials: true
    })
    items.value = res.data || []
  } catch (error) {
    errorMessage.value = error.response?.data?.message || 'Không tải được giỏ hàng'
  }
}

const updateQty = async (item, qty) => {
  if (qty < 0) return

  try {
    await axios.put(
      `http://localhost:8080/api/cart/${item.cartID}`,
      { quantity: qty },
      { withCredentials: true }
    )
    loadCart()
  } catch (error) {
    alert(error.response?.data?.message || 'Cập nhật thất bại')
  }
}

const removeItem = async (cartId) => {
  try {
    await axios.delete(`http://localhost:8080/api/cart/${cartId}`, {
      withCredentials: true
    })
    loadCart()
  } catch (error) {
    alert(error.response?.data?.message || 'Xóa thất bại')
  }
}

const totalAmount = computed(() =>
  items.value.reduce((sum, item) => sum + Number(item.product.price || 0) * Number(item.quantity || 0), 0)
)

const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')

const getImageUrl = (imageURL) => imageURL
  ? `${API_BASE}/images/${imageURL}`
  : 'https://via.placeholder.com/150'

onMounted(loadCart)
</script>