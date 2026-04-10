<template>

<main class="pt-28 pb-16 px-4 md:px-8 max-w-screen-2xl mx-auto flex flex-col md:flex-row gap-8">
<!-- Sidebar Navigation -->
<aside class="w-full md:w-80 flex flex-col gap-2">
<div class="bg-surface-container-low rounded-xl p-8 mb-4 shadow-[20px_0px_40px_rgba(25,28,30,0.04)] max-w-4xl">
<div class="flex items-center gap-4 mb-6">
<img alt="Admin User Profile" class="w-16 h-16 rounded-full object-cover" data-alt="professional headshot of a smiling man in a white lab coat with a clean clinical background" src="https://lh3.googleusercontent.com/aida-public/AB6AXuDZVSadJXcBcshmzcGRZKYxWwxAABI_EoQp5qsFHmgK4xKWCljGVkvndqGVhs9nbJo_G68vvIBzPFDByrijsj8U_yZ55S1phO0-S61507u26F2YuROsnyrNv6rEqjpiKoHl0dazscuzinuc869GeLGsJcxVv0dcCSX_xroAi5hTsVRSvpkISS5qnFJ3YZJD_77sxoujrdTFJiHviFVuk_Iqe9Y7AT_gnG7yGAaCIu_pipcNoNYBDDC_3v_hAllDUt-_lLc3BTrj8Sk"/>
<div>
<h2 class="font-headline font-bold text-lg text-on-surface">Nguyễn Văn A</h2>
<p class="font-label text-sm text-on-surface-variant">Precision Management</p>
</div>
</div>
<nav class="flex flex-col gap-1">
<a class="bg-white text-[#003f87] rounded-r-full py-3 px-6 shadow-sm flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1;">person</span>
                        Thông tin cá nhân
                    </a>
<a class="text-on-surface-variant py-3 px-6 hover:translate-x-1 transition-transform flex items-center gap-3 font-semibold text-sm tracking-wide" href="#">
<span class="material-symbols-outlined">inventory_2</span>
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
<!-- Main Content Area -->
<section class="flex-1 flex flex-col gap-10">
<!-- Profile Overview Header -->
<div class="flex flex-col md:flex-row md:items-end justify-between gap-6">
<div>
<h1 class="font-headline text-4xl font-extrabold tracking-tight text-on-surface mb-2">Thông tin cá nhân</h1>
<p class="text-on-surface-variant body-lg max-w-xl">Quản lý và cập nhật thông tin hồ sơ của bạn</p>
</div>
</div>
<div class="gap-8">
<!-- Profile Form -->
<div class="space-y-8">
<div class="bg-surface-container-low rounded-xl p-8 max-w-4xl">
<form class="grid grid-cols-1 md:grid-cols-2 gap-6">
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="fullname">Họ và Tên</label>
<input class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="fullname" type="text" value="Nguyễn Văn A"/>
</div>
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="email">Email</label>
<input class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="email" type="email" value="nguyenvana@clinical.com"/>
</div>
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="phone">Số điện thoại</label>
<input class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="phone" type="tel" value="+84 901 234 567"/>
</div>
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="dob">Ngày sinh</label>
<input class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="dob" type="date" value="1990-01-01"/>
</div>
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="gender">Giới tính</label>
<select class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="gender">
<option selected="">Nam</option>
<option>Nữ</option>
<option>Khác</option>
</select>
</div>
<div class="flex flex-col gap-2">
<label class="text-sm font-bold text-on-surface-variant" for="nationality">Quốc tịch</label>
<input class="bg-surface-container-lowest border border-outline-variant rounded-lg px-4 py-3 text-on-surface focus:border-primary focus:ring-1 focus:ring-primary outline-none transition-all" id="nationality" type="text" value="Việt Nam"/>
</div>
<div class="md:col-span-2 pt-4">
<button class="bg-primary text-on-primary px-10 py-4 rounded-md font-bold hover:bg-primary-container transition-all shadow-lg shadow-primary/10" type="submit">
                                    Lưu thay đổi
                                </button>
</div>
</form>
</div>
<!-- Shipping Address Section -->
</div></div></section></main>
</template>