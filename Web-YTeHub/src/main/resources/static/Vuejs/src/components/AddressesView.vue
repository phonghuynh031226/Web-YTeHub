<template>

<main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
<!-- Sidebar Navigation -->
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
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">inventory_2</span>
                        Đơn hàng của tôi
                    </a>
<a class="bg-white text-[#003f87] rounded-r-full py-3 px-6 shadow-sm flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">location_on</span>
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
<!-- Main Content Area: Address Book -->
<section class="flex-1 flex flex-col gap-12">
<!-- Header section -->
<div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
<div>
<h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">Sổ địa chỉ</h1>
<p class="text-on-surface-variant body-lg max-w-xl">Quản lý danh sách địa chỉ nhận hàng của bạn</p>
</div>
<button class="bg-primary text-on-primary px-8 py-4 rounded-md font-bold flex items-center gap-2 hover:bg-primary-container transition-all active:scale-95 shadow-lg shadow-primary/10">
<span class="material-symbols-outlined text-xl">add</span>
                    Thêm địa chỉ mới
                </button>
</div>
<!-- Address Cards Grid -->
<div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
<!-- Address Card 1: Primary -->
<div class="bg-surface-container-low border-2 border-primary-container/20 rounded-xl p-8 relative shadow-sm">
<div class="flex justify-between items-start mb-4">
<div class="flex items-center gap-3">
<h3 class="font-headline font-bold text-xl text-on-surface">Nhà riêng</h3>
<span class="bg-primary-fixed text-on-primary-fixed-variant px-3 py-1 rounded-full text-[10px] font-black uppercase tracking-wider flex items-center gap-1">
<span class="material-symbols-outlined text-xs" style="font-variation-settings: 'FILL' 1;">check_circle</span>
                                Mặc định
                            </span>
</div>
<div class="flex gap-2">
<button class="text-on-surface-variant hover:text-primary transition-colors p-1" title="Chỉnh sửa">
<span class="material-symbols-outlined">edit</span>
</button>
<button class="text-on-surface-variant hover:text-error transition-colors p-1" title="Xóa">
<span class="material-symbols-outlined">delete</span>
</button>
</div>
</div>
<div class="space-y-4">
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Người nhận</label>
<p class="font-headline font-semibold text-on-surface">Nguyễn Văn A</p>
</div>
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Số điện thoại</label>
<p class="font-headline font-semibold text-on-surface">+84 901 234 567</p>
</div>
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Địa chỉ</label>
<p class="font-headline font-semibold text-on-surface leading-relaxed">123 Đường Precision, Phường Bến Nghé, Quận 1, TP. Hồ Chí Minh</p>
</div>
</div>
</div>
<!-- Address Card 2 -->
<div class="bg-surface-container-lowest border border-outline-variant/10 rounded-xl p-8 relative shadow-sm">
<div class="flex justify-between items-start mb-4">
<div class="flex items-center gap-3">
<h3 class="font-headline font-bold text-xl text-on-surface">Văn phòng</h3>
</div>
<div class="flex gap-2">
<button class="text-on-surface-variant hover:text-primary transition-colors p-1" title="Chỉnh sửa">
<span class="material-symbols-outlined">edit</span>
</button>
<button class="text-on-surface-variant hover:text-error transition-colors p-1" title="Xóa">
<span class="material-symbols-outlined">delete</span>
</button>
</div>
</div>
<div class="space-y-4">
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Người nhận</label>
<p class="font-headline font-semibold text-on-surface">Bác sĩ Nguyễn Văn A</p>
</div>
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Số điện thoại</label>
<p class="font-headline font-semibold text-on-surface">+84 28 3824 1234</p>
</div>
<div>
<label class="font-label text-xs uppercase tracking-widest text-on-surface-variant block mb-1">Địa chỉ</label>
<p class="font-headline font-semibold text-on-surface leading-relaxed">Bệnh viện Đa khoa Trung tâm, 456 Đường Clinical, Quận Cam, TP. Hồ Chí Minh</p>
</div>
</div>
<div class="mt-6 pt-6 border-t border-outline-variant/5">
<button class="text-primary text-sm font-bold hover:underline">Đặt làm mặc định</button>
</div>
</div>
</div>
</section>
</main>

</template>