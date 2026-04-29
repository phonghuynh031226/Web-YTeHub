<template>
  <main class="pt-32 pb-24 px-8 max-w-screen-2xl mx-auto min-h-screen">
    <div class="mb-12">
      <h1 class="font-headline text-4xl font-extrabold tracking-tight text-primary mb-2">Giỏ hàng của bạn</h1>
    </div>

    <div v-if="errorMessage" class="mb-6 rounded-lg bg-red-50 text-red-700 px-4 py-3">{{ errorMessage }}</div>

    <div class="grid grid-cols-1 lg:grid-cols-12 gap-12 items-start">
      <div class="lg:col-span-8">
        <div v-if="items.length === 0" class="text-on-surface-variant space-y-6">
          <p>Giỏ hàng trống</p>
          <router-link to="/products" class="inline-flex items-center gap-2 text-primary font-bold hover:translate-x-[-4px] transition-transform">
            <span class="material-symbols-outlined">arrow_back</span>Tiếp tục mua sắm
          </router-link>
        </div>

        <div v-else class="space-y-6">
          <label class="inline-flex items-center gap-3 bg-white rounded-xl px-5 py-3 shadow-sm font-bold text-primary">
            <input type="checkbox" :checked="allAvailableSelected" @change="toggleSelectAll" />
            Chọn tất cả sản phẩm còn hàng
          </label>

          <div v-for="item in items" :key="item.cartID" :class="['bg-surface-container-lowest rounded-xl p-6 flex flex-col md:flex-row items-center gap-6 shadow-[0px_20px_40px_rgba(25,28,30,0.04)]', isOutOfStock(item) ? 'opacity-60 grayscale' : '']">
            <label class="flex items-center gap-2 font-semibold text-primary">
              <input type="checkbox" :disabled="isOutOfStock(item)" :value="item.cartID" v-model="selectedCartIds" />
            </label>

            <div class="w-32 h-32 bg-surface-container rounded-lg overflow-hidden flex-shrink-0">
              <img :src="getImageUrl(item.product.imageURL)" class="w-full h-full object-cover" />
            </div>

            <div class="flex-grow">
              <div class="flex justify-between items-start mb-2">
                <h3 class="font-headline font-bold text-lg text-on-surface">{{ item.product.productName }}</h3>
                <button @click="removeItem(item.cartID)" class="text-outline hover:text-error transition-colors">
                  <span class="material-symbols-outlined">delete</span>
                </button>
              </div>

              <p v-if="isOutOfStock(item)" class="mb-3 inline-flex items-center gap-2 rounded-lg bg-red-50 text-red-700 px-3 py-2 text-sm font-semibold">
                <span class="material-symbols-outlined text-base">warning</span>Sản phẩm đã hết hàng. Không thể chọn để thanh toán.
              </p>

              <div class="flex flex-wrap items-center justify-between gap-4">
                <div v-if="!isOutOfStock(item)" class="flex items-center bg-surface-container-high rounded-full px-4 py-2">
                  <button @click="updateQty(item, item.quantity - 1)" class="w-8 h-8 flex items-center justify-center text-primary hover:bg-primary/10 rounded-full transition-all"><span class="material-symbols-outlined text-sm">remove</span></button>
                  <span class="w-12 text-center font-bold text-on-surface">{{ item.quantity }}</span>
                  <button @click="updateQty(item, item.quantity + 1)" class="w-8 h-8 flex items-center justify-center text-primary hover:bg-primary/10 rounded-full transition-all"><span class="material-symbols-outlined text-sm">add</span></button>
                </div>

                <div class="text-right"><p class="text-xs text-on-surface-variant uppercase tracking-wider font-semibold mb-1">Đơn giá</p><p class="font-headline font-bold text-primary">{{ formatPrice(item.product.price) }} ₫</p></div>
                <div class="text-right border-l border-outline-variant/20 pl-6 hidden md:block"><p class="text-xs text-on-surface-variant uppercase tracking-wider font-semibold mb-1">Thành tiền</p><p class="font-headline font-extrabold text-primary text-xl">{{ formatPrice(item.product.price * item.quantity) }} ₫</p></div>
              </div>
            </div>
          </div>

          <div class="pt-8"><router-link to="/products" class="inline-flex items-center gap-2 text-primary font-bold hover:translate-x-[-4px] transition-transform"><span class="material-symbols-outlined">arrow_back</span>Tiếp tục mua sắm</router-link></div>
        </div>
      </div>

      <div class="lg:col-span-4 sticky top-32" v-if="items.length > 0">
        <div class="bg-surface-container-low rounded-xl p-8 space-y-8">
          <h2 class="font-headline font-bold text-xl text-on-surface">Tóm tắt đơn hàng</h2>
          <div class="flex justify-between items-center text-on-surface-variant"><span>Đã chọn</span><span class="font-semibold text-on-surface">{{ selectedItems.length }} sản phẩm</span></div>
          <div class="flex justify-between items-center text-on-surface-variant"><span>Tạm tính</span><span class="font-semibold text-on-surface">{{ formatPrice(totalAmount) }} ₫</span></div>
          <div class="border-t border-outline-variant/15 pt-6">
            <div class="flex justify-between items-end mb-8"><div><p class="text-sm text-on-surface-variant font-medium">Tổng cộng</p><p class="text-xs text-on-surface-variant/70 italic">(Đã bao gồm VAT)</p></div><p class="font-headline font-black text-3xl text-primary tracking-tighter">{{ formatPrice(totalAmount) }} ₫</p></div>
            <button @click="goCheckout" :disabled="selectedItems.length === 0" class="w-full py-4 rounded-xl font-headline font-bold text-lg flex items-center justify-center gap-3 transition-all" :class="selectedItems.length ? 'bg-primary text-on-primary shadow-lg hover:bg-primary-container' : 'bg-slate-300 text-slate-500 cursor-not-allowed'">
              Tiến hành thanh toán <span class="material-symbols-outlined">arrow_forward</span>
            </button>
            <p v-if="hasOutOfStockItems" class="mt-4 rounded-lg bg-yellow-50 text-yellow-700 px-4 py-3 text-sm font-semibold">Sản phẩm hết hàng vẫn ở giỏ nhưng sẽ không được chọn để thanh toán.</p>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const items = ref([])
