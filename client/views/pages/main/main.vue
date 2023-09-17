<template>
  <div class="main-container">
    <div class="up-icon-container">
      <div class="icon-container">
        <img
            src="client/views/pages/main/images/home.png"
            alt="홈 버튼 아이콘"
            class="icon"
            @click="handleHomeIconClick"
            @mouseover="showTooltip('첫페이지로 이동')"
            @mouseout="hideTooltip()"
        />
        <div v-if="showHomeTooltip" class="tooltip">{{ tooltipText }}</div>
      </div>
      <div class="icon-container">
        <img
            src="client/views/pages/main/images/camera.png"
            alt="카메라 아이콘"
            class="icon"
            @click="handleCameraIconClick"
            @mouseover="showTooltip('전체화면 캡처')"
            @mouseout="hideTooltip()"
        />
        <div v-if="showCameraTooltip" class="tooltip">{{ tooltipText }}</div>
      </div>
    </div>
    <div class="All-content">
      <div class="content-container" :class="{ 'move-left': isContentContainer2Visible }">
        <h1>Cafe Page</h1>
        <p style="color: rgb(128,128,128); margin-bottom: 10px;">For many people, enjoying a cup of coffee is a daily
          routine and a fun way to have fun.</p>
        <div class="oneimage-container">
          <img src="client/views/pages/main/images/coffee.jpg" alt="메인 이미지" class="main-image">
        </div>
      </div>
      <div class="content-container2" :class="{ 'move-left': isContentContainer3Visible }">
        <h2>커피</h2>
        <p style="color: rgb(128,128,128); margin-bottom: 10px;">커피는 항산화제를 풍부하게 포함하여 세포 손상을 줄이고 간 건강을 증진시킬 수 있습니다.</p>
        <p style="color: rgb(128,128,128); margin-bottom: 10px;">적절한 양의 커피 소비는 당뇨병 발병 위험을 낮출 수 있으며, 집중력 향상과 정신 건강에도 도움을
          줄 수 있습니다.</p>
        <br/>
        <h2>coffee</h2>
        <p style="color: rgb(128,128,128); margin-bottom: 10px;">Coffee, rich in antioxidants, can reduce cell damage
          and promote liver health.</p>
        <p style="color: rgb(128,128,128); margin-bottom: 10px;">Moderate coffee consumption may lower the risk of
          diabetes, improve concentration, and benefit mental well-being.</p>
      </div>
      <div class="content-container3" :class="{ 'move-left': isContentContainer4Visible }">
        <div class="menu">
          <div class="menu-item">
            <span class="menu-item-name">아메리카노</span>
            <span class="menu-item-price">3,000원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">에스프레소</span>
            <span class="menu-item-price">2,500원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">카페라떼</span>
            <span class="menu-item-price">4,500원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">초코라떼</span>
            <span class="menu-item-price">4,500원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">레몬에이드</span>
            <span class="menu-item-price">4,500원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">유자에이드</span>
            <span class="menu-item-price">4,500원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">히비스커스차</span>
            <span class="menu-item-price">4,000원</span>
          </div>
          <div class="menu-item">
            <span class="menu-item-name">녹차</span>
            <span class="menu-item-price">4,000원</span>
          </div>
          <p>메뉴판 업데이트중</p>
        </div>
      </div>
    </div>
    <p style="color: whitesmoke;">made by Kim Jun</p>
    <div class="under-icon-container">
      <img
          src="client/views/pages/main/images/change.png"
          alt="변경 아이콘"
          class="icon"
          @click="handleChangeIconClick"
          @mouseover="showTooltip('내용 변경')"
          @mouseout="hideTooltip()"
      />
      <div v-if="showChangeTooltip" class="tooltip">{{ tooltipText }}</div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
import html2canvas from 'html2canvas';

