<template>

<main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
<!-- Sidebar Navigation (SideNavBar) -->
<aside class="w-full md:w-80 flex flex-col gap-2">
<div class="bg-surface-container-low rounded-xl p-8 mb-4 shadow-[20px_0px_40px_rgba(25,28,30,0.04)]">
<div class="flex items-center gap-4 mb-6">
<img alt="Admin User Profile" class="w-16 h-16 rounded-full object-cover" data-alt="professional headshot of a smiling man in a white lab coat with a clean clinical background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDZVSadJXcBcshmzcGRZKYxWwxAABI_EoQp5qsFHmgK4xKWCljGVkvndqGVhs9nbJo_G68vvIBzPFDByrijsj8U_yZ55S1phO0-S61507u26F2YuROsnyrNv6rEqjpiKoHl0dazscuzinuc869GeLGsJcxVv0dcCSX_xroAi5hTsVRSvpkISS5qnFJ3YZJD_77sxoujrdTFJiHviFVuk_Iqe9Y7AT_gnG7yGAaCIu_pipcNoNYBDDC_3v_hAllDUt-_lLc3BTrj8Sk"/>
<div>
<h2 class="font-headline font-bold text-lg text-on-surface">Nguyễn Văn A</h2>
<p class="font-label text-sm text-on-surface-variant">Precision Management</p>
</div>
</div>
<nav class="flex flex-col gap-1">
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">person</span>
                        Thông tin cá nhân
                    </a>
<a class="bg-white text-[#003f87] rounded-r-full py-3 px-6 shadow-sm flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">inventory_2</span>
                        Đơn hàng của tôi
                    </a>
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">location_on</span>
                        Sổ địa chỉ
                    </a>
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">confirmation_number</span>
                        Mã giảm giá
                    </a>
</nav>
<div class="mt-8 pt-6 border-t border-outline-variant/15">
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">logout</span>
                        Đăng xuất
                    </a>
</div>
</div>
</aside>
<!-- Main Content Area: My Orders View -->
<section class="flex-1 flex flex-col gap-8">
<!-- Header & Search -->
<div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
<div>
<h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">Đơn hàng của tôi</h1>
<p class="text-on-surface-variant body-lg max-w-xl">Xem lịch sử mua hàng, trạng thái vận chuyển và quản lý các đơn đặt hàng thiết bị y tế của bạn.</p>
</div>
</div>
<!-- Filter & Search Bar -->
<div class="bg-surface-container-low p-4 rounded-xl flex flex-col md:flex-row gap-4 items-center">
<div class="relative flex-1 w-full">
<span class="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant">search</span>
<input class="w-full pl-12 pr-4 py-3 bg-white border-none rounded-lg focus:ring-2 focus:ring-primary/20 text-sm" placeholder="Tìm kiếm theo mã đơn hàng hoặc tên sản phẩm..." type="text"/>
</div>
<div class="flex gap-2 w-full md:w-auto">
<select class="bg-white border-none rounded-lg px-4 py-3 text-sm font-medium focus:ring-2 focus:ring-primary/20 cursor-pointer">
<option>Tất cả trạng thái</option>
<option>Đang giao</option>
<option>Đã giao</option>
<option>Đã hủy</option>
</select>
<select class="bg-white border-none rounded-lg px-4 py-3 text-sm font-medium focus:ring-2 focus:ring-primary/20 cursor-pointer">
<option>30 ngày qua</option>
<option>6 tháng qua</option>
<option>Năm 2023</option>
</select>
</div>
</div>
<!-- Orders List -->
<div class="space-y-6">
<!-- Order Card 1: Completed -->
<div class="bg-surface-container-lowest rounded-xl border border-outline-variant/10 overflow-hidden shadow-sm">
<div class="bg-surface-container-low/50 px-6 py-4 flex flex-wrap justify-between items-center gap-4 border-b border-outline-variant/5">
<div class="flex gap-6">
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Mã đơn hàng</p>
<p class="font-headline font-bold text-sm text-primary">CC-90821</p>
</div>
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Ngày đặt</p>
<p class="font-headline font-bold text-sm text-on-surface">01/05/2024</p>
</div>
</div>
<div class="flex items-center gap-3">
<span class="bg-tertiary-fixed text-on-tertiary-fixed-variant px-3 py-1 rounded-full text-xs font-bold flex items-center gap-1">
<span class="material-symbols-outlined text-xs">check_circle</span>
                                Đã giao
                            </span>