const selectedCartIds = ref([])
const errorMessage = ref('')
const API_BASE = 'http://localhost:8080'

const isOutOfStock = (item) => Number(item?.product?.stock || 0) <= 0
const availableItems = computed(() => items.value.filter(i => !isOutOfStock(i)))
const selectedItems = computed(() => items.value.filter(i => selectedCartIds.value.includes(i.cartID) && !isOutOfStock(i)))
const hasOutOfStockItems = computed(() => items.value.some(isOutOfStock))
const allAvailableSelected = computed(() => availableItems.value.length > 0 && availableItems.value.every(i => selectedCartIds.value.includes(i.cartID)))
const totalAmount = computed(() => selectedItems.value.reduce((sum, item) => sum + Number(item.product.price || 0) * Number(item.quantity || 0), 0))

const loadCart = async () => {
  try {
    const res = await axios.get(`${API_BASE}/api/cart`, { withCredentials: true })
    items.value = res.data || []
    selectedCartIds.value = availableItems.value.map(i => i.cartID)
  } catch (error) { errorMessage.value = error.response?.data?.message || 'Không tải được giỏ hàng' }
}
const toggleSelectAll = () => { selectedCartIds.value = allAvailableSelected.value ? [] : availableItems.value.map(i => i.cartID) }
const goCheckout = () => { localStorage.setItem('selectedCartIds', JSON.stringify(selectedCartIds.value)); router.push('/checkout') }
const updateQty = async (item, qty) => { if (qty < 1) return; try { await axios.put(`${API_BASE}/api/cart/${item.cartID}`, { quantity: qty }, { withCredentials: true }); loadCart() } catch (error) { alert(error.response?.data?.message || 'Cập nhật thất bại') } }
const removeItem = async (cartId) => { try { await axios.delete(`${API_BASE}/api/cart/${cartId}`, { withCredentials: true }); selectedCartIds.value = selectedCartIds.value.filter(id => id !== cartId); loadCart() } catch (error) { alert(error.response?.data?.message || 'Xóa thất bại') } }
const formatPrice = (price) => Number(price || 0).toLocaleString('vi-VN')
const getImageUrl = (imageURL) => imageURL ? `${API_BASE}/images/${imageURL}` : 'https://via.placeholder.com/150'
onMounted(loadCart)
</script>