export default {
  data() {
    return {
      showHomeTooltip: false,
      showCameraTooltip: false,
      showChangeTooltip: false,
      tooltipText: '',
      isContentContainer2Visible: false,
      isContentContainer3Visible: false,
      isContentContainer4Visible: false,
      isChangeIconClicked: false,
    };
  },
  methods: {
    handleHomeIconClick() {
      this.$router.push({path: '/home'});
      this.isContentContainer2Visible = false;
      this.isContentContainer3Visible = false;
      this.isContentContainer4Visible = false;
    },
    handleCameraIconClick() {
      this.captureAndSaveScreenshot();
    },
    handleChangeIconClick() {
      if (!this.isChangeIconClicked) {
        this.isContentContainer2Visible = !this.isContentContainer2Visible;
        this.isContentContainer3Visible = !this.isContentContainer3Visible;
      } else {
        this.isContentContainer3Visible = !this.isContentContainer3Visible;
        this.isContentContainer4Visible = !this.isContentContainer4Visible;
      }
      this.isChangeIconClicked = !this.isChangeIconClicked;
    },
    showTooltip(text) {
      this.tooltipText = text;
      if (text === '첫페이지로 이동') {
        this.showHomeTooltip = true;
      } else if (text === '전체화면 캡처') {
        this.showCameraTooltip = true;
      } else if (text === '내용 변경') {
        this.showChangeTooltip = true;
      }
    },
    hideTooltip() {
      this.showHomeTooltip = false;
      this.showCameraTooltip = false;
      this.showChangeTooltip = false;
      this.tooltipText = '';
    },

    captureAndSaveScreenshot() {
      const width = document.documentElement.scrollWidth;
      const height = document.documentElement.scrollHeight;

      html2canvas(document.body, {
        width: width,
        height: height,
      }).then((canvas) => {
        const screenshotDataUrl = canvas.toDataURL('image/png');

        const a = document.createElement('a');
        a.href = screenshotDataUrl;
        a.download = 'screenshot.png'; //
        document.body.appendChild(a);

        a.click();

        document.body.removeChild(a);
      });
    },
  },
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.main-container {
  background-color: #F69B94;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 100%;
  padding: 120px;
  overflow: hidden;
  overflow-x: hidden;
}

.All-content {
  flex-direction: row;
  overflow: hidden;
  display: flex;
  justify-content: center;
  position: relative;
}

.content-container {
  background-color: white;
  padding: 20px;
  text-align: center;
  flex-direction: column;
  align-items: center;
  border: 2px solid black;
  border-radius: 10px;
  max-width: 70%;
  flex: 1;
  transition: transform 0.5s ease;
  z-index: 3;
  margin: 0 auto;
  position: relative;
}

.content-container2 {
  background-color: #f4e793;
  padding: 20px;
  text-align: right;
  flex-direction: column;
  border: 2px solid black;
  border-radius: 10px;
  max-width: 70%;
  transition: transform 0.5s ease;
  z-index: 2;
  position: absolute;
  width: 100%;
  top: 0;
  left: calc(100% - 0px);
}

.content-container3 {
  background-color: #96f493;
  padding: 20px;
  text-align: right;
  flex-direction: column;
  border: 2px solid black;
  border-radius: 10px;
  max-width: 70%;
  transition: transform 0.5s ease;
  z-index: 1;
  position: absolute;
  width: 100%;
  top: 0;
  left: 100%;
}

.move-left {
  transform: translateX(-100%);
}

h1, h2 {
  margin: 0;
  padding: 10px 0;
}

.up-icon-container {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-bottom: 10px;
}

.icon {
  max-width: 96px;
  max-height: 96px;
}

.main-image {
  max-width: 100%;
  max-height: 100%;
  object-fit: contain;
}

.tooltip {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 5px;
  border-radius: 4px;
  z-index: 999;
}

.icon-container {
  max-width: 96px;
  max-height: 96px;
  cursor: pointer;
}

.under-icon-container .icon {
  cursor: grab;
}

.menu {
  background-color: #fff;
  border-radius: 10px;
  padding: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.menu-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  border-bottom: 1px solid #ccc;
  font-size: 15px;
}

.menu-item-name {
  font-weight: bold;
}

.menu-item-price {
  color: #e74c3c;
}

.menu-item:hover {
  background-color: #f9f9f9;
}

.menu p {
  text-align: center;
}
</style>