</div>
</div>
<div class="p-6">
<div class="flex flex-col gap-4">
<!-- Product Item -->
<div class="flex items-center gap-4">
<div class="w-16 h-16 bg-surface-container rounded-lg flex items-center justify-center p-2 flex-shrink-0">
<img alt="Stethoscope" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDYw7zX6rpaVppOqR9C-PkCwYN9kU1UZrdCpmHKRtnBNo3OI5iFic7VS80zooJg-fCffLEq1vWbESthZiC770zHm0fmPXzjzhwTkHkBkpjmUqiXXANn0GY9xQIQORrD5fgP5lsw2ulTQPj83CDgjuSF2PGdDzNxXQ3FeKkvWsoOiqYIsVVD-8aPZ-yCH0Q7RM90_IZcSgb-H4_6afu87aM7QfhQwgxIxQnF_WoqHTWV_J_TjbrATKrNCHPSoZR20W-mJz7i4cBFt7w"/>
</div>
<div class="flex-1">
<h4 class="font-headline font-bold text-on-surface">Ống nghe Precision Series X5</h4>
<p class="text-on-surface-variant text-sm">Số lượng: 01</p>
</div>
<div class="text-right">
<p class="font-headline font-bold text-on-surface">4.200.000đ</p>
</div>
</div>
</div>
<div class="mt-6 pt-6 border-t border-outline-variant/10 flex flex-col md:flex-row justify-between items-center gap-6">
<div class="flex items-center gap-2">
<p class="text-on-surface-variant text-sm">Tổng cộng:</p>
<p class="font-headline font-black text-2xl text-primary">4.200.000đ</p>
</div>
<div class="flex gap-3 w-full md:w-auto">
<button class="flex-1 md:flex-none border-2 border-primary text-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary/5 transition-colors">Xem chi tiết</button>
<button class="flex-1 md:flex-none border-2 border-primary text-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary/5 transition-colors">Đánh giá</button><button class="flex-1 md:flex-none bg-primary text-on-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary-container transition-all">Mua lại</button>
</div>
</div>
</div>
</div>
<!-- Order Card 2: Shipping -->
<div class="bg-surface-container-lowest rounded-xl border border-outline-variant/10 overflow-hidden shadow-sm">
<div class="bg-surface-container-low/50 px-6 py-4 flex flex-wrap justify-between items-center gap-4 border-b border-outline-variant/5">
<div class="flex gap-6">
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Mã đơn hàng</p>
<p class="font-headline font-bold text-sm text-primary">CC-91254</p>
</div>
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Ngày đặt</p>
<p class="font-headline font-bold text-sm text-on-surface">03/05/2024</p>
</div>
</div>
<div class="flex items-center gap-3">
<span class="bg-secondary-container text-on-secondary-container px-3 py-1 rounded-full text-xs font-bold flex items-center gap-1">
<span class="material-symbols-outlined text-xs">local_shipping</span>
                                Đang giao
                            </span>
