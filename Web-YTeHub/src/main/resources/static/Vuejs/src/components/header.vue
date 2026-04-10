<template>

<!-- TopNavBar Navigation -->
<nav class="fixed top-0 w-full z-50 bg-surface/70 backdrop-blur-md border-b border-outline-variant/10">
<div class="flex justify-between items-center h-20 px-8 w-full max-w-screen-2xl mx-auto">
<div class="text-2xl font-black tracking-tighter text-[#003f87]">
            Clinical Curator
        </div>
<div class="hidden md:flex items-center space-x-8">
<router-link 
  to="/" 
  class="text-[#003f87] font-bold border-b-2 border-[#003f87] pb-1 font-['Manrope'] tracking-tight"
>
  Trang chủ
</router-link>
<router-link 
  to="/products"
  class="text-on-surface-variant font-medium hover:text-[#0056b3] transition-colors duration-300 font-['Manrope'] tracking-tight"
>
  Sản phẩm
</router-link>
</div>
<div class="flex items-center space-x-6">
<router-link to="/cart">
  <button class="p-2 hover:bg-primary-container/10 rounded-full transition-all active:scale-95">
    <span class="material-symbols-outlined text-[#0056b3]">
      shopping_cart
    </span>
  </button>
</router-link>
<router-link to="/profile">
  <button class="p-2 hover:bg-primary-container/10 rounded-full transition-all active:scale-95">
    <span class="material-symbols-outlined text-[#0056b3]">
      account_circle
    </span>
  </button>
</router-link>
<router-link to="/login">
  <button class="bg-primary text-on-primary px-6 py-2 rounded-lg font-bold tracking-tight font-['Manrope'] hover:bg-primary-container transition-all active:scale-95 shadow-sm text-sm">
    Đăng nhập
  </button>
</router-link>
</div>
</div>
</nav>

</template>