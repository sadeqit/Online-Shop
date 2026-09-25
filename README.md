# 🛍️ Online Shop

> یک اپلیکیشن فروشگاه آنلاین اندروید با رابط کاربری مدرن، معماری لایه‌ای، Jetpack Compose و Backend مبتنی بر Supabase

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.10-7F52FF?logo=kotlin\&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2026.08-4285F4?logo=jetpackcompose\&logoColor=white)](https://developer.android.com/compose)
[![Supabase](https://img.shields.io/badge/Supabase-Backend-3ECF8E?logo=supabase\&logoColor=white)](https://supabase.com/)
[![Hilt](https://img.shields.io/badge/Hilt-DI-34A853)](https://developer.android.com/training/dependency-injection/hilt-android)
[![Material 3](https://img.shields.io/badge/Material%203-UI-6750A4)](https://m3.material.io/)

---

## 📱 معرفی پروژه

**Online Shop** یک اپلیکیشن فروشگاه آنلاین برای سیستم‌عامل Android است که با زبان **Kotlin** و رابط کاربری **Jetpack Compose** توسعه داده شده است.

این پروژه با هدف پیاده‌سازی یک ساختار قابل توسعه و تفکیک‌شده طراحی شده و علاوه بر رابط کاربری، شامل Backend واقعی مبتنی بر **Supabase**، احراز هویت کاربران، پایگاه داده PostgreSQL، ذخیره‌سازی تصاویر، مدیریت سفارش‌ها، سبد خرید، علاقه‌مندی‌ها، نظرات و اعلان‌ها است.

ساختار پروژه بر پایه جداسازی مسئولیت‌ها طراحی شده و لایه‌های UI، Domain و Data از یکدیگر تفکیک شده‌اند.

---

# ✨ قابلیت‌های پروژه

## 🔐 احراز هویت کاربران

* ثبت‌نام و ورود کاربران
* مدیریت حساب کاربری
* تأیید اطلاعات کاربر
* تغییر رمز عبور
* مدیریت Session
* اتصال مستقیم به Supabase Authentication

---

## 🏠 صفحه اصلی

* نمایش محتوای اصلی فروشگاه
* Banner
* دسته‌بندی محصولات
* محصولات پرفروش
* جست‌وجوی محصولات
* نمایش نتایج جست‌وجو

---

## 🗂️ دسته‌بندی محصولات

* نمایش دسته‌بندی‌ها
* نمایش زیردسته‌ها
* مشاهده محصولات هر دسته
* انتخاب دسته و زیردسته
* Navigation بین صفحات مرتبط

---

## 🛍️ محصولات

* نمایش لیست محصولات
* نمایش جزئیات محصول
* نمایش تصاویر محصول
* نمایش توضیحات
* نمایش ویژگی‌های محصول
* افزودن محصول به سبد خرید
* افزودن محصول به علاقه‌مندی‌ها
* مشاهده نظرات کاربران

---

## 🛒 سبد خرید

* مشاهده محصولات سبد خرید
* افزایش و کاهش تعداد محصولات
* حذف محصول
* محاسبه مبلغ سفارش
* انتخاب آدرس
* بررسی اطلاعات سفارش
* تکمیل فرآیند ثبت سفارش

---

## ❤️ علاقه‌مندی‌ها

* افزودن محصول به علاقه‌مندی‌ها
* حذف محصول از علاقه‌مندی‌ها
* نمایش محصولات موردعلاقه
* نگهداری اطلاعات علاقه‌مندی‌ها در Local Storage

---

## 📦 سفارش‌ها

* ثبت سفارش
* مشاهده سفارش‌های ثبت‌شده
* مشاهده خریدهای کاربر
* مشاهده آیتم‌های سفارش
* نمایش اطلاعات سفارش
* ارتباط سفارش‌ها با اطلاعات کاربر و محصولات

---

## ⭐ نظرات و تجربه خرید

* مشاهده نظرات کاربران
* ثبت نظر درباره محصول
* نمایش تجربه‌های خرید
* اتصال نظرات به محصول و کاربر
* مدیریت اطلاعات Review در Backend

---

## 🔔 اعلان‌ها

* دریافت اعلان‌های کاربر
* نمایش لیست اعلان‌ها
* نمایش وضعیت خوانده‌شدن
* علامت‌گذاری اعلان به‌عنوان خوانده‌شده
* دریافت اطلاعات اعلان از Backend

---

## 👤 پروفایل

* مشاهده پروفایل
* ویرایش اطلاعات کاربر
* تغییر رمز عبور
* مدیریت اطلاعات شخصی
* مدیریت آدرس‌ها

---

## 📍 مدیریت آدرس

* مشاهده آدرس‌های کاربر
* افزودن آدرس
* ویرایش آدرس
* انتخاب آدرس هنگام ثبت سفارش

---

# 🏗️ معماری پروژه

ساختار پروژه بر پایه ترکیبی از **MVVM، Repository Pattern و جداسازی لایه‌های Data / Domain / UI** طراحی شده است.

جریان کلی داده در برنامه:

```text
┌──────────────────────────────┐
│       Jetpack Compose UI     │
│        Feature Screens       │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│          ViewModel           │
│    State / Business Logic    │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│      Domain Repository       │
│        Interfaces            │
└──────────────┬───────────────┘
               │
               ▼
┌──────────────────────────────┐
│       Data Repository        │
│       Remote / Local         │
└──────────────┬───────────────┘
               │
          ┌────┴─────┐
          ▼          ▼
     ┌────────┐ ┌──────────┐
     │Supabase│ │ DataStore│
     └────────┘ └──────────┘
```

### اهداف معماری

* جداسازی مسئولیت‌ها
* کاهش وابستگی بین بخش‌ها
* قابلیت تست بهتر
* قابلیت توسعه و نگهداری آسان‌تر
* جلوگیری از وابستگی مستقیم UI به Data Source
* مدیریت متمرکز وابستگی‌ها
* تفکیک مدل‌های Domain و DTOهای Remote

---

# 📂 ساختار پروژه

ساختار اصلی Packageهای پروژه:

```text
io.github.sadeghi.online_shop
│
├── app
├── core
├── data
├── di
├── domain
├── feature
└── navigation
```

---

## `app`

شامل تنظیمات سطح Application است.

```text
app/
└── OnlineShopApplication.kt
```

Application اصلی پروژه در این بخش قرار دارد.

---

## `core`

کدهای عمومی و قابل استفاده مجدد در کل پروژه در این بخش قرار گرفته‌اند.

```text
core/
├── common/
├── theme/
├── ui/
└── utils/
```

### `core/common`

ابزارهای عمومی مانند بررسی وضعیت شبکه.

### `core/theme`

تعریف Theme و طراحی ظاهری:

* Color
* Theme
* Typography

### `core/ui`

کامپوننت‌های قابل استفاده مجدد UI:

* TextField
* Button
* Card
* Shape
* Spacer
* سایر UI Components

### `core/utils`

توابع عمومی مانند:

* محاسبه قدرت رمز عبور
* فرمت تاریخ
* فرمت تاریخ شمسی
* فرمت تاریخ اعلان‌ها

---

# 🗃️ Data Layer

لایه Data مسئول ارتباط با منابع داده است.

```text
data/
├── constants/
├── local/
├── remote/
└── repository/
```

---

## Remote Data Source

ارتباط با Backend در این بخش مدیریت می‌شود.

```text
data/remote/
├── SupabaseClient.kt
└── dto/
```

DTOهای پروژه شامل مدل‌هایی برای:

* Product
* Profile
* Address
* Cart
* Order
* Order Item
* Notification
* Product Review
* Category
* SubCategory
* User Role

و سایر داده‌های مورد نیاز Backend هستند.

---

# 💾 Local Data

برای نگهداری برخی اطلاعات محلی از **Jetpack DataStore** استفاده شده است.

```text
data/local/datastore/
├── FavoritesDataStore.kt
└── UserPreferences.kt
```

این بخش برای نگهداری اطلاعات محلی و تنظیمات مورد نیاز برنامه استفاده می‌شود.

---

# 🧩 Repository Layer

Repositoryها واسط بین لایه Data و سایر بخش‌های برنامه هستند.

```text
data/repository/
├── AddressRepository.kt
├── AuthRepository.kt
├── CartRepository.kt
├── FavoritesRepository.kt
├── NotificationsRepositoryImpl.kt
├── OrderRepository.kt
├── ProductRepository.kt
├── ProductReviewRepository.kt
├── ProfileRepository.kt
└── SubCategoryRepository.kt
```

Repository Pattern باعث می‌شود ViewModelها مستقیماً به منبع داده وابسته نباشند.

---

# 🧠 Domain Layer

لایه Domain شامل مدل‌های اصلی برنامه و قرارداد Repositoryها است.

```text
domain/
├── model/
└── repository/
```

### Domain Models

مدل‌هایی مانند:

* Product
* Order
* CartItems
* Address
* Notification
* ProductReview
* SubCategory

در این لایه قرار گرفته‌اند.

### Repository Interfaces

قراردادهای Repository نیز در Domain تعریف شده‌اند.

به این ترتیب لایه Domain به پیاده‌سازی مستقیم Backend وابسته نیست.

---

# 🎨 Feature Layer

هر قابلیت اصلی برنامه در یک Feature مستقل قرار گرفته است.

```text
feature/
├── address/
├── auth/
├── cart/
├── category/
├── favorites/
├── home/
├── main/
├── notifications/
├── orders/
├── product/
├── profile/
└── splash/
```

این ساختار باعث می‌شود کدهای مربوط به هر قابلیت در یک محدوده مشخص قرار داشته باشند.

---

## Authentication

```text
feature/auth/
├── LoginScreen.kt
├── LoginViewModel.kt
├── component/
└── model/
```

فرآیندهای مختلف ورود و احراز هویت در این Feature مدیریت می‌شوند.

---

## Home

```text
feature/home/
├── HomeScreen.kt
├── SearchResultScreen.kt
└── component/
```

---

## Product

```text
feature/product/
├── ProductDetailScreen.kt
├── ProductViewModel.kt
├── component/
└── review/
```

---

## Cart

```text
feature/cart/
├── CartScreen.kt
├── CartViewModel.kt
├── component/
└── model/
```

---

## Orders

```text
feature/orders/
├── MyBuyScreen.kt
├── MyOrdersScreen.kt
├── MyPurchaseItem.kt
├── OrderViewModel.kt
└── component/
```

---

## Profile

```text
feature/profile/
├── ProfileScreen.kt
├── ProfileViewModel.kt
├── EditProfile.kt
├── ChangePasswordViewModel.kt
└── component/
```

---

# 🧭 Navigation

Navigation در Package اختصاصی مدیریت می‌شود:

```text
navigation/
├── MainNavGraph.kt
├── Navgraph.kt
└── Screens.kt
```

Navigation بین Featureهای مختلف برنامه از طریق Navigation Compose مدیریت می‌شود.

---

# 💉 Dependency Injection

برای Dependency Injection از **Dagger Hilt** استفاده شده است.

ماژول‌های اصلی:

```text
di/
├── DataStoreModule.kt
├── RepositoryModule.kt
└── SupabaseModule.kt
```

Hilt برای مدیریت وابستگی‌هایی مانند:

* Supabase Client
* DataStore
* Repositoryها

استفاده می‌شود.

این روش باعث کاهش Coupling و ساده‌تر شدن مدیریت وابستگی‌های پروژه می‌شود.

---

# ☁️ Backend — Supabase

Backend پروژه با **Supabase** پیاده‌سازی شده است.

Supabase بخش‌های مختلف Backend را فراهم می‌کند:

```text
                    Android Application
                            │
                            ▼
                    Supabase Client
                            │
            ┌───────────────┼───────────────┐
            │               │               │
            ▼               ▼               ▼
        Auth            PostgreSQL       Storage
            │               │               │
            │               ▼               │
            │          Database Data        │
            │                               │
            └──────────────┬────────────────┘
                           ▼
                       Realtime
```

---

# 🔐 Supabase Authentication

احراز هویت کاربران از طریق Supabase Auth انجام می‌شود.

بخش Android از Supabase Auth SDK برای مدیریت عملیات مرتبط با حساب کاربری استفاده می‌کند.

---

# 🗄️ PostgreSQL

اطلاعات اصلی فروشگاه در PostgreSQL نگهداری می‌شوند.

داده‌های مرتبط با بخش‌هایی مانند:

* کاربران
* محصولات
* دسته‌بندی‌ها
* سبد خرید
* سفارش‌ها
* آیتم‌های سفارش
* نظرات
* اعلان‌ها
* آدرس‌ها
* نقش کاربران

در Backend مدیریت می‌شوند.

ساختار دقیق جداول و روابط آن‌ها در مستندات Database پروژه قابل ارائه است.

---

# 🖼️ Supabase Storage

برای نگهداری فایل‌ها و تصاویر از Supabase Storage استفاده می‌شود.

این بخش برای مواردی مانند تصاویر مرتبط با محصولات و اطلاعات تصویری کاربران کاربرد دارد.

---

# ⚡ Supabase Realtime

پروژه از قابلیت Realtime Supabase برای دریافت داده‌های بلادرنگ در بخش‌هایی که نیاز به به‌روزرسانی لحظه‌ای دارند استفاده می‌کند.

---

# 🔄 جریان داده

جریان کلی درخواست‌ها در برنامه:

```text
User
 │
 ▼
Compose Screen
 │
 ▼
ViewModel
 │
 ▼
Repository Interface
 │
 ▼
Repository Implementation
 │
 ▼
Supabase / DataStore
 │
 ▼
Result
 │
 ▼
StateFlow
 │
 ▼
Compose UI
```

این ساختار باعث می‌شود UI از جزئیات مربوط به Backend و نحوه ذخیره‌سازی داده مستقل باشد.

---

# 📡 Network Layer

برای ارتباطات شبکه‌ای و سرویس‌های مورد نیاز پروژه از ابزارهای Kotlin و کتابخانه‌های مرتبط با HTTP استفاده شده است.

کتابخانه‌های موجود در پروژه شامل:

* Ktor Client
* OkHttp
* Retrofit
* Kotlin Serialization
* Gson

هستند.

در قسمت‌هایی که از Supabase Kotlin SDK استفاده می‌شود، ارتباط با سرویس‌های Supabase از طریق Client مربوط به Supabase انجام می‌شود.

---

# 🧵 Coroutines & StateFlow

برای عملیات asynchronous از Kotlin Coroutines استفاده شده است.

و برای مدیریت وضعیت Reactive در ViewModelها از `StateFlow` استفاده می‌شود.

الگوی کلی:

```text
Repository
    │
    ▼
Coroutine
    │
    ▼
ViewModel
    │
    ▼
StateFlow
    │
    ▼
Compose
```

---

# 🖼️ مدیریت تصاویر

برای بارگذاری تصاویر در رابط کاربری از **Coil** استفاده شده است.

همچنین برای برش تصاویر از **uCrop** استفاده می‌شود.

---

# 🎨 رابط کاربری

UI پروژه با **Jetpack Compose** پیاده‌سازی شده است.

ویژگی‌های اصلی:

* Declarative UI
* Material 3
* Reusable Components
* Custom Components
* Custom Theme
* Animation
* Responsive Layout
* مدیریت State در Compose

کامپوننت‌های عمومی پروژه در:

```text
core/ui/
```

قرار گرفته‌اند.

---

# 🧰 تکنولوژی‌ها و کتابخانه‌ها

## Android

| تکنولوژی           | کاربرد               |
| ------------------ | -------------------- |
| Kotlin             | زبان برنامه‌نویسی    |
| Jetpack Compose    | رابط کاربری          |
| Material 3         | طراحی رابط کاربری    |
| AndroidX           | کتابخانه‌های پایه    |
| Navigation Compose | Navigation           |
| Lifecycle          | مدیریت Lifecycle     |
| ViewModel          | مدیریت State و Logic |

## Architecture

| تکنولوژی           | کاربرد                |
| ------------------ | --------------------- |
| MVVM               | معماری UI             |
| Repository Pattern | جداسازی Data Source   |
| Hilt               | Dependency Injection  |
| Coroutines         | عملیات asynchronous   |
| StateFlow          | مدیریت Reactive State |

## Backend

| تکنولوژی          | کاربرد           |
| ----------------- | ---------------- |
| Supabase Auth     | احراز هویت       |
| PostgreSQL        | پایگاه داده      |
| Supabase Storage  | ذخیره فایل       |
| Supabase Realtime | داده‌های بلادرنگ |

## Network & Serialization

| تکنولوژی             | کاربرد             |
| -------------------- | ------------------ |
| Ktor                 | HTTP Client        |
| OkHttp               | Network            |
| Retrofit             | REST API           |
| Kotlin Serialization | Serialization      |
| Gson                 | JSON Serialization |

## UI & Media

| کتابخانه         | کاربرد         |
| ---------------- | -------------- |
| Coil             | Image Loading  |
| uCrop            | Image Cropping |
| ConstraintLayout | Layout         |
| Material Icons   | آیکون‌ها       |

## Local Storage

| کتابخانه              | کاربرد            |
| --------------------- | ----------------- |
| DataStore Preferences | Local Preferences |

---

# 📦 مدیریت Dependencyها

Dependencyهای پروژه از طریق **Gradle Version Catalog** مدیریت می‌شوند.

فایل:

```text
gradle/libs.versions.toml
```

مرجع مرکزی نسخه‌های کتابخانه‌ها و Pluginهای پروژه است.

این روش باعث می‌شود مدیریت نسخه‌ها متمرکز و قابل نگهداری باشد.

---

# 🔒 امنیت

در طراحی پروژه تلاش شده اطلاعات حساس مستقیماً در کد عمومی Repository قرار نگیرند.

موارد امنیتی پروژه شامل:

* استفاده از Supabase Authentication
* جداسازی Configurationهای حساس
* عدم قرار دادن Secretهای Backend در Git
* استفاده از Release Build
* فعال بودن R8 / ProGuard در Release
* استفاده از Signed APK برای نسخه Release

> **نکته:** کلیدهای حساس Supabase مانند Service Role Key نباید در Repository عمومی GitHub قرار گیرند.

---

# 🛡️ R8 / ProGuard

برای نسخه Release قابلیت Minification فعال شده است.

```kotlin
buildTypes {
    release {
        isMinifyEnabled = true
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}
```

نسخه Release با R8/ProGuard ساخته و روی دستگاه واقعی آزمایش شده است.

---

# 🧪 تست و بررسی Release

نسخه Release پروژه پس از فعال‌سازی Minification ساخته شده و APK امضاشده روی دستگاه واقعی بررسی شده است.

موارد اصلی برنامه در Release بررسی شده‌اند، از جمله:

* Authentication
* ارتباط با Supabase
* محصولات
* تصاویر
* سبد خرید
* ثبت سفارش
* سفارش‌های کاربر
* Profile
* Notifications
* Reviews
* DataStore

---

# 🚀 اجرای پروژه

برای اجرای پروژه:

### 1. Clone کردن Repository

```bash
git clone <REPOSITORY_URL>
```

### 2. باز کردن پروژه

پروژه را در Android Studio باز کنید.

### 3. Sync کردن Gradle

اجازه دهید Gradle Dependencyهای پروژه را دریافت و Sync کند.

### 4. تنظیم Backend

اطلاعات مورد نیاز Supabase را طبق Configuration پروژه تنظیم کنید.

> اطلاعات حساس مانند Secret Key یا Service Role Key نباید داخل GitHub Commit شوند.

### 5. اجرای پروژه

برنامه را روی Emulator یا Android Device اجرا کنید.

---

# 🏗️ Build

برای ساخت نسخه Debug:

```bash
./gradlew assembleDebug
```

برای ساخت نسخه Release:

```bash
./gradlew assembleRelease
```

نسخه Release با R8/ProGuard ساخته می‌شود.

---

# 📱 Release

نسخه Release پروژه با:

* R8 / ProGuard
* Minification
* Signed APK

ساخته و روی دستگاه واقعی تست شده است.

---

# 🗺️ Roadmap

وضعیت قابلیت‌های اصلی فعلی:

* [x] Authentication
* [x] Home
* [x] Product Catalog
* [x] Categories
* [x] Subcategories
* [x] Product Details
* [x] Search
* [x] Favorites
* [x] Cart
* [x] Address Management
* [x] Order Creation
* [x] My Orders
* [x] Purchase History
* [x] Product Reviews
* [x] Notifications
* [x] Profile
* [x] Password Management
* [x] Supabase Backend
* [x] DataStore
* [x] Release Build
* [x] R8 / ProGuard

قابلیت‌های آینده، در صورت توسعه پروژه، می‌توانند شامل مواردی مانند:

* [ ] اتصال به درگاه پرداخت واقعی
* [ ] پنل مدیریت
* [ ] پنل فروشنده
* [ ] سیستم پیشرفته مدیریت سفارش
* [ ] گزارش‌گیری و Analytics
* [ ] Push Notification پیشرفته

باشند.

---

# ⚠️ محدودیت‌های فعلی

این بخش برای مستندسازی صادقانه وضعیت پروژه در نظر گرفته شده است.

هر قابلیتی که هنوز در پروژه پیاده‌سازی نشده باشد، باید به‌عنوان Future Work یا Roadmap معرفی شود و نه به‌عنوان قابلیت فعلی سیستم.

---

# 📸 Screenshots

> تصاویر واقعی پروژه در این بخش قرار خواهند گرفت.

### 🔐 Authentication

<!-- screenshot -->

### 🏠 Home

<!-- screenshot -->

### 🛍️ Product

<!-- screenshot -->

### 🛒 Cart

<!-- screenshot -->

### 📦 Orders

<!-- screenshot -->

### 👤 Profile

<!-- screenshot -->

### 🔔 Notifications

<!-- screenshot -->

---

# 🧩 ساختار کلی سیستم

```text
                         ┌───────────────────────┐
                         │      Android App      │
                         │    Kotlin + Compose   │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │       ViewModel       │
                         └───────────┬───────────┘
                                     │
                                     ▼
                         ┌───────────────────────┐
                         │      Repository       │
                         └───────────┬───────────┘
                                     │
                    ┌────────────────┴────────────────┐
                    │                                 │
                    ▼                                 ▼
          ┌──────────────────┐              ┌──────────────────┐
          │    DataStore     │              │     Supabase     │
          │   Local Storage  │              │     Backend      │
          └──────────────────┘              └────────┬─────────┘
                                                     │
                                  ┌──────────────────┼──────────────────┐
                                  │                  │                  │
                                  ▼                  ▼                  ▼
                               Auth             PostgreSQL           Storage
```

---

# 📚 اصول طراحی پروژه

در توسعه پروژه اصول زیر مورد توجه قرار گرفته‌اند:

* Separation of Concerns
* Single Responsibility
* Repository Pattern
* Dependency Injection
* Feature-based Organization
* Reusable UI Components
* State-driven UI
* Separation of Domain Models and DTOs
* Local / Remote Data Separation
* Modular and Maintainable Structure

---

# 👨‍💻 توسعه‌دهنده

**Sadeghi**

Android Developer
Kotlin / Jetpack Compose / Supabase

---

# 📄 License

مجوز استفاده از پروژه در نسخه نهایی Repository مشخص خواهد شد.

---

## ⭐ درباره پروژه

این پروژه با تمرکز بر طراحی و پیاده‌سازی یک سیستم فروشگاه آنلاین واقعی توسعه داده شده است و علاوه بر رابط کاربری Android، شامل Backend، احراز هویت، پایگاه داده، ذخیره‌سازی فایل، مدیریت سفارش، سبد خرید، نظرات، اعلان‌ها و مدیریت اطلاعات کاربران است.

هدف اصلی پروژه، پیاده‌سازی یک ساختار قابل توسعه و قابل نگهداری با استفاده از تکنولوژی‌های مدرن Android و یک Backend ابری است.