</div>
</div>
<div class="p-6">
<div class="flex flex-col gap-4">
<div class="flex items-center gap-4">
<div class="w-16 h-16 bg-surface-container rounded-lg flex items-center justify-center p-2 flex-shrink-0">
<img alt="Monitor" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDKXn8uZHaQO_biVMM4qtmL9YfFV3rOqpYGf_9NRFt7dyu7dgsGPOmgVtEUtL2ioc3HeWOmYGjOWcKGF2XZQ1iq1l4FKOy-fk5jc4SJFzA-ZS4icDoUQQ-zGYvDSNGkYAgk9YPsgsa8CVWUo6FWu8R6vJj8eKxse9vjnUqQMuntSkhnIKYj_oFOJloXMLfnx6UHTDZT2tYMPEOB_JFuMTf5eVvS3ECCD9IQIgvcwi0Sq1ac2HHUbtrZh4Jwfw82up-ttdig5oJdh5Y"/>
</div>
<div class="flex-1">
<h4 class="font-headline font-bold text-on-surface">Máy theo dõi nhịp tim Pro-Touch</h4>
<p class="text-on-surface-variant text-sm">Số lượng: 01</p>
</div>
<div class="text-right">
<p class="font-headline font-bold text-on-surface">12.850.000đ</p>
</div>
</div>
</div>
<div class="mt-6 pt-6 border-t border-outline-variant/10 flex flex-col md:flex-row justify-between items-center gap-6">
<div class="flex items-center gap-2">
<p class="text-on-surface-variant text-sm">Tổng cộng:</p>
<p class="font-headline font-black text-2xl text-primary">12.850.000đ</p>
</div>
<div class="flex gap-3 w-full md:w-auto">
<button class="flex-1 md:flex-none border-2 border-primary text-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary/5 transition-colors">Theo dõi hành trình</button>
<button class="flex-1 md:flex-none border-2 border-primary text-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary/5 transition-colors">Xem chi tiết</button>
</div>
</div>
</div>
</div>
<!-- Order Card 3: Cancelled -->
<div class="bg-surface-container-lowest rounded-xl border border-outline-variant/10 overflow-hidden shadow-sm opacity-80">
<div class="bg-surface-container-low/50 px-6 py-4 flex flex-wrap justify-between items-center gap-4 border-b border-outline-variant/5">
<div class="flex gap-6">
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Mã đơn hàng</p>
<p class="font-headline font-bold text-sm text-primary">CC-89772</p>
</div>
<div>
<p class="text-[10px] uppercase tracking-widest text-on-surface-variant font-bold mb-1">Ngày đặt</p>
<p class="font-headline font-bold text-sm text-on-surface">15/04/2024</p>
</div>
</div>
<div class="flex items-center gap-3">
<span class="bg-error-container text-on-error-container px-3 py-1 rounded-full text-xs font-bold flex items-center gap-1">
<span class="material-symbols-outlined text-xs">cancel</span>
                                Đã hủy
                            </span>
</div>
</div>
<div class="p-6">
<div class="flex flex-col gap-4">
<div class="flex items-center gap-4 grayscale opacity-60">
<div class="w-16 h-16 bg-surface-container rounded-lg flex items-center justify-center p-2 flex-shrink-0">
<img alt="Consumables" class="w-full h-full object-contain" src="https://lh3.googleusercontent.com/aida-public/AB6AXuA0oiFSOTwOVxt0_cKfUMoKnzSdBMATcrGVyguZUdaFhaZohLJg_tG69Bv6lXM5ftDDYNdSfxqO-fRB0T8nWta-zcWNmKqxFon4KXW5OkBp2GE7gBN6Idvatcy5K8WnftusBZKZ-wh__HpznEVXtqUcHzHcLGlgimWjMOK2Shjqemrt3ZWWSOayPhzNgP_Ru9H7nhQWcDf3wyyg75oZ1Xa39eJtTuAINaSwGZabjhg2OLkEGd3Dh__pBGdxzJh9ZWRwRskSeuf5ypI"/>
</div>
<div class="flex-1">
<h4 class="font-headline font-bold text-on-surface text-decoration-line-through">Bộ vật tư phẫu thuật dùng một lần</h4>
<p class="text-on-surface-variant text-sm">Số lượng: 10</p>
</div>
<div class="text-right">
<p class="font-headline font-bold text-on-surface-variant">1.950.000đ</p>
</div>
</div>
</div>
<div class="mt-6 pt-6 border-t border-outline-variant/10 flex flex-col md:flex-row justify-between items-center gap-6">
<div class="flex items-center gap-2">
<p class="text-on-surface-variant text-sm">Tổng cộng:</p>
<p class="font-headline font-black text-2xl text-on-surface-variant">1.950.000đ</p>
</div>
<div class="flex gap-3 w-full md:w-auto">
<button class="flex-1 md:flex-none border-2 border-primary text-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary/5 transition-colors">Xem chi tiết</button>
<button class="flex-1 md:flex-none bg-primary text-on-primary px-6 py-2.5 rounded-lg font-bold text-sm hover:bg-primary-container transition-all">Mua lại</button>
</div>
</div>
</div>
</div>
</div>
</section>
</main>

</template>