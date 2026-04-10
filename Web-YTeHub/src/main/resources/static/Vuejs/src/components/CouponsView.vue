<template>

<div class="flex min-h-screen pt-20">
<!-- SideNavBar -->
<aside class="h-screen w-64 sticky top-20 left-0 bg-surface-container-low flex flex-col py-8 gap-2 overflow-y-auto" style="box-shadow: 20px 0px 40px rgba(25,28,30,0.04);">
<div class="px-6 mb-8">
<div class="flex items-center gap-3 mb-4">
<div class="w-12 h-12 rounded-full bg-primary-fixed flex items-center justify-center text-on-primary-fixed overflow-hidden">
<img class="w-full h-full object-cover" data-alt="professional portrait of a male doctor in a clean clinical setting with soft natural light" src="https://lh3.googleusercontent.com/aida-public/AB6AXuAdIbprC0GMADv-bN_VW6Zs5DK17rODO6M5HQy8pGK642xB3AmkvbK2rIyY0Woda_ENevS8Cpc3ezvwjEKYOxljgRYC_0AR-wOf5umQ3mEuwB26T9Kb9v-bDIi7WG_nCQyLfrGJ3wXALZnm2P93o8sdmTUW2yT92uDeCLUczmQm4cyKTKhJ1wsJMe9UFMtH0MQLfgowblZPQXELC1B3tlbmv1_2XAt2VYf5dFNjaoc3XV10pGaTJ6aC9yfrYhekjaNUz458biBMO0k"/>
</div>
<div>
<p class="font-headline font-bold text-on-surface leading-tight">My Account</p>
<p class="font-label text-xs text-on-surface-variant tracking-wide uppercase">Manage your medical profile</p>
</div>
</div>
</div>
<nav class="flex flex-col gap-1 pr-4">

  <!-- Profile -->
  <router-link 
    to="/profile"
    class="flex items-center gap-4 px-6 py-3 text-slate-600 hover:bg-slate-100 hover:pl-7 transition-all duration-300 font-label text-sm tracking-wide"
    active-class="bg-white text-blue-800 rounded-r-full font-bold shadow-sm"
  >
    <span class="material-symbols-outlined">person</span>
    <span>Profile</span>
  </router-link>

  <!-- Orders -->
  <router-link 
    to="/orders"
    class="flex items-center gap-4 px-6 py-3 text-slate-600 hover:bg-slate-100 hover:pl-7 transition-all duration-300 font-label text-sm tracking-wide"
    active-class="bg-white text-blue-800 rounded-r-full font-bold shadow-sm"
  >
    <span class="material-symbols-outlined">package</span>
    <span>Orders</span>
  </router-link>

  <!-- Addresses -->
  <router-link 
    to="/address"
    class="flex items-center gap-4 px-6 py-3 text-slate-600 hover:bg-slate-100 hover:pl-7 transition-all duration-300 font-label text-sm tracking-wide"
    active-class="bg-white text-blue-800 rounded-r-full font-bold shadow-sm"
  >
    <span class="material-symbols-outlined">location_on</span>
    <span>Addresses</span>
  </router-link>

  <!-- Vouchers -->
  <router-link 
    to="/coupons"
    class="flex items-center gap-4 px-6 py-3 text-slate-600 hover:bg-slate-100 hover:pl-7 transition-all duration-300 font-label text-sm tracking-wide"
    active-class="bg-white text-blue-800 rounded-r-full font-bold shadow-sm"
  >
    <span class="material-symbols-outlined">confirmation_number</span>
    <span>Vouchers</span>
  </router-link>

  <!-- Logout -->
  <button 
    @click="logout"
    class="flex items-center gap-4 px-6 py-3 text-slate-600 hover:bg-slate-100 hover:pl-7 transition-all duration-300 font-label text-sm tracking-wide mt-auto"
  >
    <span class="material-symbols-outlined">logout</span>
    <span>Logout</span>
  </button>

