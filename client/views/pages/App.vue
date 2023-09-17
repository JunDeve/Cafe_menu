<template>
  <Header></Header>
  <div>
    <router-view/>
  </div>
  <div class="footer">
    <div class="contact-info">
      <h4>개발자 정보</h4>
      <p>이름 : 김준</p>
      <p>이메일 : qnfto02143@naver.com</p>
      <p>전화번호 : 010-XXXX-XXXX</p>
      <p>학교 : 경일대학교</p>
      <p>SNS : I don’t use social media other than Instagram.</p>
    </div>
    <div class="sns-info" style="margin-right: 30px;">
      <h4 >SNS 정보</h4>
      <p>instagram :</p>
      <p><img src="/client/views/img/ins.png" alt="ins" width="100"></p>
    </div>
    <div class="site-info">
      <h4>사이트 정보</h4>
      <p>The current site is a private, unhosted site.</p>
      <p>Designed exclusively for swEDU.</p>
      <p>Top icon and representative image.</p>
      <p><img src="favicon.ico" alt="ico" width="50"></p>
    </div>
    <div class="copyright-info">
      <h4>저작권 정보</h4>
      <p>아이콘팩 : https://www.flaticon.com/kr/packs/simple-line-handdrawn?word=simple%20ui</p>
      <br/>
      <p>무료이미지 : https://pixabay.com/ko/</p>
    </div>
  </div>
</template>

<script>
import Header from '../layout/Header.vue';
import Main from './main/main.vue';

const App = {
  data() {
    return {
      dataTest: 2,
      dataTest2: 2
    };
  },
  methods: {},
  watch: {
    dataTest(oldValue, newValue) {
      // watch 로직 추가
    }
  },
  computed: {
    dataTestAdd() {
      return this.dataTest + this.dataTest2;
    }
  },
  components: {
    'Header': Header,
    'Main': Main,
  },
  mounted() {
    console.log("App.vue가 초기화됨");
  }
};

export default App;
</script>

<style scoped>
.footer {
  background-color: #333;
  color: #fff;
  display: flex;
  justify-content: space-between; /* 각 div 사이에 균일한 간격을 만듭니다. */
  padding: 10px 0 10px 0;
}
h4 {
  margin-bottom: 5px;
}

.contact-info ,
.sns-info ,
.site-info ,
.copyright-info {
  margin: 0 5px;
}

.contact-info p,
.sns-info p,
.site-info p,
.copyright-info p {
  margin: 0 5px;
  font-size: 12px;
}

.contact-info {
  margin-left: 60px;
}

.copyright-info {
  margin-right: 60px;
}
</style>
