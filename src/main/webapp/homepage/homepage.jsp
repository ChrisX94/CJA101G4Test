<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shakemates - 連結深度，分享時刻</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }

        /* 頂部導航欄 */
        .header {
            /* background: rgba(255, 255, 255, 0.1); */
            background-color: gray;
            backdrop-filter: blur(10px);
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
        }

        .nav-menu {
            display: flex;
            gap: 30px;
            align-items: center;
        }

        .nav-item {
            color: white;
            text-decoration: none;
            font-weight: 500;
            padding: 8px 16px;
            border-radius: 20px;
            transition: all 0.3s ease;
        }

        .nav-item:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: translateY(-2px);
        }

        .search-btn {
            background: linear-gradient(45deg, #667eea, #764ba2);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 25px;
            cursor: pointer;
            font-weight: bold;
            transition: all 0.3s ease;
        }

        .search-btn:hover {
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .user-profile {
            width: 40px;
            height: 40px;
            background: rgba(255, 255, 255, 0.3);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .user-profile:hover {
            background: rgba(255, 255, 255, 0.4);
            transform: scale(1.1);
        }

        /* 主要內容區 */
        .main-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 40px 20px;
        }

        /* 輪播區域 */
        .hero-section {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(15px);
            border-radius: 20px;
            padding: 50px;
            text-align: center;
            margin-bottom: 40px;
            position: relative;
            overflow: hidden;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        .carousel-container {
            position: relative;
            width: 100%;
            height: 300px;
            margin-bottom: 30px;
        }

        .carousel-slide {
            position: absolute;
            width: 100%;
            height: 100%;
            opacity: 0;
            transition: opacity 0.5s ease-in-out;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .carousel-slide.active {
            opacity: 1;
        }

        .slide-icon {
            width: 80px;
            height: 80px;
            background: rgba(255, 255, 255, 0.3);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 36px;
            margin-bottom: 20px;
            animation: float 3s ease-in-out infinite;
        }

        @keyframes float {

            0%,
            100% {
                transform: translateY(0px);
            }

            50% {
                transform: translateY(-10px);
            }
        }

        .hero-title {
            font-size: 3.5em;
            font-weight: bold;
            color: white;
            margin-bottom: 20px;
            text-shadow: 0 2px 10px rgba(0, 0, 0, 0.3);
        }

        .hero-subtitle {
            font-size: 1.3em;
            color: rgba(255, 255, 255, 0.9);
            margin-bottom: 30px;
        }

        .start-btn {
            background: linear-gradient(45deg, #ff6b6b, #ee5a24);
            color: white;
            border: none;
            padding: 15px 40px;
            font-size: 1.1em;
            border-radius: 30px;
            cursor: pointer;
            font-weight: bold;
            transition: all 0.3s ease;
            box-shadow: 0 5px 15px rgba(238, 90, 36, 0.4);
        }

        .start-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(238, 90, 36, 0.6);
        }

        /* 輪播控制點 */
        .carousel-dots {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin-top: 20px;
        }

        .dot {
            width: 12px;
            height: 12px;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.5);
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .dot.active {
            background: white;
            transform: scale(1.2);
        }

        /* 功能區域 */
        .features-section {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 30px;
            margin-bottom: 40px;
        }

        .feature-card {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 40px 30px;
            text-align: center;
            transition: all 0.3s ease;
            cursor: pointer;
            position: relative;
            overflow: hidden;
        }

        .feature-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: linear-gradient(45deg, transparent, rgba(255, 255, 255, 0.1), transparent);
            transform: translateX(-100%);
            transition: transform 0.6s ease;
        }

        .feature-card:hover::before {
            transform: translateX(100%);
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        }

        .feature-shape {
            width: 100px;
            height: 100px;
            margin: 0 auto 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-weight: bold;
            font-size: 1.1em;
            transition: all 0.3s ease;
        }

        .triangle {
            background: linear-gradient(45deg, #2dd4bf, #06b6d4);
            clip-path: polygon(50% 0%, 0% 100%, 100% 100%);
        }

        .square {
            background: linear-gradient(45deg, #fbbf24, #f59e0b);
            border-radius: 15px;
        }

        .circle {
            background: linear-gradient(45deg, #c084fc, #a855f7);
            border-radius: 50%;
        }

        .feature-card:hover .feature-shape {
            transform: scale(1.1) rotate(5deg);
        }

        /* 底部區域 */
        .bottom-section {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 30px;
            margin-bottom: 30px;
        }

        .news-section,
        .teachers-section {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            padding: 30px;
            min-height: 200px;
        }

        .section-title {
            color: white;
            font-size: 1.3em;
            font-weight: bold;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .news-item {
            background: rgba(255, 255, 255, 0.1);
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 15px;
            transition: all 0.3s ease;
        }

        .news-item:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: translateX(5px);
        }

        /* 服務區域 */
        .service-section {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(5px);
            border-radius: 15px;
            padding: 20px;
            text-align: center;
            color: white;
            font-size: 1.2em;
            margin-bottom: 20px;
        }

        /* 頁尾 */
        .footer {
            text-align: center;
            color: rgba(255, 255, 255, 0.7);
            padding: 20px;
        }

        /* 響應式設計 */
        @media (max-width: 768px) {
            .header {
                padding: 10px 20px;
            }

            .nav-menu {
                gap: 15px;
            }

            .nav-item {
                padding: 5px 10px;
                font-size: 0.9em;
            }

            .hero-title {
                font-size: 2.5em;
            }

            .hero-subtitle {
                font-size: 1.1em;
            }

            .bottom-section {
                grid-template-columns: 1fr;
            }

            .features-section {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>

<body>
    <!-- 頂部導航欄 -->
    <header class="header">
        <nav class="nav-menu">
            <button class="search-btn">搜尋</button>
            <a href="#" class="nav-item">首頁</a>
            <a href="#" class="nav-item">活動</a>
            <a href="#" class="nav-item">社群</a>
            <a href="#" class="nav-item">商店</a>
            <a href="#" class="nav-item">關於我們</a>
        </nav>
        <div class="user-profile">👤</div>
    </header>

    <!-- 主要內容 -->
    <main class="main-container">
        <!-- 輪播英雄區域 -->
        <section class="hero-section">
            <div class="carousel-container">
                <div class="carousel-slide active">
                    <div class="slide-icon">🤝</div>
                    <h1 class="hero-title">Shakemates</h1>
                    <p class="hero-subtitle">Have fun, Connect deeply, Share moments !</p>
                </div>
                <div class="carousel-slide">
                    <div class="slide-icon">🎉</div>
                    <h1 class="hero-title">歡樂時光</h1>
                    <p class="hero-subtitle">與朋友一起創造美好回憶</p>
                </div>
                <div class="carousel-slide">
                    <div class="slide-icon">💫</div>
                    <h1 class="hero-title">深度連結</h1>
                    <p class="hero-subtitle">建立真摯友誼，分享生活點滴</p>
                </div>
            </div>
            <div class="carousel-dots">
                <span class="dot active" onclick="currentSlide(1)"></span>
                <span class="dot" onclick="currentSlide(2)"></span>
                <span class="dot" onclick="currentSlide(3)"></span>
            </div>
            <button class="start-btn" onclick="startMatch()">開始配對</button>
        </section>

        <!-- 功能區域 -->
        <section class="features-section">
            <div class="feature-card" onclick="visitShop()">
                <div class="feature-shape triangle">
                    <span>商店</span>
                </div>
                <p style="color: rgba(255,255,255,0.9);">點擊造訪商店</p>
            </div>
            <div class="feature-card" onclick="visitActivity()">
                <div class="feature-shape square">
                    <span>活動</span>
                </div>
                <p style="color: rgba(255,255,255,0.9);">點擊造訪活動</p>
            </div>
            <div class="feature-card" onclick="visitCommunity()">
                <div class="feature-shape circle">
                    <span>社群</span>
                </div>
                <p style="color: rgba(255,255,255,0.9);">點擊造訪社群</p>
            </div>
        </section>

        <!-- 底部區域 -->
        <section class="bottom-section">
            <div class="news-section">
                <h3 class="section-title">📰 最新消息</h3>
                <div class="news-item">新功能上線：群組聊天室</div>
                <div class="news-item">週末活動：線上交友派對</div>
                <div class="news-item">系統維護通知</div>
            </div>
            <div class="teachers-section">
                <h3 class="section-title">👨‍🏫 我們的導師</h3>
                <p style="color: rgba(255,255,255,0.8);">專業的交友指導團隊，協助您建立真摯的人際關係，提供個人化的建議和支持。</p>
            </div>
        </section>

        <!-- 服務區域 -->
        <section class="service-section">
            <p>💎 優質服務</p>
        </section>
    </main>

    <!-- 頁尾 -->
    <footer class="footer">
        <p>Made with 💎 Whimsical</p>
    </footer>

    <script>
        let slideIndex = 1;
        let slideInterval;

        // 初始化輪播
        function initCarousel() {
            showSlide(slideIndex);
            startAutoSlide();
        }

        // 顯示指定幻燈片
        function showSlide(n) {
            let slides = document.querySelectorAll('.carousel-slide');
            let dots = document.querySelectorAll('.dot');

            if (n > slides.length) { slideIndex = 1; }
            if (n < 1) { slideIndex = slides.length; }

            slides.forEach(slide => slide.classList.remove('active'));
            dots.forEach(dot => dot.classList.remove('active'));

            if (slides[slideIndex - 1]) {
                slides[slideIndex - 1].classList.add('active');
            }
            if (dots[slideIndex - 1]) {
                dots[slideIndex - 1].classList.add('active');
            }
        }

        // 切換到指定幻燈片
        function currentSlide(n) {
            slideIndex = n;
            showSlide(slideIndex);
            resetAutoSlide();
        }

        // 下一張幻燈片
        function nextSlide() {
            slideIndex++;
            showSlide(slideIndex);
        }

        // 開始自動輪播
        function startAutoSlide() {
            slideInterval = setInterval(nextSlide, 4000);
        }

        // 重置自動輪播
        function resetAutoSlide() {
            clearInterval(slideInterval);
            startAutoSlide();
        }

        // 功能按鈕事件
        function startMatch() {
            alert('🎉 歡迎加入 Shakemates！開始您的交友之旅！');
        }

        function visitShop() {
            alert('🛍️ 即將前往商店頁面...');
        }

        function visitActivity() {
            alert('🎯 即將前往活動頁面...');
        }

        function visitCommunity() {
            alert('👥 即將前往社群頁面...');
        }

        // 頁面載入時初始化
        document.addEventListener('DOMContentLoaded', function () {
            initCarousel();

            // 添加平滑滾動效果
            document.querySelectorAll('a[href^="#"]').forEach(anchor => {
                anchor.addEventListener('click', function (e) {
                    e.preventDefault();
                    const target = document.querySelector(this.getAttribute('href'));
                    if (target) {
                        target.scrollIntoView({
                            behavior: 'smooth',
                            block: 'start'
                        });
                    }
                });
            });

            // 添加滾動動畫效果
            const observerOptions = {
                threshold: 0.1,
                rootMargin: '0px 0px -50px 0px'
            };

            const observer = new IntersectionObserver(function (entries) {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        entry.target.style.opacity = '1';
                        entry.target.style.transform = 'translateY(0)';
                    }
                });
            }, observerOptions);

            // 為所有卡片添加觀察者
            document.querySelectorAll('.feature-card, .news-section, .teachers-section').forEach(el => {
                el.style.opacity = '0';
                el.style.transform = 'translateY(30px)';
                el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
                observer.observe(el);
            });
        });

        // 滑鼠懸停時暫停輪播
        document.querySelector('.hero-section').addEventListener('mouseenter', function () {
            clearInterval(slideInterval);
        });

        document.querySelector('.hero-section').addEventListener('mouseleave', function () {
            startAutoSlide();
        });
    </script>
</body>

</html>