</nav>
</aside>
<!-- Main Content Area -->
<main class="flex-1 bg-background p-8 lg:p-12 overflow-x-hidden">
<header class="mb-10">
<h1 class="font-headline text-4xl font-extrabold text-primary mb-2">Kho mã giảm giá</h1>
<p class="text-on-surface-variant font-body">Lưu trữ và quản lý các ưu đãi dành riêng cho bạn tại Aura Medical.</p>
</header>
<!-- Filter Tabs -->
<div class="flex gap-4 mb-8">
<button class="px-6 py-2 bg-primary text-on-primary rounded-full font-bold text-sm shadow-sm transition-transform active:scale-95">Tất cả</button>
<button class="px-6 py-2 bg-surface-container-lowest text-on-surface-variant rounded-full font-semibold text-sm hover:bg-surface-container shadow-sm transition-all">Sắp hết hạn</button>
<button class="px-6 py-2 bg-surface-container-lowest text-on-surface-variant rounded-full font-semibold text-sm hover:bg-surface-container shadow-sm transition-all">Dịch vụ</button>
<button class="px-6 py-2 bg-surface-container-lowest text-on-surface-variant rounded-full font-semibold text-sm hover:bg-surface-container shadow-sm transition-all">Sản phẩm</button>
</div>
<!-- Voucher Grid -->
<div class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
<!-- Voucher Card 1 -->
<div class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
<div class="p-6 flex-1 relative ticket-cutout-right">
<div class="flex justify-between items-start mb-4">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">medication</span>
</div>
<span class="px-3 py-1 bg-tertiary-fixed text-on-tertiary-fixed-variant rounded-full text-[10px] font-bold tracking-widest uppercase">MỚI</span>
</div>
<h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm 50.000đ</h2>
<p class="font-body text-on-surface font-semibold mb-3">Mã giảm giá cho thiết bị y tế</p>
<div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
<span class="material-symbols-outlined text-sm">schedule</span>
<span>Hết hạn: 31/12/2024</span>
</div>
</div>
<div class="border-t border-dashed border-outline-variant mx-6"></div>
<div class="p-4 bg-slate-50 flex items-center justify-between">
<span class="text-[10px] text-slate-400 font-label tracking-tighter uppercase">Code: MEDI50K</span>
<button class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors active:scale-95">Sao chép mã</button>
</div>
</div>
<!-- Voucher Card 2 -->
<div class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
<div class="p-6 flex-1 relative ticket-cutout-right">
<div class="flex justify-between items-start mb-4">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">stethoscope</span>
</div>
<span class="px-3 py-1 bg-secondary-fixed text-on-secondary-fixed-variant rounded-full text-[10px] font-bold tracking-widest uppercase">Dịch Vụ</span>
</div>
<h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm 10%</h2>
<p class="font-body text-on-surface font-semibold mb-3">Gói khám tổng quát định kỳ</p>
<div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
<span class="material-symbols-outlined text-sm">schedule</span>
<span>Hết hạn: 15/11/2024</span>
</div>
</div>
<div class="border-t border-dashed border-outline-variant mx-6"></div>
<div class="p-4 bg-slate-50 flex items-center justify-between">
<span class="text-[10px] text-slate-400 font-label tracking-tighter uppercase">Code: CHECKUP10</span>
<button class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors active:scale-95">Sao chép mã</button>
</div>
</div>
<!-- Voucher Card 3 -->
<div class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
<div class="p-6 flex-1 relative ticket-cutout-right">
<div class="flex justify-between items-start mb-4">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">vaccines</span>
</div>
</div>
<h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm 100.000đ</h2>
<p class="font-body text-on-surface font-semibold mb-3">Voucher tiêm chủng cho bé</p>
<div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
<span class="material-symbols-outlined text-sm">schedule</span>
<span>Hết hạn: 20/12/2024</span>
</div>
</div>
<div class="border-t border-dashed border-outline-variant mx-6"></div>
<div class="p-4 bg-slate-50 flex items-center justify-between">
<span class="text-[10px] text-slate-400 font-label tracking-tighter uppercase">Code: VACCINE100</span>
<button class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors active:scale-95">Sao chép mã</button>
</div>
</div>
<!-- Voucher Card 4 -->
<div class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
<div class="p-6 flex-1 relative ticket-cutout-right">
<div class="flex justify-between items-start mb-4">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">ecg_heart</span>
</div>
</div>
<h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm 15%</h2>
<p class="font-body text-on-surface font-semibold mb-3">Xét nghiệm tim mạch chuyên sâu</p>
<div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
<span class="material-symbols-outlined text-sm text-error">warning</span>
<span class="text-error font-bold">Hết hạn: 31/10/2024</span>
</div>
</div>
<div class="border-t border-dashed border-outline-variant mx-6"></div>
<div class="p-4 bg-slate-50 flex items-center justify-between">
<span class="text-[10px] text-slate-400 font-label tracking-tighter uppercase">Code: HEART15</span>
<button class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors active:scale-95">Sao chép mã</button>
</div>
</div>
<!-- Voucher Card 5 -->
<div class="relative bg-surface-container-lowest rounded-xl overflow-hidden shadow-sm flex flex-col group transition-all hover:shadow-md border border-outline-variant/15">
<div class="p-6 flex-1 relative ticket-cutout-right">
<div class="flex justify-between items-start mb-4">
<div class="w-12 h-12 rounded-lg bg-primary/10 flex items-center justify-center text-primary">
<span class="material-symbols-outlined text-3xl">local_pharmacy</span>
</div>
</div>
<h2 class="font-headline text-2xl font-extrabold text-primary mb-1 leading-tight">Giảm 30.000đ</h2>
<p class="font-body text-on-surface font-semibold mb-3">Đơn thuốc trên 500.000đ</p>
<div class="flex items-center gap-2 text-on-surface-variant text-xs font-label">
<span class="material-symbols-outlined text-sm">schedule</span>
<span>Hết hạn: 05/01/2025</span>
</div>
</div>
<div class="border-t border-dashed border-outline-variant mx-6"></div>
<div class="p-4 bg-slate-50 flex items-center justify-between">
<span class="text-[10px] text-slate-400 font-label tracking-tighter uppercase">Code: PHARMA30</span>
<button class="px-6 py-2 bg-primary text-on-primary rounded-lg font-bold text-sm hover:bg-primary-container transition-colors active:scale-95">Sao chép mã</button>
</div>
</div>
<!-- Promotion Banner Card -->
</div>
</main>
</div>
</template>